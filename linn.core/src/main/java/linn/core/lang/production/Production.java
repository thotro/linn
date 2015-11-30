package linn.core.lang.production;

import java.util.List;

import linn.core.execute.state.LinnTurtle;

@FunctionalInterface
public interface Production {

	public List<Production> execute(final LinnTurtle state,
			ProductionParameter... parameters);

	default public String getName() {
		return "?";
	}
}
