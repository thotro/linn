/**
 * Copyright (c) 2015 by Thomas Trojer <thomas@trojer.net>
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

import linn.core.LinnContainer;
import linn.core.RuleProductionContainer;
import linn.core.lang.production.FProduction;
import linn.core.lang.production.RewriteProduction;

/**
 * A builder (with fluent API) to describe the actual production part of a
 * production rule (see {@link ProductionRuleBuilder}).
 * <p>
 * If the rule id occurs during a production iteration, it is rewritten (i.e.
 * replaced) by the contents of this production part. Such production contents
 * can contain rewriting rules itself or describe certain state changes (e.g.
 * those of a <i>turtle</i> that stores render information like current position
 * and orientation).
 *
 * @author Thomas Trojer <thomas@trojer.net>
 * @param <T>
 */
public class ProductionRuleProductionBuilder<T extends LinnContainer> {
	// references
	private final RuleProductionContainer ruleProductionContainer;
	private final T parent;
	// production rule content
	private final int ruleId;

	public ProductionRuleProductionBuilder(
			final RuleProductionContainer ruleProductionContainer,
			final T parent, final int ruleId) {
		this.ruleProductionContainer = ruleProductionContainer;
		this.parent = parent;
		this.ruleId = ruleId;
	}

	public T done() {
		return this.parent;
	}

	public ProductionRuleProductionBuilder<T> F(final FProduction prod) {
		this.ruleProductionContainer.addRuleProduction(this.ruleId, prod);
		return this;
	}

	public ProductionRuleProductionBuilder<T> F() {
		final FProduction fProd = new FProduction() {
		};
		this.ruleProductionContainer.addRuleProduction(this.ruleId, fProd);
		return this;
	}

	public ProductionRuleProductionBuilder<T> rewrite(final String ruleName) {
		final RewriteProduction rwProd = new RewriteProduction(ruleName,
				this.parent.getLinn());
		this.ruleProductionContainer.addRuleProduction(this.ruleId, rwProd);
		return this;
	}

	public ProductionRuleProductionBuilder<T> f() {

		return this;
	}

	public ProductionRuleProductionBuilder<T> yaw(final float deltaYaw) {

		return this;
	}

	public ProductionRuleProductionBuilder<T> pitch(final float deltaPitch) {

		return this;
	}

	public ProductionRuleProductionBuilder<T> roll(final float deltaRoll) {

		return this;
	}
}
