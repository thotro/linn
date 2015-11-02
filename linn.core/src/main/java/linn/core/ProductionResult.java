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

package linn.core;

import linn.core.lang.production.Production;

public class ProductionResult extends Axiom {

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("production result {\n\t");
		for (final Production production : this.productions) {
			sb.append(production.getName());
		}
		sb.append("\n}");
		return sb.toString();
	}
}
