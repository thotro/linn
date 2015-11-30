package linn.core.lang.production;

import java.util.List;

import linn.core.execute.state.LinnTurtle;

import com.google.common.collect.Lists;

public class FProduction implements Production {

	private double length = 1.0;

	@Override
	public List<Production> execute(final LinnTurtle state,
			final ProductionParameter... parameters) {
		state.move(this.length);
		return Lists.newArrayList(this);
	}

	@Override
	public String getName() {
		return "F";
	}
}
