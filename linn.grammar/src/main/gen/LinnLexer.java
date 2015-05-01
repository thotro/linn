// Generated from LinnLexer.g4 by ANTLR 4.5
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
		FLOAT=1, ID=2, WS=3, MOVE=4, ROTATE=5;
	public static final int PROD = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "PROD"
	};

	public static final String[] ruleNames = {
		"LETTER", "DIGIT", "INT", "FLOAT", "ID", "WS", "MOVE", "ROTATE"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FLOAT", "ID", "WS", "MOVE", "ROTATE"
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
	public String getGrammarFileName() { return "LinnLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\7\65\b\1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3"+
		"\3\3\3\3\4\6\4\32\n\4\r\4\16\4\33\3\5\3\5\3\5\5\5!\n\5\3\6\3\6\3\6\7\6"+
		"&\n\6\f\6\16\6)\13\6\3\7\6\7,\n\7\r\7\16\7-\3\7\3\7\3\b\3\b\3\t\3\t\2"+
		"\2\n\4\2\6\2\b\2\n\3\f\4\16\5\20\6\22\7\4\2\3\7\5\2C\\aac|\3\2\62;\5\2"+
		"\13\f\17\17\"\"\4\2HHhh\4\2>>@@\65\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2"+
		"\2\3\20\3\2\2\2\3\22\3\2\2\2\4\24\3\2\2\2\6\26\3\2\2\2\b\31\3\2\2\2\n"+
		"\35\3\2\2\2\f\"\3\2\2\2\16+\3\2\2\2\20\61\3\2\2\2\22\63\3\2\2\2\24\25"+
		"\t\2\2\2\25\5\3\2\2\2\26\27\t\3\2\2\27\7\3\2\2\2\30\32\5\6\3\2\31\30\3"+
		"\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\t\3\2\2\2\35 \5\b"+
		"\4\2\36\37\7\60\2\2\37!\5\b\4\2 \36\3\2\2\2 !\3\2\2\2!\13\3\2\2\2\"\'"+
		"\5\4\2\2#&\5\4\2\2$&\5\6\3\2%#\3\2\2\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2"+
		"\'(\3\2\2\2(\r\3\2\2\2)\'\3\2\2\2*,\t\4\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2"+
		"\2\2-.\3\2\2\2./\3\2\2\2/\60\b\7\2\2\60\17\3\2\2\2\61\62\t\5\2\2\62\21"+
		"\3\2\2\2\63\64\t\6\2\2\64\23\3\2\2\2\t\2\3\33 %\'-\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}