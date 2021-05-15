GRAMMAR=Arithmetic
PARSER=$(GRAMMAR)Parser

all: Calc.class

Calc.class: $(PARSER).java Calc.java CalcVisitor.java
	javac $(GRAMMAR)Lexer.java \
		$(GRAMMAR)BaseVisitor.java \
		$(GRAMMAR)Visitor.java \
		$(GRAMMAR)Parser.java \
		Calc.java

$(PARSER).java: $(GRAMMAR).g4
	java -jar /Users/will/libs/antlr4/antlr-4.9.2-complete.jar -no-listener -visitor $^

.PHONY: clean
clean:
	rm -f *.interp \
		*.tokens \
		*.class \
		$(GRAMMAR)BaseListener.java \
		$(GRAMMAR)Lexer.java \
		$(GRAMMAR)Listener.java \
		$(GRAMMAR)Parser.java \
		$(GRAMMAR)Visitor.java \
		$(GRAMMAR)BaseVisitor.java

