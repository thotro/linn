package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

import linn.core.execute.state.LinnTurtle;

public class FProduction implements Production {

	private double length = 1.0;

	public FProduction() {
	}

	public FProduction(double length) {
		this.length = length;
	}

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
