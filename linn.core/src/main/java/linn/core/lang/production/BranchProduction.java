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

package linn.core.lang.production;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.*;

import linn.core.LinnContainer;
import linn.core.RuleProductionContainer;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.ProductionRuleProductionBuilder;

/**
 * A branching production is used to isolate certain production elements of a
 * production rule and execute them without them influencing the
 * {@link LinnTurtle} state for productions outside of the branch. Still a newly
 * opened branch refers to a previous state as all other productions do too.
 * <p>
 *
 * When considering the following example rule
 *
 * <pre>
 * A ---> F [ F ] F
 * </pre>
 *
 * the branched F production (i.e. the "F" in brackets) bases on the outcome of
 * the first "F". The third "F" will also base on the first "F"'s
 * outcomes as the branch is executed in an isolated way.
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 * @param <T>
 */
public class BranchProduction<T extends LinnContainer>
		implements Production, RuleProductionContainer {

	private ProductionRuleProductionBuilder<T> builder;
	private final ProductionRuleProductionBuilder<T> parent;

	private List<Production> productions = Lists.newArrayList();

	@SuppressWarnings("unchecked")
	public BranchProduction(final ProductionRuleProductionBuilder<T> parent) {
		checkNotNull(parent);
		this.parent = parent;
		this.builder = new ProductionRuleProductionBuilder<T>(this,
				(T) this.parent, -1);
	}

	public ProductionRuleProductionBuilder<T> getProductionBuilder() {
		return this.builder;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			ProductionParameter... parameters) {
		// copy state (stack push)
		LinnTurtle pushState = new LinnTurtle(state);
		BranchProduction<T> branch = new BranchProduction<T>(this.parent);
		for (Production production : this.productions) {
			List<Production> newProductions = production.execute(pushState,
					parameters);
			for (Production newProduction : newProductions) {
				branch.addRuleProduction(-1, newProduction);
			}
		}
		return Lists.newArrayList(branch);
	}

	@Override
	public void addRuleProduction(Integer ruleId, Production production) {
		this.productions.add(production);
	}

	@Override
	public List<Production> getRuleProductions(Integer ruleId) {
		return Collections.unmodifiableList(this.productions);
	}

	@Override
	public String getName() {
		final StringBuilder sb = new StringBuilder("[ ");
		for (Production prod : this.productions) {
			sb.append(prod.getName() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
}
