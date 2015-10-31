package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Iterables;

import linn.core.Linn;

public class RewriteProduction implements Production {

	private String ruleName;
	private final Linn linn;
	
	public RewriteProduction(String ruleName, final Linn linn) {
		this.ruleName = ruleName;
		this.linn = linn;
	}
	
	public List<Production> execute(ProductionParameter... parameters) {
		List<Integer> ruleIds = this.linn.getRuleIds(ruleName);
		if(ruleIds.size() == 1) {
			Integer ruleId = Iterables.getOnlyElement(ruleIds);
			// TODO check condition if any
			List<Production> rewriteProductions = this.linn.getRuleProductions(ruleId);
			return rewriteProductions;
		} else {
			// TODO impl stochastic and conditional case
		}
		return null;
	}
	
	public String getName() {
		return ruleName;
	}
}
