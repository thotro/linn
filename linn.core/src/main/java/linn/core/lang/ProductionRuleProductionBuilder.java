package linn.core.lang;

import linn.core.Linn;

public class ProductionRuleProductionBuilder {
	// references
	private final Linn linn;
	private final LinnBuilder parent;
	// production rule content
	private final int ruleId;
	
	public ProductionRuleProductionBuilder(final Linn linn, final LinnBuilder parent, int ruleId) {
		this.linn = linn;
		this.parent = parent;
		this.ruleId = ruleId;
	}
	
	public LinnBuilder done() {
		return this.parent;
	}
	
	public ProductionRuleProductionBuilder F() {
		
		return this;
	}
	
	public ProductionRuleProductionBuilder f() {
		
		return this;
	}
	
	public ProductionRuleProductionBuilder yaw(float deltaYaw) {
		
		return this;
	} 
	
	public ProductionRuleProductionBuilder pitch(float deltaPitch) {
		
		return this;
	} 
	
	public ProductionRuleProductionBuilder roll(float deltaRoll) {
		
		return this;
	} 
}
