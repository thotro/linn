package linn.core.lang.production;

import java.util.Collections;
import java.util.List;

import linn.core.LinnContainer;
import linn.core.RuleProductionContainer;
import linn.core.execute.state.LinnTurtle;
import linn.core.lang.ProductionRuleProductionBuilder;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.*;

public class BranchProduction<T extends LinnContainer> implements Production,
		RuleProductionContainer {

	private ProductionRuleProductionBuilder<T> builder;

	private List<Production> productions = Lists.newArrayList();

	@SuppressWarnings("unchecked")
	public BranchProduction(final ProductionRuleProductionBuilder<T> parent) {
		checkNotNull(parent);
		this.builder = new ProductionRuleProductionBuilder<T>(this, (T) parent,
				-1);
	}

	public ProductionRuleProductionBuilder<T> getProductionBuilder() {
		return this.builder;
	}

	@Override
	public List<Production> execute(final LinnTurtle state,
			ProductionParameter... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRuleProduction(Integer ruleId, Production production) {
		this.productions.add(production);
	}

	@Override
	public List<Production> getRuleProductions(Integer ruleId) {
		return Collections.unmodifiableList(this.productions);
	}

	@Override
	public String getName() {
		final StringBuilder sb = new StringBuilder("[ ");
		for (Production prod : this.productions) {
			sb.append(prod.getName() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
}
