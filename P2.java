// ****************************************************************************
// Erin Rasmussen
// rasmusse
// ejrasmussen2@wisc.edu
// Spring 2012
// CS 536
// ****************************************************************************


// This program is to be used to test the Little scanner.
// This version is set up to test all tokens, but more code is
// needed to test other aspects of the scanner (e.g., input that
// causes errors, character numbers, values associated with tokens).

import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

class P2 {
    public static void main(String[] args) throws IOException // may be thrown by yylex
    {
	// test all tokens
	System.out.println("testAllTokens");
	testAllTokens();
	CharNum.num = 1;
	System.out.println("testCharAndLineNum");
	testCharAndLineNum();
	CharNum.num = 1;
	System.out.println("eofCheck");
	eofCheck();
	CharNum.num = 1;
	System.out.println("checkErrors");
	checkErrors();
	CharNum.num = 1;
	// ADD CALLS TO OTHER TEST METHODS HERE
    }
    
    // **********************************************************************
    // checkErrors
    //       
    // open and read from file checkErrors
    //               
    // for each token read, write the corresponding string to checkErrors.out
    //                      
    // the input file contains many things that should cause errors. The
    // output terminal should be checked for things that are errors to make
    // sure the correct error message is displayed. The ouput file gets the
    // tokens that aren't errors. This should be a few because when something
    // is ignored, the following token could be legal.
    // **********************************************************************

