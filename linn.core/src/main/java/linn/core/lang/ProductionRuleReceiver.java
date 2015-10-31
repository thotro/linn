package linn.core.lang;

import java.util.List;

import linn.core.lang.production.Production;

public abstract class ProductionRuleReceiver {

	protected abstract void receiveProductionRule(final List<Production> productions);
}
