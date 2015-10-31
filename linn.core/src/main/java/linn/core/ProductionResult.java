package linn.core;

import linn.core.lang.production.Production;

public class ProductionResult extends Axiom {

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("production result {\n\t");
		for(Production production : this.productions) {
			sb.append(production.getName());
		}
		sb.append("\n}");
		return sb.toString();
	}
}
