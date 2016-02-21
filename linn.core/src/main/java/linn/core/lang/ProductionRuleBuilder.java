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

package linn.core.lang;

import linn.core.Linn;
import linn.core.lang.production.ProductionParameter;

/**
 * A builder (with fluent API) to describe single production rules within an
 * L-System definition.
 * <p>
 * Stochastic and conditional production rules are supported by means of TODO
 * conditions and weights (see {@link #andWeight(double)} and TODO weight func)
 * respectively. If both a condition and weight is provided for the rule, the
 * condition is evaluated first and only if it passes the condition its weight
 * is considered.
 * <p>
 * A production rule has the general form of Id [+ condition][+ weight] +
 * production, where <i>production</i> is covered by
 * {@link ProductionRuleProductionBuilder}.
 * <p>
 *
 * @author Thomas Trojer <thomas@trojer.net> -- Initial contribution
 *
 */
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

	public ProductionRuleBuilder withName(final String name) {
		this.linn.addRule(currentRuleId, name);
		return this;
	}

	public ProductionRuleBuilder andParameter(final String parameterName) {
		ProductionParameter.register(parameterName);
		return this;
	}

	public ProductionRuleBuilder andWeight(final double weight) {
		this.linn.setRuleWeight(currentRuleId, weight);
		return this;
	}

	public ProductionRuleProductionBuilder<LinnBuilder> andProduction() {
		final ProductionRuleProductionBuilder<LinnBuilder> contentBuilder = new ProductionRuleProductionBuilder<LinnBuilder>(
				this.linn, this.parent, currentRuleId);
		currentRuleId++;
		return contentBuilder;
	}
}
