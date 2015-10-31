package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

public interface FProduction extends Production {

	default List<Production> execute(ProductionParameter... parameters) {
		return Lists.newArrayList(this);
	}
	
	default public String getName() {
		return "F";
	}
}
