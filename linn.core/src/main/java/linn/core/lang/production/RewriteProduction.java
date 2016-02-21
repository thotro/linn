/**
 * Copyright (c) 2016 by Thomas Trojer <thomas@trojer.net>
 * LINN - A small L-System interpreter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package linn.core.lang.production;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import static com.google.common.base.Preconditions.*;

import linn.core.Linn;
import linn.core.execute.StateChangeHandler;
import linn.core.execute.state.LinnTurtle;

/**
 * A rewriting production is used to refer to a rule of the LINN L-system
 * definition that should be revisited in the next execution iteration. That
 * means that the reference to a rule (as defined by this production) is
 * replaced with the actual contents of this rule.
 * <p>
 *
 * In stochastic L-systems an arbitrary matching rule may be target of a
 * rewrite. The actual target is decided locally at runtime.
 * <p>
 *
 * When considering the example rule
 *
 * <pre>
 * A ---> F A
 * </pre>
 *
 * then after every "move" as part of an {@link FProduction} the rule "A" is
 * reconsidered. When executing the rule "A" for three time, the following
 * result is expected:
 *
 * <pre>
 * F F F A
 * </pre>
 *
 * Note that rewrite productions do never modify the state of a
 * {@link LinnTurtle}. Hence, {@link StateChangeHandler}s will never fire when a
 * rewrite production has been processed.
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 */
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
