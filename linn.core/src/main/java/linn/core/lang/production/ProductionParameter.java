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

	public double getValue() {
		return this.value.get();
	}

	public void setValue(final double value) {
		this.value.set(value);
	}
}
