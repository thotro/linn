package linn.core.lang.production;

import java.util.List;

import com.google.common.collect.Lists;

import linn.core.execute.state.LinnTurtle;

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
		state.yaw(this.deltaYaw);
		state.pitch(this.deltaPitch);
		state.roll(this.deltaRoll);
		return Lists.newArrayList(this);
	}

	@Override
	public String getName() {
		return "R(" + this.deltaYaw + ", " + this.deltaPitch + ", "
				+ this.deltaRoll + ")";
	}
}