    private static void checkErrors() throws IOException {
        FileReader inFile = null;
	PrintWriter outFile = null;
	try {
	   inFile = new FileReader("checkErrors");
	   outFile = new PrintWriter(new FileWriter("checkErrors.out"));
	} catch (FileNotFoundException ex) {
 	   System.err.println("File checkErrors not found.");
	   System.exit(-1);
	} catch (IOException ex) {
	   System.err.println("checkErrors.out cannot be opened.");
	   System.exit(-1);							
	   }
		Yylex scanner = new Yylex(inFile);
	   Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
	   token = scanner.next_token();
	  // if something that would probably be an error gets printed, the
	  // output file recieves a message of what it was. This could
	  // receive tokens after something else was deemed illegal
	  outFile.print("Something wasn't an error! ");
	   switch (token.sym) {
	    case sym.INT:
		outFile.println(((TokenVal)token.value).charnum + " "
		+ ((TokenVal)token.value).linenum + " int");
		break;
	    case sym.DBL:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " double");
		break;
	    case sym.VOID:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum +  " void");
		break;
	    case sym.IF:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " if");
		break;
	    case sym.ELSE:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " else");
		break;
	    case sym.WHILE:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " while");
		break;
	    case sym.RETURN:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " return");
		break;
	    case sym.SCANF:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " scanf");
		break;
	    case sym.PRINTF:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " printf");
		break;
	    case sym.ID:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " " 
		+ ((IdTokenVal)token.value).idVal);
		break;
	    case sym.INTLITERAL:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " " 
		+ ((IntLitTokenVal)token.value).intVal);
		break;
	    case sym.DBLLITERAL:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " " 
		+ ((DblLitTokenVal)token.value).dblVal);
		break;
	    case sym.STRINGLITERAL:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " " 
		+ ((StrLitTokenVal)token.value).strVal);
		break;
	    case sym.LCURLY:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " {");
		break;
	    case sym.RCURLY:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " }");
		break;
	    case sym.LPAREN:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " (");
		break;
	    case sym.RPAREN:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " )");
		break;
	    case sym.COMMA:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ,");
		break;
	    case sym.ASSIGN:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " =");
		break;
	    case sym.SEMICOLON:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ;");
		break;
	    case sym.PLUS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " +");
		break;
	    case sym.MINUS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " -");
		break;
	    case sym.STAR:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " *");
		break;
	    case sym.DIVIDE:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " /");
		break;
	    case sym.PLUSPLUS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ++");
		break;
	    case sym.MINUSMINUS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " --");
		break;
	    case sym.NOT:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " !");
		break;
	    case sym.AND:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " &&");
		break;
	    case sym.OR:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ||");
		break;
	    case sym.EQUALS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ==");
		break;
	    case sym.NOTEQUALS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " !=");
		break;
	    case sym.LESS:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " <");
		break;
	    case sym.GREATER:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " >");
		break;
	    case sym.LESSEQ:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " <=");
		break;
	    case sym.GREATEREQ:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " >=");
		break;
	    case sym.AMPERSAND:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " &");
		break;
	    case sym.INT_FORMAT:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " \"%d\"");
		break;
	    case sym.DBL_FORMAT:
		outFile.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " \"%f\"");
		break;
	    }
	}
	outFile.close();
    }


    // **********************************************************************
    // eofCheck
    //        
    // open and read from file eof
    //               
    // check that eof is reached and output message to eof.out
    //                   
    // if input file contains eof, then the output file gets a message,
    // if not, the output file is blank
    // 
    // **********************************************************************
    private static void eofCheck() throws IOException {
    	FileReader inFile = null;
    	PrintWriter outFile = null;
    	try {
    	    inFile = new FileReader("eof");
    	    outFile = new PrintWriter(new FileWriter("eof.out"));
	} catch (FileNotFoundException ex) {
    	    System.err.println("File eof not found.");
    	    System.exit(-1);
    	} catch (IOException ex) {
    	    System.err.println("eof.out cannot be opened.");
    	    System.exit(-1);
    	}
    	Yylex scanner = new Yylex(inFile);
    	Symbol token = scanner.next_token();
    	while (token.sym != sym.EOF) {
    		token = scanner.next_token();
		// iterate through
    	}
    	if (token.sym == sym.EOF){
    		outFile.println("EOF reached");
    	}
    	outFile.close();
    }
    
   
    // **********************************************************************
    //     testCharAndLineNum
    //      
    //     open and read from file inCharNum
    //        
    //     for each token read, write the corresponding string to inCharNum.out
    //     
    //     For each token, this method outputs the charnum and line number
    //     to the output file. The output charnum's and line numbers can be
    //     compared to the input file for discrepencies. This differs from
    //     the changes to testAllTokens because the input file mixes chars
    //     on a line to check that CharNum gets updated correctly.
    // **********************************************************************
   private static void testCharAndLineNum() throws IOException {
    	// open input and output files
    	FileReader inFile = null;
    	PrintWriter outFile = null;
    	try {
    	    inFile = new FileReader("inCharNum");
    	    outFile = new PrintWriter(new FileWriter("inCharNum.out"));
    	} catch (FileNotFoundException ex) {
    	    System.err.println("File inCharNum not found.");
    	    System.exit(-1);
    	} catch (IOException ex) {
    	    System.err.println("inCharNum.out cannot be opened.");
    	    System.exit(-1);
    	}
    	// create and call the scanner
    	Yylex scanner = new Yylex(inFile);
    	Symbol token = scanner.next_token();
    	while (token.sym != sym.EOF) {
    	    outFile.println(((TokenVal)token.value).charnum + "(" 
	    + ((TokenVal)token.value).linenum + ")    sym # : " + token.sym);
    	    token = scanner.next_token();
    	}
    	outFile.close();
    	
    }
    
   
    // **********************************************************************
    // testAllTokens
    //
    // open and read from file inTokens
    //
    // for each token read, write the corresponding string to inTokens.out
    // and also to allCharNum.out
    //
    // if the input file contains all tokens, one per line, we can verify
    // correctness of the scanner by comparing the input and output files.
    // In addition, the allCharNum.out file receives the charnum and line
    // numbers of each token. CharNum should always be 1 (except comments)
    // and line number should always increase by 1.
    // **********************************************************************
    private static void testAllTokens() throws IOException {
	// open input and output files
	FileReader inFile = null;
	PrintWriter outFile = null;
	PrintWriter outFile2 = null;
	try {
	    inFile = new FileReader("inTokens");
	    outFile = new PrintWriter(new FileWriter("inTokens.out"));
	    outFile2 = new PrintWriter(new FileWriter("allCharNum.out"));
	} catch (FileNotFoundException ex) {
	    System.err.println("File inTokens not found.");
	    System.exit(-1);
	} catch (IOException ex) {
	    System.err.println("inTokens.out cannot be opened.");
	    System.err.println("allCharNum.out cannot be opened.");
	    System.exit(-1);
	}
	// create and call the scanner
	Yylex scanner = new Yylex(inFile);
	Symbol token = scanner.next_token();
	while (token.sym != sym.EOF) {
	    switch (token.sym) {
	    case sym.INT:
		outFile.println("int");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " int");
		break;
	    case sym.DBL:
		outFile.println("double");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " double");
		break;
	    case sym.VOID:
		outFile.println("void");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum +  " void");
		break;
	    case sym.IF:
		outFile.println("if");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " if");
		break;
	    case sym.ELSE:
		outFile.println("else");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " else");
		break;
	    case sym.WHILE:
		outFile.println("while");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " while");
		break;
	    case sym.RETURN:
		outFile.println("return");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " return");
		break;
	    case sym.SCANF:
		outFile.println("scanf");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " scanf");
		break;
	    case sym.PRINTF:
		outFile.println("printf");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " printf");
		break;
	    case sym.ID:
		outFile.println(((IdTokenVal)token.value).idVal);
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ID");
		break;
	    case sym.INTLITERAL:
		outFile.println(((IntLitTokenVal)token.value).intVal);
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " int literal");
		break;
	    case sym.DBLLITERAL:
		outFile.println(((DblLitTokenVal)token.value).dblVal);
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " dbl literal");
		break;
	    case sym.STRINGLITERAL:
		outFile.println(((StrLitTokenVal)token.value).strVal);
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " str literal");
		break;
	    case sym.LCURLY:
		outFile.println("{");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " {");
		break;
	    case sym.RCURLY:
		outFile.println("}");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " }");
		break;
	    case sym.LPAREN:
		outFile.println("(");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " (");
		break;
	    case sym.RPAREN:
		outFile.println(")");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " )");
		break;
	    case sym.COMMA:
		outFile.println(",");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ,");
		break;
	    case sym.ASSIGN:
		outFile.println("=");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " =");
		break;
	    case sym.SEMICOLON:
		outFile.println(";");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ;");
		break;
	    case sym.PLUS:
		outFile.println("+");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " +");
		break;
	    case sym.MINUS:
		outFile.println("-");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " -");
		break;
	    case sym.STAR:
		outFile.println("*");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " *");
		break;
	    case sym.DIVIDE:
		outFile.println("/");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " /");
		break;
	    case sym.PLUSPLUS:
		outFile.println("++");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ++");
		break;
	    case sym.MINUSMINUS:
		outFile.println("--");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " --");
		break;
	    case sym.NOT:
		outFile.println("!");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " !");
		break;
	    case sym.AND:
		outFile.println("&&");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " &&");
		break;
	    case sym.OR:
		outFile.println("||");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ||");
		break;
	    case sym.EQUALS:
		outFile.println("==");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " ==");
		break;
	    case sym.NOTEQUALS:
		outFile.println("!=");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " !=");
		break;
	    case sym.LESS:
		outFile.println("<");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " <");
		break;
	    case sym.GREATER:
		outFile.println(">");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " >");
		break;
	    case sym.LESSEQ:
		outFile.println("<=");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " <=");
		break;
	    case sym.GREATEREQ:
		outFile.println(">=");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " >=");
		break;
	    case sym.AMPERSAND:
		outFile.println("&");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " &");
		break;
	    case sym.INT_FORMAT:
		outFile.println("\"%d\"");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " \"%d\"");
		break;
	    case sym.DBL_FORMAT:
		outFile.println("\"%f\"");
		outFile2.println(((TokenVal)token.value).charnum + " " 
		+ ((TokenVal)token.value).linenum + " \"%f\"");
		break;
	    }

	    token = scanner.next_token();
	}
	outFile.close();
	outFile2.close();
    }
}
