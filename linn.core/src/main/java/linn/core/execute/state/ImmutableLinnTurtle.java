package linn.core.execute.state;

import com.google.common.collect.Lists;

public class ImmutableLinnTurtle extends LinnTurtle {

	public ImmutableLinnTurtle(final LinnTurtle copy) {
		super(copy.position.getX(), copy.position.getY(), copy.position.getZ(),
				copy.yaw, copy.pitch, copy.roll, copy.properties,
				copy.baseDirection);
		// fields additionally considered for copying
		this.stateChangeHandlers = Lists.newArrayList(copy.stateChangeHandlers);
		this.previousState = copy.previousState;
		this.traceStates = copy.traceStates;
	}

	@Override
	public void move(double distance) {
		throw new UnsupportedOperationException(
				"Immutable copy of a LinnTurtle");
	}

	@Override
	public void yaw(double delta) {
		throw new UnsupportedOperationException(
				"Immutable copy of a LinnTurtle");
	}

	@Override
	public void pitch(double delta) {
		throw new UnsupportedOperationException(
				"Immutable copy of a LinnTurtle");
	}

	@Override
	public void roll(double delta) {
		throw new UnsupportedOperationException(
				"Immutable copy of a LinnTurtle");
	}
}
