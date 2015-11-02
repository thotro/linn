package linn.core.grammar;

import java.util.List;
import java.util.Map;

import linn.grammar.LinnLexer;
import linn.grammar.LinnParser;
import linn.grammar.LinnParser.LinnContext;
import linn.grammar.LinnParser.LinnRuleContext;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BasicParserTest {

	@Test
	public void test() {
		// input and parse
		final CharStream inputStream = new ANTLRInputStream(
				"linn name { H -> F F < > + - [ A B ]; }");
		final LinnLexer lexer = new LinnLexer(inputStream);
		final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		final LinnParser parser = new LinnParser(tokenStream);
		final LinnContext parseContext = parser.linn();
		// extracts from the L-system definition
		final Map<String, List<Integer>> ruleIdsOfRuleName = Maps.newHashMap();
		final Map<Integer, Float> weightOfRuleId = Maps.newHashMap();
		// visitor
		final ParseTreeWalker parseTreeWalker = ParseTreeWalker.DEFAULT;
		parseTreeWalker.walk(new ParseTreeListener() {

			private int currentRuleId = 0;

			@Override
			public void visitTerminal(final TerminalNode node) {
				System.out.println("TERMINAL: " + node.getText());
			}

			@Override
			public void visitErrorNode(final ErrorNode node) {
				// TODO Auto-generated method stub

			}

			@Override
			public void exitEveryRule(final ParserRuleContext ctx) {
				// TODO Auto-generated method stub

			}

			@Override
			public void enterEveryRule(final ParserRuleContext ctx) {
				if (ctx instanceof LinnRuleContext) {
					// fetch info of rule
					final LinnRuleContext lctx = (LinnRuleContext) ctx;
					final String ruleName = lctx.name.getText().trim();
					Float ruleWeight = 1.0f;
					if (lctx.weight != null) {
						ruleWeight = Float
								.valueOf(lctx.weight.getText().trim());
					}
					// remember as extract
					ruleIdsOfRuleName.putIfAbsent(ruleName,
							Lists.<Integer> newArrayList());
					final List<Integer> ruleIds = ruleIdsOfRuleName
							.get(ruleName);
					ruleIds.add(this.currentRuleId);
					weightOfRuleId.put(this.currentRuleId, ruleWeight);
					System.out.println("LINN RULE: '" + ruleName + ", w("
							+ ruleWeight + ")'");
				} else {
					System.out.println("RULE ENTER: " + ctx.getRuleIndex());
				}
				// increment for next rule id
				this.currentRuleId++;
			}
		}, parseContext);
	}

}
