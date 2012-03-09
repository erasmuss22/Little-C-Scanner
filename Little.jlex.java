import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
 // fields
    int linenum;
    int charnum;
 // constructor
    TokenVal(int l, int c) {
        linenum = l;
	charnum = c;
    }
}
class IntLitTokenVal extends TokenVal {
 // new field: the value of the integer literal
    int intVal;
 // constructor
    IntLitTokenVal(int l, int c, int val) {
        super(l,c);
	intVal = val;
    }
}
class DblLitTokenVal extends TokenVal {
 // new field: the value of the double literal
    double dblVal;
 // constructor
    DblLitTokenVal(int l, int c, double val) {
        super(l,c);
	dblVal = val;
    }
}
class IdTokenVal extends TokenVal {
 // new field: the value of the identifier
    String idVal;
 // constructor
    IdTokenVal(int l, int c, String val) {
        super(l,c);
	idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
 // new field: the value of the string literal
    String strVal;
 // constructor
    StrLitTokenVal(int l, int c, String val) {
        super(l,c);
	strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
  static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

   private int comment_count = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		50
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"51:9,3,2,51:2,28,51:18,3,47,25,51:2,50,48,27,39,40,45,4,41,44,24,46,1:10,51" +
",43,29,42,36,51:2,23:4,33,23:7,35,31,23:3,34,23,32,30,23:5,51,26,51:2,23,51" +
",21,11,20,8,13,15,23,18,5,23:2,12,23,6,9,22,23,19,16,7,10,14,17,23:3,37,49," +
"38,51:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,120,
"0,1,2,1,3,4,5,6,7,8,9,1:5,10,1,11,1,12,13,14,15,1,16,6,1:9,15,16,17,18,16:2" +
",1,18,16:5,18,19,1:3,20,21,22,16,1,23,24,17,25,26,27,28,29,30:2,31,32,33,34" +
",35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,49,39,51,52,53,54,55,56,57" +
",58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,18:2")[0];

	private int yy_nxt[][] = unpackFromString(78,52,
"1,2,3,4,5,6,57:2,112,57:4,98,99,57,106,107,57,113,57:2,114,57,7,8,58:2,-1,9" +
",57:6,10,11,12,13,14,15,16,17,18,19,20,21,22,65,58:2,-1:53,2,-1:22,23,-1:30" +
",4,-1:52,24,-1:48,57,-1:3,57,64,57:8,25,57:8,-1:6,57:6,-1:17,26,-1:51,119,-" +
"1,119:22,27,59,119:2,116,119:20,117,119,-1:42,28,-1:51,29,-1:51,30,-1:53,31" +
",-1:52,32,-1:48,33,-1:57,34,-1:4,36,-1:51,57,-1:3,57:19,-1:6,57:6,-1:17,38," +
"-1,38:22,42,60,38:25,-1,119,-1,119:22,27,59,119:25,1,55,3,56,62:41,54,63,62" +
":5,-1,62,-1,62:42,69,52,62:5,-1,55,51,62:42,70,75,62:5,-1,62,51,56,62:41,70" +
",75,62:5,-1,38,87,90,38:2,119:2,38:17,39,66,119,38:24,-1,38,87,77,38:21,61," +
"60,38:25,-1,62,51,62:42,70,75,62:5,-1,62,-1,62:42,53,74,62:5,-1,57,-1:3,57:" +
"2,37,57:16,-1:6,57:6,-1:65,35,-1:3,38,87,90,38:2,119:2,38:17,43,66,119,38:2" +
"4,-1,67,-1,67:22,42,79,67:25,-1,62,51,62:42,69,75,62:5,-1,62,-1,62:42,69,-1" +
",62:5,-1,57,-1:3,57:8,40,57:10,-1:6,57:6,-1:17,119,-1,119:22,27,59,119:9,49" +
",119:15,-1,89,87,81,89:2,67:2,89:17,68,79,67,89:24,-1,62,51,62:42,70,74,62:" +
"5,-1,62,-1,62:42,-1,74,62:5,-1,57,-1:3,57:3,41,57:15,-1:6,57:6,-1:17,38,-1," +
"77,38:21,42,60,38:25,-1,57,-1:3,57:10,44,57:8,-1:6,57:6,-1:17,67,-1,83,67:2" +
"1,68,79,67:25,-1,57,-1:3,57:8,45,57:10,-1:6,57:6,-1:17,67,-1,83,67:21,42,73" +
",67:25,-1,57,-1:3,57:8,46,57:10,-1:6,57:6,-1:17,67,-1,83,67:21,42,79,67:25," +
"-1,57,-1:3,57,47,57:17,-1:6,57:6,-1:17,57,-1:3,57:10,48,57:8,-1:6,57:6,-1:1" +
"7,119,-1,119:22,27,59,119:8,72,119:16,-1,67,-1,67:22,42,73,67:25,-1,57,-1:3" +
",57:11,71,57:7,-1:6,57:6,-1:17,57,-1:3,76,57:18,-1:6,57:6,-1:17,57,-1:3,57," +
"78,57:17,-1:6,57:6,-1:17,57,-1:3,57:7,80,57:11,-1:6,57:6,-1:17,57,-1:3,57:7" +
",82,57:11,-1:6,57:6,-1:17,57,-1:3,57:14,84,57:4,-1:6,57:6,-1:17,57,-1:3,57:" +
"2,85,57:16,-1:6,57:6,-1:17,119,-1,119:22,27,59,119:7,86,119:17,-1,57,-1:3,5" +
"7:7,88,57:11,-1:6,57:6,-1:17,57,-1:3,57:4,91,57:14,-1:6,57:6,-1:17,57,-1:3," +
"57:16,92,57:2,-1:6,57:6,-1:17,57,-1:3,93,57:18,-1:6,57:6,-1:17,57,-1:3,57:6" +
",94,57:12,-1:6,57:6,-1:17,57,-1:3,57:5,95,57:13,-1:6,57:6,-1:17,57,-1:3,57," +
"96,57:17,-1:6,57:6,-1:17,119,-1,119:22,27,59,119:6,97,119:18,-1,57,-1:3,57:" +
"15,100,57:3,-1:6,57:6,-1:17,57,-1:3,57:13,101,57:5,-1:6,57:6,-1:17,57,-1:3," +
"57:5,102,57:13,-1:6,57:6,-1:17,57,-1:3,57:2,103,57:16,-1:6,57:6,-1:17,57,-1" +
":3,104,57:18,-1:6,57:6,-1:17,119,-1,119:22,27,59,119:5,105,119:19,-1,57,-1:" +
"3,57:4,108,57:14,-1:6,57:6,-1:17,57,-1:3,57:8,109,57:10,-1:6,57:6,-1:17,57," +
"-1:3,57:14,110,57:4,-1:6,57:6,-1:17,119,-1,119:22,27,59,119:4,111,119:20,-1" +
",119,-1,119:22,27,59,119:3,115,119:21,-1,119,-1,119:5,118,119:6,118,119:9,2" +
"7,59,119:25");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{// NOTE: the following computation of the integer value does NOT
	    //       check for overflow.  This must be changed.
	    double d = (new Double(yytext())).doubleValue();
	    if (d > Integer.MAX_VALUE){
	    	Errors.warn(yyline+1, CharNum.num,
			 		"integer literal too large; using max value " + yytext());
	    			for (int i = 0; i < Integer.MAX_VALUE / 10; i++){
	    				CharNum.num++;
	    			}
	    			Symbol S = new Symbol(sym.INTLITERAL,
			          new IntLitTokenVal(yyline+1, CharNum.num, Integer.MAX_VALUE));
			          return S;
	    }
	    else{
	    int val = (new Integer(yytext())).intValue();
	    Symbol S = new Symbol(sym.INTLITERAL,
			          new IntLitTokenVal(yyline+1, CharNum.num, val)
				 );
	    CharNum.num += yytext().length();
	    return S;
	    }
	   }
					case -3:
						break;
					case 3:
						{CharNum.num = 1;}
					case -4:
						break;
					case 4:
						{CharNum.num += yytext().length();}
					case -5:
						break;
					case 5:
						{Symbol S = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -6:
						break;
					case 6:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -7:
						break;
					case 7:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring illegal character: " + yytext());
	    			CharNum.num++;
	   				}
					case -8:
						break;
					case 8:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -9:
						break;
					case 9:
						{Symbol S = new Symbol(sym.LESS, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -10:
						break;
					case 10:
						{Symbol S = new Symbol(sym.GREATER, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -11:
						break;
					case 11:
						{Symbol S = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -12:
						break;
					case 12:
						{Symbol S = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -13:
						break;
					case 13:
						{Symbol S = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -14:
						break;
					case 14:
						{Symbol S = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -15:
						break;
					case 15:
						{Symbol S = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   			}
					case -16:
						break;
					case 16:
						{Symbol S = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -17:
						break;
					case 17:
						{Symbol S = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -18:
						break;
					case 18:
						{Symbol S = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -19:
						break;
					case 19:
						{Symbol S = new Symbol(sym.STAR, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -20:
						break;
					case 20:
						{Symbol S = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
       				}
					case -21:
						break;
					case 21:
						{Symbol S = new Symbol(sym.NOT, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -22:
						break;
					case 22:
						{Symbol S = new Symbol(sym.AMPERSAND, new TokenVal(yyline+1, CharNum.num));
	    			CharNum.num++;
	    			return S;
	   				}
					case -23:
						break;
					case 23:
						{Symbol S = new Symbol(sym.DBLLITERAL, new 
				DblLitTokenVal(yyline+1, CharNum.num, (new Double(yytext())).doubleValue()));
					for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
					return S;
					}
					case -24:
						break;
					case 24:
						{Symbol S = new Symbol(sym.PLUSPLUS, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -25:
						break;
					case 25:
						{Symbol S = new Symbol(sym.IF, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   			}
					case -26:
						break;
					case 26:
						{Symbol S = new Symbol(sym.DBLLITERAL, new 
				DblLitTokenVal(yyline+1, CharNum.num, (new Double(yytext())).doubleValue()));
					for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
					return S;
					}
					case -27:
						break;
					case 27:
						{Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	   			 return S;
	  		    }
					case -28:
						break;
					case 28:
						{Symbol S = new Symbol(sym.LESSEQ, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -29:
						break;
					case 29:
						{Symbol S = new Symbol(sym.GREATEREQ, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -30:
						break;
					case 30:
						{Symbol S = new Symbol(sym.EQUALS, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -31:
						break;
					case 31:
						{Symbol S = new Symbol(sym.MINUSMINUS, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -32:
						break;
					case 32:
						{ yybegin(COMMENT); comment_count = comment_count + 1;
		   CharNum.num += 2; }
					case -33:
						break;
					case 33:
						{Symbol S = new Symbol(sym.NOTEQUALS, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -34:
						break;
					case 34:
						{Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -35:
						break;
					case 35:
						{Symbol S = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 2; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -36:
						break;
					case 36:
						{Symbol S = new Symbol(sym.DBLLITERAL, new 
				DblLitTokenVal(yyline+1, CharNum.num, (new Double(yytext())).doubleValue()));
					for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
					return S;
					}
					case -37:
						break;
					case 37:
						{Symbol S = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 3; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -38:
						break;
					case 38:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -39:
						break;
					case 39:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -40:
						break;
					case 40:
						{Symbol S = new Symbol(sym.ELSE, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 4; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -41:
						break;
					case 41:
						{Symbol S = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 4; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -42:
						break;
					case 42:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -43:
						break;
					case 43:
						{Symbol S = new Symbol(sym.STRINGLITERAL, new StrLitTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	   			 return S;
	  		    }
					case -44:
						break;
					case 44:
						{Symbol S = new Symbol(sym.SCANF, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 5; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -45:
						break;
					case 45:
						{Symbol S = new Symbol(sym.WHILE, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 5; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -46:
						break;
					case 46:
						{Symbol S = new Symbol(sym.DBL, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 6; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -47:
						break;
					case 47:
						{Symbol S = new Symbol(sym.RETURN, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 6; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -48:
						break;
					case 48:
						{Symbol S = new Symbol(sym.PRINTF, new TokenVal(yyline+1, CharNum.num));
	    			for (int i = 0; i < 6; i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -49:
						break;
					case 49:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -50:
						break;
					case 50:
						{ CharNum.num += yytext().length(); }
					case -51:
						break;
					case 51:
						{ CharNum.num += yytext().length();
		   Errors.fatal(yyline+1, CharNum.num,
			 		"unterminated comment");
		  yybegin(YYINITIAL);
		 }
					case -52:
						break;
					case 52:
						{ comment_count = comment_count - 1;
		   CharNum.num += yytext().length();
		  if (comment_count == 0) {
			yybegin(YYINITIAL);
		  }
	         }
					case -53:
						break;
					case 53:
						{ comment_count = comment_count + 1;
		   CharNum.num += 2; }
					case -54:
						break;
					case 55:
						{// NOTE: the following computation of the integer value does NOT
	    //       check for overflow.  This must be changed.
	    double d = (new Double(yytext())).doubleValue();
	    if (d > Integer.MAX_VALUE){
	    	Errors.warn(yyline+1, CharNum.num,
			 		"integer literal too large; using max value " + yytext());
	    			for (int i = 0; i < Integer.MAX_VALUE / 10; i++){
	    				CharNum.num++;
	    			}
	    			Symbol S = new Symbol(sym.INTLITERAL,
			          new IntLitTokenVal(yyline+1, CharNum.num, Integer.MAX_VALUE));
			          return S;
	    }
	    else{
	    int val = (new Integer(yytext())).intValue();
	    Symbol S = new Symbol(sym.INTLITERAL,
			          new IntLitTokenVal(yyline+1, CharNum.num, val)
				 );
	    CharNum.num += yytext().length();
	    return S;
	    }
	   }
					case -55:
						break;
					case 56:
						{CharNum.num += yytext().length();}
					case -56:
						break;
					case 57:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -57:
						break;
					case 58:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring illegal character: " + yytext());
	    			CharNum.num++;
	   				}
					case -58:
						break;
					case 59:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -59:
						break;
					case 60:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -60:
						break;
					case 61:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -61:
						break;
					case 62:
						{ CharNum.num += yytext().length(); }
					case -62:
						break;
					case 64:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -63:
						break;
					case 65:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring illegal character: " + yytext());
	    			CharNum.num++;
	   				}
					case -64:
						break;
					case 66:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -65:
						break;
					case 67:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -66:
						break;
					case 68:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -67:
						break;
					case 69:
						{ CharNum.num += yytext().length(); }
					case -68:
						break;
					case 71:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -69:
						break;
					case 72:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -70:
						break;
					case 73:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -71:
						break;
					case 74:
						{ CharNum.num += yytext().length(); }
					case -72:
						break;
					case 76:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -73:
						break;
					case 77:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -74:
						break;
					case 78:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -75:
						break;
					case 79:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -76:
						break;
					case 80:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -77:
						break;
					case 81:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -78:
						break;
					case 82:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -79:
						break;
					case 83:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -80:
						break;
					case 84:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -81:
						break;
					case 85:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -82:
						break;
					case 86:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -83:
						break;
					case 87:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -84:
						break;
					case 88:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -85:
						break;
					case 89:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -86:
						break;
					case 90:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal with bad escaped character");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -87:
						break;
					case 91:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -88:
						break;
					case 92:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -89:
						break;
					case 93:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -90:
						break;
					case 94:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -91:
						break;
					case 95:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -92:
						break;
					case 96:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -93:
						break;
					case 97:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -94:
						break;
					case 98:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -95:
						break;
					case 99:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -96:
						break;
					case 100:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -97:
						break;
					case 101:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -98:
						break;
					case 102:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -99:
						break;
					case 103:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -100:
						break;
					case 104:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -101:
						break;
					case 105:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -102:
						break;
					case 106:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -103:
						break;
					case 107:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -104:
						break;
					case 108:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -105:
						break;
					case 109:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -106:
						break;
					case 110:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -107:
						break;
					case 111:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -108:
						break;
					case 112:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -109:
						break;
					case 113:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -110:
						break;
					case 114:
						{Symbol S = new Symbol(sym.ID, 
					new IdTokenVal(yyline+1, CharNum.num, yytext()));
	    			for (int i = 0; i < yytext().length(); i++){
	    				CharNum.num++;
	    			}
	    			return S;
	   				}
					case -111:
						break;
					case 115:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -112:
						break;
					case 116:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -113:
						break;
					case 117:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -114:
						break;
					case 118:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -115:
						break;
					case 119:
						{Errors.fatal(yyline+1, CharNum.num,
			 		"ignoring unterminated string literal");
	    			for (int i = 0; i < yytext().length(); i++) {
				   CharNum.num++;
				}
	  		    }
					case -116:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
