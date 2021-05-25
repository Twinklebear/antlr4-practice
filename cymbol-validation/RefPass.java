import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class RefPass extends CymbolBaseListener {
    Map<ParserRuleContext, Scope> scopes;
    Scope globals;
    Scope currentScope;

    RefPass(Map<ParserRuleContext, Scope> scopes, Scope globals) {
        this.scopes = scopes;
        this.globals = globals;
    }

    @Override
    public void enterFile(CymbolParser.FileContext ctx) {
        currentScope = globals;
    }

    @Override
    public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        currentScope = scopes.get(ctx);
    }

    @Override
    public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void enterBlock(CymbolParser.BlockContext ctx) {
        currentScope = scopes.get(ctx);
    }

    @Override
    public void exitBlock(CymbolParser.BlockContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void exitCall(CymbolParser.CallContext ctx) {
        Symbol fn = currentScope.resolve(ctx.ID().getText());
        if (fn == null) {
            reportError(ctx.ID().getSymbol(), "Call to undefined function");
        }
    }

    @Override
    public void exitVar(CymbolParser.VarContext ctx) {
        Symbol v = currentScope.resolve(ctx.ID().getText());
        if (v == null) {
            reportError(
                ctx.ID().getSymbol(), "Error: Use of undefined variable " + ctx.ID().getText());
        } else if (v.type == Symbol.Type.FUNCTION) {
            reportError(ctx.ID().getSymbol(), ctx.ID().getText() + " is not a variable");
        }
    }

    void reportError(Token t, String msg) {
        System.err.printf("Line %d:%d error: %s\n", t.getLine(), t.getCharPositionInLine(), msg);
    }
}

