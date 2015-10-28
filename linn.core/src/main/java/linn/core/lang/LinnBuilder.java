package linn.core.lang;

import java.util.List;

import com.google.common.collect.Lists;

import linn.core.Linn;

public class LinnBuilder {

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
		return this.linn;
	}
	
	public ProductionRuleBuilder withRule(String ruleName) {
		final ProductionRuleBuilder ruleBuilder = new ProductionRuleBuilder(this.linn, this);
		return ruleBuilder.withName(ruleName);
	}
}
