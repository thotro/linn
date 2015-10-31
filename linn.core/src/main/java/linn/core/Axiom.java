package linn.core;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import linn.core.lang.production.Production;

public class Axiom implements RuleProductionContainer {

	protected final List<Production> productions = Lists.newArrayList();
	
	@Override
	public void addRuleProduction(Integer ruleId, Production production) {
		// rule id is ignored for the axiom
		this.productions.add(production);
	}
	
	public List<Production> getRuleProductions(Integer ruleId) {
		return Collections.unmodifiableList(this.productions);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("axiom {\n\t");
		for(Production production : this.productions) {
			sb.append(production.getName());
		}
		sb.append("\n}");
		return sb.toString();
	}
}
