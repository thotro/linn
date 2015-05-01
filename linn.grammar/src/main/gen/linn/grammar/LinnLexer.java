// Generated from Linn.g4 by ANTLR 4.5

    package linn.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LinnLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, FLOAT=13, ID=14, PROD=15, WS=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "LETTER", "DIGIT", "INT", "FLOAT", "ID", "PROD", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'linn'", "'('", "')'", "'{'", "'}'", "'--'", "'->'", "'['", "']'", 
		"';'", "','", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "FLOAT", "ID", "PROD", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LinnLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Linn.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22e\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\6\20L\n\20\r\20\16\20M\3\21\3\21\3\21\5\21S\n"+
		"\21\3\22\3\22\3\22\7\22X\n\22\f\22\16\22[\13\22\3\23\3\23\3\24\6\24`\n"+
		"\24\r\24\16\24a\3\24\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\2\35\2\37\2!\17#\20%\21\'\22\3\2\6\5\2C\\aac|\3"+
		"\2\62;\n\2((--//\61\61>>@@^^~~\5\2\13\f\17\17\"\"f\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5.\3\2\2\2\7\60\3\2\2"+
		"\2\t\62\3\2\2\2\13\64\3\2\2\2\r\66\3\2\2\2\179\3\2\2\2\21<\3\2\2\2\23"+
		">\3\2\2\2\25@\3\2\2\2\27B\3\2\2\2\31D\3\2\2\2\33F\3\2\2\2\35H\3\2\2\2"+
		"\37K\3\2\2\2!O\3\2\2\2#T\3\2\2\2%\\\3\2\2\2\'_\3\2\2\2)*\7n\2\2*+\7k\2"+
		"\2+,\7p\2\2,-\7p\2\2-\4\3\2\2\2./\7*\2\2/\6\3\2\2\2\60\61\7+\2\2\61\b"+
		"\3\2\2\2\62\63\7}\2\2\63\n\3\2\2\2\64\65\7\177\2\2\65\f\3\2\2\2\66\67"+
		"\7/\2\2\678\7/\2\28\16\3\2\2\29:\7/\2\2:;\7@\2\2;\20\3\2\2\2<=\7]\2\2"+
		"=\22\3\2\2\2>?\7_\2\2?\24\3\2\2\2@A\7=\2\2A\26\3\2\2\2BC\7.\2\2C\30\3"+
		"\2\2\2DE\7?\2\2E\32\3\2\2\2FG\t\2\2\2G\34\3\2\2\2HI\t\3\2\2I\36\3\2\2"+
		"\2JL\5\35\17\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N \3\2\2\2OR\5\37"+
		"\20\2PQ\7\60\2\2QS\5\37\20\2RP\3\2\2\2RS\3\2\2\2S\"\3\2\2\2TY\5\33\16"+
		"\2UX\5\33\16\2VX\5\35\17\2WU\3\2\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3"+
		"\2\2\2Z$\3\2\2\2[Y\3\2\2\2\\]\t\4\2\2]&\3\2\2\2^`\t\5\2\2_^\3\2\2\2`a"+
		"\3\2\2\2a_\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\b\24\2\2d(\3\2\2\2\b\2MRWYa\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}