package linn.core.execute;

import linn.core.execute.state.LinnTurtle;

@FunctionalInterface
public interface ProductionExecutionHandler {

	public void handle(final LinnTurtle state);
}
