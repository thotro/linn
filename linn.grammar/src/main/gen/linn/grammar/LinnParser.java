// Generated from Linn.g4 by ANTLR 4.5

    package linn.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LinnParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, FLOAT=13, ID=14, PROD=15, WS=16;
	public static final int
		RULE_linn = 0, RULE_linnRule = 1, RULE_linnProduction = 2, RULE_params = 3, 
		RULE_keyValue = 4;
	public static final String[] ruleNames = {
		"linn", "linnRule", "linnProduction", "params", "keyValue"
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

	@Override
	public String getGrammarFileName() { return "Linn.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LinnParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LinnContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(LinnParser.ID, 0); }
		public KeyValueContext keyValue() {
			return getRuleContext(KeyValueContext.class,0);
		}
		public List<LinnRuleContext> linnRule() {
			return getRuleContexts(LinnRuleContext.class);
		}
		public LinnRuleContext linnRule(int i) {
			return getRuleContext(LinnRuleContext.class,i);
		}
		public LinnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).enterLinn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).exitLinn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LinnVisitor ) return ((LinnVisitor<? extends T>)visitor).visitLinn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinnContext linn() throws RecognitionException {
		LinnContext _localctx = new LinnContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_linn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(T__0);
			setState(11);
			((LinnContext)_localctx).name = match(ID);
			setState(16);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(12);
				match(T__1);
				setState(13);
				keyValue();
				setState(14);
				match(T__2);
				}
			}

			setState(18);
			match(T__3);
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				linnRule();
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(24);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinnRuleContext extends ParserRuleContext {
		public Token name;
		public Token weight;
		public TerminalNode ID() { return getToken(LinnParser.ID, 0); }
		public TerminalNode FLOAT() { return getToken(LinnParser.FLOAT, 0); }
		public List<LinnProductionContext> linnProduction() {
			return getRuleContexts(LinnProductionContext.class);
		}
		public LinnProductionContext linnProduction(int i) {
			return getRuleContext(LinnProductionContext.class,i);
		}
		public LinnRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linnRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).enterLinnRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).exitLinnRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LinnVisitor ) return ((LinnVisitor<? extends T>)visitor).visitLinnRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinnRuleContext linnRule() throws RecognitionException {
		LinnRuleContext _localctx = new LinnRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_linnRule);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			((LinnRuleContext)_localctx).name = match(ID);
			setState(29);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(27);
				match(T__5);
				setState(28);
				((LinnRuleContext)_localctx).weight = match(FLOAT);
				}
			}

			setState(31);
			match(T__6);
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(46);
				switch (_input.LA(1)) {
				case ID:
				case PROD:
					{
					setState(33); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(32);
							linnProduction();
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(35); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case T__7:
					{
					setState(37);
					match(T__7);

							System.out.println("Branching");
						
					setState(40); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(39);
						linnProduction();
						}
						}
						setState(42); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==ID || _la==PROD );
					setState(44);
					match(T__8);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << ID) | (1L << PROD))) != 0) );
			setState(50);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinnProductionContext extends ParserRuleContext {
		public Token ID;
		public Token PROD;
		public List<TerminalNode> ID() { return getTokens(LinnParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LinnParser.ID, i);
		}
		public List<TerminalNode> PROD() { return getTokens(LinnParser.PROD); }
		public TerminalNode PROD(int i) {
			return getToken(LinnParser.PROD, i);
		}
		public LinnProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linnProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).enterLinnProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).exitLinnProduction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LinnVisitor ) return ((LinnVisitor<? extends T>)visitor).visitLinnProduction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinnProductionContext linnProduction() throws RecognitionException {
		LinnProductionContext _localctx = new LinnProductionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_linnProduction);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(56);
					switch (_input.LA(1)) {
					case ID:
						{
						setState(52);
						((LinnProductionContext)_localctx).ID = match(ID);

								System.out.println((((LinnProductionContext)_localctx).ID!=null?((LinnProductionContext)_localctx).ID.getText():null));
							
						}
						break;
					case PROD:
						{
						setState(54);
						((LinnProductionContext)_localctx).PROD = match(PROD);

								System.out.println((((LinnProductionContext)_localctx).PROD!=null?((LinnProductionContext)_localctx).PROD.getText():null));
							
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(58); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LinnParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LinnParser.ID, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LinnVisitor ) return ((LinnVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ID);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(61);
				match(T__10);
				setState(62);
				match(ID);
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyValueContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LinnParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LinnParser.ID, i);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).enterKeyValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinnListener ) ((LinnListener)listener).exitKeyValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LinnVisitor ) return ((LinnVisitor<? extends T>)visitor).visitKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_keyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(ID);
			setState(69);
			match(T__11);
			setState(70);
			match(ID);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(71);
				match(T__10);
				setState(72);
				match(ID);
				setState(73);
				match(T__11);
				setState(74);
				match(ID);
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22S\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\5\2\23\n\2\3\2\3\2"+
		"\6\2\27\n\2\r\2\16\2\30\3\2\3\2\3\3\3\3\3\3\5\3 \n\3\3\3\3\3\6\3$\n\3"+
		"\r\3\16\3%\3\3\3\3\3\3\6\3+\n\3\r\3\16\3,\3\3\3\3\6\3\61\n\3\r\3\16\3"+
		"\62\3\3\3\3\3\4\3\4\3\4\3\4\6\4;\n\4\r\4\16\4<\3\5\3\5\3\5\7\5B\n\5\f"+
		"\5\16\5E\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6N\n\6\f\6\16\6Q\13\6\3\6"+
		"\2\2\7\2\4\6\b\n\2\2X\2\f\3\2\2\2\4\34\3\2\2\2\6:\3\2\2\2\b>\3\2\2\2\n"+
		"F\3\2\2\2\f\r\7\3\2\2\r\22\7\20\2\2\16\17\7\4\2\2\17\20\5\n\6\2\20\21"+
		"\7\5\2\2\21\23\3\2\2\2\22\16\3\2\2\2\22\23\3\2\2\2\23\24\3\2\2\2\24\26"+
		"\7\6\2\2\25\27\5\4\3\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30\31"+
		"\3\2\2\2\31\32\3\2\2\2\32\33\7\7\2\2\33\3\3\2\2\2\34\37\7\20\2\2\35\36"+
		"\7\b\2\2\36 \7\17\2\2\37\35\3\2\2\2\37 \3\2\2\2 !\3\2\2\2!\60\7\t\2\2"+
		"\"$\5\6\4\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\61\3\2\2\2\'(\7"+
		"\n\2\2(*\b\3\1\2)+\5\6\4\2*)\3\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-.\3"+
		"\2\2\2./\7\13\2\2/\61\3\2\2\2\60#\3\2\2\2\60\'\3\2\2\2\61\62\3\2\2\2\62"+
		"\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\f\2\2\65\5\3\2\2\2\66"+
		"\67\7\20\2\2\67;\b\4\1\289\7\21\2\29;\b\4\1\2:\66\3\2\2\2:8\3\2\2\2;<"+
		"\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\7\3\2\2\2>C\7\20\2\2?@\7\r\2\2@B\7\20\2"+
		"\2A?\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\t\3\2\2\2EC\3\2\2\2FG\7\20"+
		"\2\2GH\7\16\2\2HO\7\20\2\2IJ\7\r\2\2JK\7\20\2\2KL\7\16\2\2LN\7\20\2\2"+
		"MI\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\13\3\2\2\2QO\3\2\2\2\r\22\30"+
		"\37%,\60\62:<CO";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}