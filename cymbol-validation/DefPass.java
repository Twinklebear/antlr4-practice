import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class DefPass extends CymbolBaseListener {
    Map<ParserRuleContext, Scope> scopes = new IdentityHashMap<>();
    Scope globals;
    Scope currentScope;

    @Override
    public void enterFile(CymbolParser.FileContext ctx) {
        globals = new Scope(null);
        currentScope = globals;
    }

    @Override
    public void exitFile(CymbolParser.FileContext ctx) {
        System.out.println(globals);
    }

    @Override
    public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        String fcnName = ctx.ID().getText();
        // Note: in the book they also track the return type, which would be used to
        // validate assigning the right type to a variable storing the return value
        Symbol fcnSymbol = new Symbol(fcnName, Symbol.Type.FUNCTION);
        currentScope.define(fcnSymbol);

        // Create the scope for the function and make it active
        Scope fcnScope = new Scope(currentScope);
        scopes.put(ctx, fcnScope);
        currentScope = fcnScope;
    }

    @Override
    public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void enterBlock(CymbolParser.BlockContext ctx) {
        // Create the scope for the block and make it active
        Scope scope = new Scope(currentScope);
        scopes.put(ctx, scope);
        currentScope = scope;
    }

    @Override
    public void exitBlock(CymbolParser.BlockContext ctx) {
        System.out.println(currentScope);
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void exitFormalParameter(CymbolParser.FormalParameterContext ctx) {
        String name = ctx.ID().getText();
        String type = ctx.type().getText();
        Symbol param = new Symbol(name, Symbol.stringToType(ctx.type().getText()));
        currentScope.define(param);
    }

    @Override
    public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
        String name = ctx.ID().getText();
        String type = ctx.type().getText();
        Symbol param = new Symbol(name, Symbol.stringToType(ctx.type().getText()));
        currentScope.define(param);
    }
}

