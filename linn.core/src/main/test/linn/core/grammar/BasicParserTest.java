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
import org.antlr.v4.runtime.Token;
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
		CharStream inputStream = new ANTLRInputStream("linn name { H -> F F < > + - [ A B ]; }");
		LinnLexer lexer = new LinnLexer(inputStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		LinnParser parser = new LinnParser(tokenStream);
		LinnContext parseContext = parser.linn();
		// extracts from the L-system definition
		final Map<String, List<Integer>> ruleIdsOfRuleName = Maps.newHashMap();
		final Map<Integer, Float> weightOfRuleId = Maps.newHashMap();
		// visitor
		ParseTreeWalker parseTreeWalker = ParseTreeWalker.DEFAULT;
		parseTreeWalker.walk(new ParseTreeListener() {
			
			private int currentRuleId = 0;
			
			@Override
			public void visitTerminal(TerminalNode node) {
				System.out.println("TERMINAL: " + node.getText());
			}
			
			@Override
			public void visitErrorNode(ErrorNode node) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void exitEveryRule(ParserRuleContext ctx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enterEveryRule(ParserRuleContext ctx) {
				if(ctx instanceof LinnRuleContext) {
					// fetch info of rule
					LinnRuleContext lctx = (LinnRuleContext)ctx;
					String ruleName = lctx.name.getText().trim();
					Float ruleWeight = 1.0f;
					if(lctx.weight != null) {
						ruleWeight = Float.valueOf(lctx.weight.getText().trim());
					}
					// remember as extract
					ruleIdsOfRuleName.putIfAbsent(ruleName, Lists.<Integer>newArrayList());
					List<Integer> ruleIds = ruleIdsOfRuleName.get(ruleName);
					ruleIds.add(currentRuleId);
					weightOfRuleId.put(currentRuleId, ruleWeight);
					System.out.println("LINN RULE: '" + ruleName + ", w(" + ruleWeight + ")'");
				} else {
					System.out.println("RULE ENTER: " + ctx.getRuleIndex());
				}
				// increment for next rule id
				currentRuleId++;
			}
		}, parseContext);
	}

}
