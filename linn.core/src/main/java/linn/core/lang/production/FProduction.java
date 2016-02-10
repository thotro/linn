package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.*;

import linn.core.execute.state.LinnTurtle;
import linn.core.math.NumberUtil;

public class FProduction implements Production {

	private double length = 1.0;

	public FProduction() {
	}

	public FProduction(double length) {
		checkArgument(NumberUtil.doubleIsDifferent(length, 0, 1e-9));
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
