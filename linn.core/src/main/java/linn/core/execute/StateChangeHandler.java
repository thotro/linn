package linn.core.execute;

import linn.core.execute.state.LinnTurtle;

@FunctionalInterface
public interface StateChangeHandler {

	public void handle(final LinnTurtle state);
}
