import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Calc {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        ArithmeticLexer lexer = new ArithmeticLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokens);

        ParseTree tree = parser.prog();
        CalcVisitor calc = new CalcVisitor();
        calc.visit(tree);
    }
}

