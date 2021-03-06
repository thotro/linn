/**
 * Copyright (c) 2016 by Thomas Trojer <thomas@trojer.net>
 * LINN - A small L-System interpreter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package linn.core.execute;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

import static com.google.common.base.Preconditions.*;

import linn.core.Axiom;
import linn.core.Linn;
import linn.core.LinnContainer;
import linn.core.ProductionResult;
import linn.core.RuleProductionContainer;
import linn.core.execute.state.ImmutableLinnTurtle;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.ProductionRuleProductionBuilder;
import linn.core.lang.production.BranchProduction;
import linn.core.lang.production.Production;
import linn.core.lang.production.RewriteProduction;

public class LinnExecutor implements LinnContainer {

	public static LinnExecutor newExecutor() {
		return new LinnExecutor();
	}

	private Linn linn;
	private final Axiom axiom = new Axiom();
	// lazy result
	private ProductionResult result = null;
	private int iterations = 0;
	private boolean terminated = false;
	// turtle
	private LinnTurtle state = new LinnTurtle();
	// trace turtle states
	private boolean traceStates = true;
	// state change handler
	private StateChangeHandler stateChangeHandler;
	// helpers for partial execution
	private List<Production> openProductions = Lists.newArrayList();
	private ProductionResult openResult = null;

	protected LinnExecutor() {
		this.reset();
	}

	public LinnExecutor useLinn(final Linn linn) {
		checkNotNull(linn);
		this.linn = linn;
		return this;
	}

	public LinnExecutor onStateChanged(final StateChangeHandler stateChangeHandler) {
		checkNotNull(stateChangeHandler);
		this.stateChangeHandler = stateChangeHandler;
		if (this.stateChangeHandler != null) {
			this.state.addStateChangeHandler(this.stateChangeHandler);
		}
		return this;
	}

	public ProductionRuleProductionBuilder<LinnExecutor> withAxiom() {
		return new ProductionRuleProductionBuilder<LinnExecutor>(this.axiom, this, -1);
	}

	/**
	 * Tells the executor whether a history of (immutable) states shall be
	 * tracked while performing a number of iterations.
	 *
	 * @param trace
	 *            <code>true</code> if states shall be traced (accessible via
	 *            {@link LinnTurtle#getPreviousState()}, <code>false</code>
	 *            otherwise, in which {@link LinnTurtle#getPreviousState()} will
	 *            always return <code>null</code>.
	 * @return This executor for chaining.
	 */
	public LinnExecutor traceStates(boolean trace) {
		this.traceStates = trace;
		this.state.setTrace(this.traceStates);
		return this;
	}

	/**
	 * A partial execution gives back control to the caller after a single state
	 * change occurred. This is useful in render loops with animations, where
	 * typically not entire production execution results are awaited, but the
	 * movement of the turtle is observed.
	 * <p>
	 * <b>Note</b> that in case you want to abort partial execution you need to
	 * either call {@link #reset()} or start a new (non-partial) execution with
	 * {@link #executeOnce()}, {@link #executeAtMost(int)} or
	 * {@link #executeAtMost(int, PostExecutionHandler)}.
	 *
	 * @param postHandler
	 *            The handler that is called after an execution is completed. In
	 *            case of partial executions this is typically not after every
	 *            call to # {@link #executePartial()} or
	 *            {@link #executePartial(PostExecutionHandler)}.
	 * @return This executor for chaining.
	 */
	public LinnExecutor executePartial(final PostExecutionHandler postHandler) {
		this.execute(true);
		if (this.openProductions.isEmpty()) {
			postHandler.handle(this.result);
		}
		return this;
	}

	/**
	 * See {@link #executePartial(PostExecutionHandler)}. No notification is
	 * sent after execution completion.
	 */
	public LinnExecutor executePartial() {
		return this.executePartial(p -> {
		});
	}

	public LinnExecutor executeOnce() {
		return this.execute(false);
	}

	public LinnExecutor executeAtMost(final int iterations) {
		return this.executeAtMost(iterations, p -> {
		});
	}

	public LinnExecutor executeAtMost(final int iterations, final PostExecutionHandler postHandler) {
		checkNotNull(postHandler);
		checkArgument(iterations > 0);
		while (this.isTerminated() == false && this.getIterationCount() < iterations) {
			this.execute(false);
			postHandler.handle(this.result);
		}
		return this;
	}

	private LinnExecutor execute(boolean partial) {
		// early check of termination state
		if (this.isTerminated()) {
			return this;
		}
		// determine current result
		final Axiom currentAxiom;
		if (this.result == null) {
			currentAxiom = this.axiom;
		} else {
			currentAxiom = this.result;
		}
		// remember if at least one rewrite production was obtained
		boolean willHaveRewrite = false;
		// execute
		if (this.openProductions.isEmpty()) {
			this.resetState();
			this.openProductions.addAll(currentAxiom.getRuleProductions(-1));
			this.openResult = new ProductionResult();
		}
		Iterator<Production> productionIter = this.openProductions.iterator();
		while (productionIter.hasNext()) {
			final Production production = productionIter.next();
			final List<Production> newProductions = production.execute(this.state);
			if (newProductions == null) {
				// terminated, nothing to add
				continue;
			}
			// add all new productions (most of the time this is one to one)
			for (final Production newProduction : newProductions) {
				this.openResult.addRuleProduction(-1, newProduction);
				// check if this production will rewrite in any way
				if (this.willRewrite(newProduction)) {
					willHaveRewrite = true;
				}
			}
			// remove from open productions
			productionIter.remove();
			// check partial execution condition
			if (partial && productionIter.hasNext() != false) {
				return this;
			}
		}
		// check if termination state changed
		if (willHaveRewrite == false) {
			this.terminated = true;
		}
		this.result = this.openResult;
		// successfully iterated
		this.iterations++;
		return this;
	}

	public void reset() {
		this.result = null;
		this.iterations = 0;
		this.terminated = false;
		this.resetState();
	}

	private void resetState() {
		// TODO copy from initially given turtle state (yet to be implemented)
		this.state = new LinnTurtle();
		if (this.stateChangeHandler != null) {
			this.state.addStateChangeHandler(this.stateChangeHandler);
		}
		this.state.setTrace(this.traceStates);
		this.openProductions.clear();
		this.openResult = null;
	}

	public RuleProductionContainer getProductionResult() {
		if (this.result == null) {
			return this.axiom;
		} else {
			return this.result;
		}
	}

	public LinnTurtle getState() {
		return new ImmutableLinnTurtle(this.state);
	}

	@Override
	public Linn getLinn() {
		return this.linn;
	}

	public int getIterationCount() {
		return this.iterations;
	}

	public boolean isTerminated() {
		return this.terminated;
	}

	private boolean willRewrite(Production production) {
		// fill worker queue
		Deque<Production> openProduction = Queues.newArrayDeque();
		openProduction.offer(production);
		// till no production to consider ...
		while (openProduction.isEmpty() == false) {
			Production currentProduction = openProduction.poll();
			if (currentProduction instanceof RewriteProduction) {
				// we're not done, for sure, as we found a rewrite production
				return true;
			} else if (currentProduction instanceof BranchProduction<?>) {
				BranchProduction<?> branchProduction = (BranchProduction<?>) currentProduction;
				List<Production> branchInnerProductions = branchProduction.getRuleProductions(-1);
				for (Production branchInnerProduction : branchInnerProductions) {
					openProduction.offer(branchInnerProduction);
				}
			}
		}
		return false;
	}
}
