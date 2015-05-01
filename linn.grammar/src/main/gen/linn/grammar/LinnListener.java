// Generated from Linn.g4 by ANTLR 4.5

    package linn.grammar;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LinnParser}.
 */
public interface LinnListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LinnParser#linn}.
	 * @param ctx the parse tree
	 */
	void enterLinn(LinnParser.LinnContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinnParser#linn}.
	 * @param ctx the parse tree
	 */
	void exitLinn(LinnParser.LinnContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinnParser#linnRule}.
	 * @param ctx the parse tree
	 */
	void enterLinnRule(LinnParser.LinnRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinnParser#linnRule}.
	 * @param ctx the parse tree
	 */
	void exitLinnRule(LinnParser.LinnRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinnParser#linnProduction}.
	 * @param ctx the parse tree
	 */
	void enterLinnProduction(LinnParser.LinnProductionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinnParser#linnProduction}.
	 * @param ctx the parse tree
	 */
	void exitLinnProduction(LinnParser.LinnProductionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinnParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(LinnParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinnParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(LinnParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinnParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void enterKeyValue(LinnParser.KeyValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinnParser#keyValue}.
	 * @param ctx the parse tree
	 */
	void exitKeyValue(LinnParser.KeyValueContext ctx);
}