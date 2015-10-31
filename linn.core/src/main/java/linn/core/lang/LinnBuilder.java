package linn.core.lang;

import linn.core.Linn;
import linn.core.LinnContainer;

public class LinnBuilder implements LinnContainer {

	public static LinnBuilder newLinn(String name) {
		final Linn linn = new Linn();
		linn.setName(name);
		return new LinnBuilder(linn);
	}
	
	private final Linn linn;
	
	private LinnBuilder(final Linn linn) {
		this.linn = linn;
	}
	
	public LinnBuilder withName(String name) {
		this.linn.setName(name);
		return this;
	}
	
	public LinnBuilder withAuthor(String author) {
		this.linn.setAuthor(author);
		return this;
	}
	
	public Linn build() {
		return this.getLinn();
	}
	
	public ProductionRuleBuilder withRule(String ruleName) {
		final ProductionRuleBuilder ruleBuilder = new ProductionRuleBuilder(this.linn, this);
		return ruleBuilder.withName(ruleName);
	}
	
	public Linn getLinn() {
		return this.linn;
	}
}
