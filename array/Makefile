
program: Test.java ArrayInitParser.java ShortToUnicodeString.java
	javac ./*.java

ArrayInitParser.java: ArrayInit.g4
	java -jar /Users/will/libs/antlr4/antlr-4.9.2-complete.jar $^

.PHONY: clean
clean:
	rm -f ArrayInit.interp ArrayInit.tokens ArrayInitBaseListener.java ArrayInitLexer.interp \
		ArrayInitLexer.java ArrayInitLexer.tokens ArrayInitListener.java \
		ArrayInitParser.java *.class

