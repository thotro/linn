package linn.core.lang.production;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import linn.core.Linn;

public class RewriteProduction implements Production {

	private static final Random LOCAL_RAND = new Random(System.currentTimeMillis());
	
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
		} else if(ruleIds.size() > 1) {
			// TODO impl conditional case
			double totalRuleWeight = 0;
			// range for probability based on weight for all potential rules 
			TreeMap<Double, Integer> weightRangeOfRule = Maps.newTreeMap();
			for(Integer ruleId : ruleIds) {
				double ruleWeight = this.linn.getRuleWeight(ruleId);
				totalRuleWeight += ruleWeight;
				weightRangeOfRule.put(totalRuleWeight, ruleId);
			}
			// pick a random rule considering weights 
			double chosenRangeValue = LOCAL_RAND.nextDouble() * totalRuleWeight;
			Entry<Double, Integer> chosenRuleEntry = weightRangeOfRule.ceilingEntry(chosenRangeValue);
			checkNotNull(chosenRuleEntry);
			List<Production> rewriteProductions = this.linn.getRuleProductions(chosenRuleEntry.getValue());
			return rewriteProductions;
		}
		return null;
	}
	
	public String getName() {
		return ruleName;
	}
}
