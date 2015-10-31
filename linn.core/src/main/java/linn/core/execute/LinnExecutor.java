package linn.core.execute;

import java.util.List;

import linn.core.Axiom;
import linn.core.Linn;
import linn.core.LinnContainer;
import linn.core.ProductionResult;
import linn.core.RuleProductionContainer;
import linn.core.lang.ProductionRuleProductionBuilder;
import linn.core.lang.production.Production;

public class LinnExecutor implements LinnContainer {

	public static LinnExecutor newExecutor() {
		return new LinnExecutor();
	}
	
	private Linn linn;
	private Axiom axiom = new Axiom();
	// lazy result
	private ProductionResult result = null;
	
	protected LinnExecutor() {
		
	}
	
	public LinnExecutor useLinn(final Linn linn) {
		this.linn = linn;
		return this;
	}
	
	public ProductionRuleProductionBuilder<LinnExecutor> withAxiom() {
		return new ProductionRuleProductionBuilder<LinnExecutor>(axiom, this, -1);
	}
	
	public LinnExecutor execute() {
		// determine current result
		final Axiom currentAxiom;
		if(this.result == null) {
			currentAxiom = this.axiom;
		} else {
			currentAxiom = this.result;
		}
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
			}
		}
		this.result = newResult;
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
}
