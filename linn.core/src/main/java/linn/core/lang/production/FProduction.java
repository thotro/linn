package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

public interface FProduction extends Production {

	@Override
	default List<Production> execute(final ProductionParameter... parameters) {
		return Lists.newArrayList(this);
	}

	@Override
	default public String getName() {
		return "F";
	}
}
