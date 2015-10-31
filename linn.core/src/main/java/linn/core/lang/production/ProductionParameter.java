package linn.core.lang.production;

import java.util.Map;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;

public class ProductionParameter {

	private static final Map<String, ProductionParameter> productionParameters = Maps.newHashMap();
	
	public static ProductionParameter register(String parameterName) {
		checkNotNull(parameterName);
		ProductionParameter param = productionParameters.getOrDefault(parameterName, new ProductionParameter(parameterName));
		productionParameters.putIfAbsent(parameterName, param);
		return param;
	}
	
	public static boolean exists(String parameterName) {
		return productionParameters.containsKey(parameterName);
	}
	
	public static ProductionParameter get(String parameterName) {
		checkArgument(productionParameters.containsKey(parameterName));
		return productionParameters.get(parameterName);
	}
	
	private String name;
	private AtomicDouble value;
	
	private ProductionParameter(String name) {
		checkNotNull(name);
		this.name = name;
		this.value = new AtomicDouble(0.0);
	}
	
	public double getValue() {
		return this.value.get();
	}
	
	public void setValue(double value) {
		this.value.set(value);
	}
}
