package linn.core.execute;

import java.util.List;

import static com.google.common.base.Preconditions.*;

import linn.core.Axiom;
import linn.core.Linn;
import linn.core.LinnContainer;
import linn.core.ProductionResult;
import linn.core.RuleProductionContainer;
import linn.core.lang.ProductionRuleProductionBuilder;
import linn.core.lang.production.Production;
import linn.core.lang.production.RewriteProduction;

public class LinnExecutor implements LinnContainer {

	public static LinnExecutor newExecutor() {
		return new LinnExecutor();
	}
	
	private Linn linn;
	private Axiom axiom = new Axiom();
	// lazy result
	private ProductionResult result = null;
	private int iterations = 0;
	private boolean terminated = false;
	
	protected LinnExecutor() {}
	
	public LinnExecutor useLinn(final Linn linn) {
		checkNotNull(linn);
		this.linn = linn;
		return this;
	}
	
	public ProductionRuleProductionBuilder<LinnExecutor> withAxiom() {
		return new ProductionRuleProductionBuilder<LinnExecutor>(axiom, this, -1);
	}
	
	public LinnExecutor executeOnce() {
		return this.execute();
	}
	
	public LinnExecutor executeAtMost(int iterations) {
		return this.executeAtMost(iterations, () -> {});
	}
	
	public LinnExecutor executeAtMost(int iterations, final PostExecutionHandler postHandler) {
		checkNotNull(postHandler);
		checkArgument(iterations > 0);
		while(this.isTerminated() == false && this.getIterationCount() < iterations) {
			this.execute();
			postHandler.handle();
		}
		return this;
	}
	
	private LinnExecutor execute() {
		// early check of termination state
		if(this.isTerminated()) {
			return this;
		}
		// determine current result
		final Axiom currentAxiom;
		if(this.result == null) {
			currentAxiom = this.axiom;
		} else {
			currentAxiom = this.result;
		}
		// remember if at least one rewrite production was obtained
		boolean willHaveRewrite = false;
		// execute
		final ProductionResult newResult = new ProductionResult();
		for(Production production : currentAxiom.getRuleProductions(-1)) {
			List<Production> newProductions = production.execute();
			if(newProductions == null) {
				// terminated, nothing to add
				continue;
			}
			// add all new productions (most of the time this is one to one)
			for(Production newProduction : newProductions) {
				newResult.addRuleProduction(-1, newProduction);
				if(newProduction instanceof RewriteProduction) {
					willHaveRewrite = true;
				}
			}
		}
		// check if termination state changed
		if(willHaveRewrite == false) {
			this.terminated = true;
		}
		this.result = newResult;
		// successfully iterated
		this.iterations++;
		return this;
	}
	
	public RuleProductionContainer getProductionResult() {
		if(this.result == null) {
			return this.axiom;
		} else {
			return this.result;
		}
	}
	
	public Linn getLinn() {
		return this.linn;
	}
	
	public int getIterationCount() {
		return this.iterations;
	}
	
	public boolean isTerminated() {
		return this.terminated;
	}
}
