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

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;

import static com.google.common.base.Preconditions.*;

public class ProductionParameter {

	private static final Map<String, ProductionParameter> productionParameters = Maps
			.newHashMap();

	public static ProductionParameter register(final String parameterName) {
		checkNotNull(parameterName);
		final ProductionParameter param = productionParameters.getOrDefault(
				parameterName, new ProductionParameter(parameterName));
		productionParameters.putIfAbsent(parameterName, param);
		return param;
	}

	public static boolean exists(final String parameterName) {
		return productionParameters.containsKey(parameterName);
	}

	public static ProductionParameter get(final String parameterName) {
		checkArgument(productionParameters.containsKey(parameterName));
		return productionParameters.get(parameterName);
	}

	private final String name;
	private final AtomicDouble value;

	private ProductionParameter(final String name) {
		checkNotNull(name);
		this.name = name;
		this.value = new AtomicDouble(0.0);
	}

	public String getName() {
		return this.name;
	}

	public double getValue() {
		return this.value.get();
	}

	public void setValue(final double value) {
		this.value.set(value);
	}
}
