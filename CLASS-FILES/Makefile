###
# This Makefile can be used to make a scanner for the Little language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g
CLASSPATH = -classpath  .:JLex.jar:java_cup.jar

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

###
# clean up
###

clean:
	rm -f *~ *.class Little.jlex.java
