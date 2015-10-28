package linn.core.lang;

import linn.core.Linn;


public class ProductionRuleBuilder {
	// helper
	private static int currentRuleId = 0;
	// references
	private final Linn linn;
	private final LinnBuilder parent;
	
	public ProductionRuleBuilder(final Linn linn, final LinnBuilder parent) {
		this.linn = linn;
		this.parent = parent;
	}
	
	public ProductionRuleBuilder withName(String name) {
		this.linn.addRule(currentRuleId, name);
		return this;
	}
	
	public ProductionRuleBuilder thatHasWeight(float weight) {
		this.linn.setRuleWeight(currentRuleId, weight);
		return this;
	}
	
	public ProductionRuleProductionBuilder andProduction() {
		final ProductionRuleProductionBuilder contentBuilder = new ProductionRuleProductionBuilder(linn, parent, currentRuleId);
		currentRuleId++;
		return contentBuilder;
	}
}
