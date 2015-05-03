package linn.core.grammar;

import linn.grammar.LinnLexer;
import linn.grammar.LinnParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class BasicParserTest {

	@Test
	public void test() {
		CharStream inputStream = new ANTLRInputStream("linn name { H -> F F < > + - [ A B ]; }");
		LinnLexer lexer = new LinnLexer(inputStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		LinnParser parser = new LinnParser(tokenStream);
		parser.linn();
	}

}
