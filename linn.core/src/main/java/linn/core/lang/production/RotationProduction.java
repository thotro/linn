package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

import linn.core.execute.state.LinnTurtle;
import linn.core.math.NumberUtil;

public class RotationProduction implements Production {

	private double deltaYaw = 0.0;
	private double deltaPitch = 0.0;
	private double deltaRoll = 0.0;

	public RotationProduction(double deltaYaw, double deltaPitch,
			double deltaRoll) {
		this.deltaYaw = deltaYaw;
		this.deltaPitch = deltaPitch;
		this.deltaRoll = deltaRoll;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			ProductionParameter... parameters) {
		if (NumberUtil.doubleIsDifferent(this.deltaYaw, 0, 1e-9)) {
			state.yaw(this.deltaYaw);
		}
		if (NumberUtil.doubleIsDifferent(this.deltaPitch, 0, 1e-9)) {
			state.pitch(this.deltaPitch);
		}
		if (NumberUtil.doubleIsDifferent(this.deltaRoll, 0, 1e-9)) {
			state.roll(this.deltaRoll);
		}
		return Lists.newArrayList(this);
	}

	@Override
	public String getName() {
		return "R(" + this.deltaYaw + ", " + this.deltaPitch + ", "
				+ this.deltaRoll + ")";
	}
}
