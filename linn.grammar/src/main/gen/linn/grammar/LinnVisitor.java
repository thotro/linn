// Generated from Linn.g4 by ANTLR 4.5

    package linn.grammar;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LinnParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LinnVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LinnParser#linn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinn(LinnParser.LinnContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinnParser#linnRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinnRule(LinnParser.LinnRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinnParser#linnProduction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinnProduction(LinnParser.LinnProductionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinnParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(LinnParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinnParser#keyValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyValue(LinnParser.KeyValueContext ctx);
}