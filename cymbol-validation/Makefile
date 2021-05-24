GRAMMAR=Cymbol
PARSER=$(GRAMMAR)Parser

all: ValidateSymbols

ValidateSymbols: $(PARSER).java Main.java DefPass.java RefPass.java Scope.java
	javac *.java

$(PARSER).class: $(PARSER).java
	javac $(GRAMMAR)Lexer.java \
		$(GRAMMAR)BaseListener.java \
		$(GRAMMAR)Listener.java \
		$(GRAMMAR)Parser.java

$(PARSER).java: $(GRAMMAR).g4
	java -jar /Users/will/libs/antlr4/antlr-4.9.2-complete.jar $^

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

