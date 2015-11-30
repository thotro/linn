package linn.core.lang.production;

import java.util.List;

import linn.core.execute.state.LinnTurtle;

import com.google.common.collect.Lists;

public class RotationProduction implements Production {

	private double deltaYaw = 0.0;
	private double deltaPitch = 0.0;
	private double deltaRoll = 0.0;

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
