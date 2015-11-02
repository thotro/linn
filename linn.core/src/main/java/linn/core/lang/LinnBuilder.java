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

import linn.core.Linn;
import linn.core.LinnContainer;

/**
 * A builder (with fluent API) to describe L-System definitions. Stores some
 * meta information as well as a set of production rules which are built by
 * {@link ProductionRuleBuilder}s.
 *
 * @author Thomas Trojer <thomas@trojer.net>
 */
public class LinnBuilder implements LinnContainer {

	public static LinnBuilder newLinn(final String name) {
		final Linn linn = new Linn();
		linn.setName(name);
		return new LinnBuilder(linn);
	}

	private final Linn linn;

	private LinnBuilder(final Linn linn) {
		this.linn = linn;
	}

	public LinnBuilder withName(final String name) {
		this.linn.setName(name);
		return this;
	}

	public LinnBuilder withAuthor(final String author) {
		this.linn.setAuthor(author);
		return this;
	}

	public Linn build() {
		return this.getLinn();
	}

	public ProductionRuleBuilder withRule(final String ruleName) {
		final ProductionRuleBuilder ruleBuilder = new ProductionRuleBuilder(
				this.linn, this);
		return ruleBuilder.withName(ruleName);
	}

	@Override
	public Linn getLinn() {
		return this.linn;
	}
}
