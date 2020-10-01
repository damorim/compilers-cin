// Generated from autogen/Grammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, NUMBER=13, IDENTIFIER=14, COMMENTBLOCK=15, 
		COMMENTLINE=16, WHITESPACE=17, FUNCTIONINT=18, FUNCTIONFLOAT=19, INT=20, 
		FLOAT=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "NUMBER", "IDENTIFIER", "COMMENTBLOCK", "COMMENTLINE", 
			"WHITESPACE", "FUNCTIONINT", "FUNCTIONFLOAT", "INT", "FLOAT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'='", "';'", "','", "'return'", "'+'", "'-'", "'*'", 
			"'/'", "'('", "')'", null, null, null, null, null, "'int'", "'float'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "NUMBER", "IDENTIFIER", "COMMENTBLOCK", "COMMENTLINE", "WHITESPACE", 
			"FUNCTIONINT", "FUNCTIONFLOAT", "INT", "FLOAT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u008d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\6\16L\n\16\r\16\16\16M\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\7\20V\n\20\f\20\16\20Y\13\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\7\21d\n\21\f\21\16\21g\13\21\3\21\3\21\3\21\3\21\3"+
		"\22\6\22n\n\22\r\22\16\22o\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\6\25\177\n\25\r\25\16\25\u0080\3\26\6\26\u0084\n"+
		"\26\r\26\16\26\u0085\3\26\3\26\6\26\u008a\n\26\r\26\16\26\u008b\4We\2"+
		"\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27\3\2\5\3\2\62;\3\2c|\5\2\13\f\17\17"+
		"\"\"\2\u0093\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2"+
		"\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3\2\2\2\r\67\3\2\2\2\17"+
		">\3\2\2\2\21@\3\2\2\2\23B\3\2\2\2\25D\3\2\2\2\27F\3\2\2\2\31H\3\2\2\2"+
		"\33K\3\2\2\2\35O\3\2\2\2\37Q\3\2\2\2!_\3\2\2\2#m\3\2\2\2%s\3\2\2\2\'w"+
		"\3\2\2\2)~\3\2\2\2+\u0083\3\2\2\2-.\7}\2\2.\4\3\2\2\2/\60\7\177\2\2\60"+
		"\6\3\2\2\2\61\62\7?\2\2\62\b\3\2\2\2\63\64\7=\2\2\64\n\3\2\2\2\65\66\7"+
		".\2\2\66\f\3\2\2\2\678\7t\2\289\7g\2\29:\7v\2\2:;\7w\2\2;<\7t\2\2<=\7"+
		"p\2\2=\16\3\2\2\2>?\7-\2\2?\20\3\2\2\2@A\7/\2\2A\22\3\2\2\2BC\7,\2\2C"+
		"\24\3\2\2\2DE\7\61\2\2E\26\3\2\2\2FG\7*\2\2G\30\3\2\2\2HI\7+\2\2I\32\3"+
		"\2\2\2JL\t\2\2\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\34\3\2\2\2O"+
		"P\t\3\2\2P\36\3\2\2\2QR\7\61\2\2RS\7,\2\2SW\3\2\2\2TV\13\2\2\2UT\3\2\2"+
		"\2VY\3\2\2\2WX\3\2\2\2WU\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7,\2\2[\\\7\61"+
		"\2\2\\]\3\2\2\2]^\b\20\2\2^ \3\2\2\2_`\7\61\2\2`a\7\61\2\2ae\3\2\2\2b"+
		"d\13\2\2\2cb\3\2\2\2dg\3\2\2\2ef\3\2\2\2ec\3\2\2\2fh\3\2\2\2ge\3\2\2\2"+
		"hi\7\f\2\2ij\3\2\2\2jk\b\21\2\2k\"\3\2\2\2ln\t\4\2\2ml\3\2\2\2no\3\2\2"+
		"\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\b\22\2\2r$\3\2\2\2st\7k\2\2tu\7p\2"+
		"\2uv\7v\2\2v&\3\2\2\2wx\7h\2\2xy\7n\2\2yz\7q\2\2z{\7c\2\2{|\7v\2\2|(\3"+
		"\2\2\2}\177\5\33\16\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081*\3\2\2\2\u0082\u0084\5\33\16\2\u0083\u0082\3\2\2"+
		"\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0089\7\60\2\2\u0088\u008a\5\33\16\2\u0089\u0088\3\2\2"+
		"\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c,"+
		"\3\2\2\2\n\2MWeo\u0080\u0085\u008b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}