package linn.core.lang.production;

import java.util.List;

@FunctionalInterface
public interface Production {
	
	public List<Production> execute(ProductionParameter... parameters);
	
	default public String getName() {
		return "?";
	}
}
