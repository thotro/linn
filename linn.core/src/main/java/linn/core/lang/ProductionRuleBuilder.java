package linn.core.lang;

import linn.core.Linn;
import linn.core.lang.production.ProductionParameter;


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
	
	public ProductionRuleBuilder andParameter(String parameterName) {
		ProductionParameter.register(parameterName);
		return this;
	}
	
	public ProductionRuleBuilder andWeight(float weight) {
		this.linn.setRuleWeight(currentRuleId, weight);
		return this;
	}
	
	public ProductionRuleProductionBuilder<LinnBuilder> andProduction() {
		final ProductionRuleProductionBuilder<LinnBuilder> contentBuilder = new ProductionRuleProductionBuilder<LinnBuilder>(linn, parent, currentRuleId);
		currentRuleId++;
		return contentBuilder;
	}
}
