import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        CSVLexer lexer = new CSVLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);

        ParseTree tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        LoadCSV loader = new LoadCSV();
        walker.walk(loader, tree);
        System.out.println(loader.rows);
    }
}

