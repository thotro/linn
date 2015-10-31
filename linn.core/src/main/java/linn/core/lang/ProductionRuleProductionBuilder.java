package linn.core.lang;

import linn.core.Linn;
import linn.core.LinnContainer;
import linn.core.RuleProductionContainer;
import linn.core.lang.production.FProduction;
import linn.core.lang.production.RewriteProduction;

public class ProductionRuleProductionBuilder<T extends LinnContainer> {
	// references
	private final RuleProductionContainer ruleProductionContainer;
	private final T parent;
	// production rule content
	private final int ruleId;
	
	public ProductionRuleProductionBuilder(final RuleProductionContainer ruleProductionContainer, final T parent, int ruleId) {
		this.ruleProductionContainer = ruleProductionContainer;
		this.parent = parent;
		this.ruleId = ruleId;
	}
	
	public T done() {
		return this.parent;
	}
	
	public ProductionRuleProductionBuilder<T> F(FProduction prod) {
		this.ruleProductionContainer.addRuleProduction(this.ruleId, prod);
		return this;
	}
	
	public ProductionRuleProductionBuilder<T> F() {
		FProduction fProd = new FProduction() {};
		this.ruleProductionContainer.addRuleProduction(ruleId, fProd);
		return this;
	}
	
	public ProductionRuleProductionBuilder<T> rewrite(String ruleName) {
		RewriteProduction rwProd = new RewriteProduction(ruleName, this.parent.getLinn());
		this.ruleProductionContainer.addRuleProduction(ruleId, rwProd);
		return this;
	}
	
	public ProductionRuleProductionBuilder<T> f() {
		
		return this;
	}
	
	public ProductionRuleProductionBuilder<T> yaw(float deltaYaw) {
		
		return this;
	} 
	
	public ProductionRuleProductionBuilder<T> pitch(float deltaPitch) {
		
		return this;
	} 
	
	public ProductionRuleProductionBuilder<T> roll(float deltaRoll) {
		
		return this;
	} 
}
