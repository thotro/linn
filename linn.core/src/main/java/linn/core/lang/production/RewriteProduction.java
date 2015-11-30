package linn.core.lang.production;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import linn.core.Linn;
import linn.core.execute.state.LinnTurtle;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import static com.google.common.base.Preconditions.*;

public class RewriteProduction implements Production {

	private static final Random LOCAL_RAND = new Random(
			System.currentTimeMillis());

	private final String ruleName;
	private final Linn linn;

	public RewriteProduction(final String ruleName, final Linn linn) {
		this.ruleName = ruleName;
		this.linn = linn;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			final ProductionParameter... parameters) {
		final List<Integer> ruleIds = this.linn.getRuleIds(this.ruleName);
		if (ruleIds.size() == 1) {
			final Integer ruleId = Iterables.getOnlyElement(ruleIds);
			// TODO check condition if any
			final List<Production> rewriteProductions = this.linn
					.getRuleProductions(ruleId);
			return rewriteProductions;
		} else if (ruleIds.size() > 1) {
			// TODO impl conditional case
			double totalRuleWeight = 0;
			// range for probability based on weight for all potential rules
			final TreeMap<Double, Integer> weightRangeOfRule = Maps
					.newTreeMap();
			for (final Integer ruleId : ruleIds) {
				final double ruleWeight = this.linn.getRuleWeight(ruleId);
				totalRuleWeight += ruleWeight;
				weightRangeOfRule.put(totalRuleWeight, ruleId);
			}
			// pick a random rule considering weights
			final double chosenRangeValue = LOCAL_RAND.nextDouble()
					* totalRuleWeight;
			final Entry<Double, Integer> chosenRuleEntry = weightRangeOfRule
					.ceilingEntry(chosenRangeValue);
			checkNotNull(chosenRuleEntry);
			final List<Production> rewriteProductions = this.linn
					.getRuleProductions(chosenRuleEntry.getValue());
			return rewriteProductions;
		}
		return null;
	}

	@Override
	public String getName() {
		return this.ruleName;
	}
}
