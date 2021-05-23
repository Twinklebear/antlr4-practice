import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        CymbolLexer lexer = new CymbolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CymbolParser parser = new CymbolParser(tokens);

        ParseTree tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        FunctionListener collector = new FunctionListener();
        walker.walk(collector, tree);
        System.out.println(collector.graph.toDOT());
    }
}

