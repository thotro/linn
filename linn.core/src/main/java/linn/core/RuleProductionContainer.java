package linn.core;

import java.util.List;

import linn.core.lang.production.Production;

public interface RuleProductionContainer {

	public void addRuleProduction(Integer ruleId, Production production);
	
	public List<Production> getRuleProductions(Integer ruleId);
}
