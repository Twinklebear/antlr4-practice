import java.util.HashMap;
import java.util.Map;

public class CalcVisitor extends ArithmeticBaseVisitor<Integer> {
    // Memory for variables defined in the calculator
    Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitPrintExpr(ArithmeticParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitClear(ArithmeticParser.ClearContext ctx) {
        memory = new HashMap<String, Integer>();
        return 0;
    }

    @Override
    public Integer visitAssign(ArithmeticParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitMulDiv(ArithmeticParser.MulDivContext ctx) {
        // Evaluate left and right sub expressions
        int lhs = visit(ctx.expr(0));
        int rhs = visit(ctx.expr(1));
        if (ctx.op.getType() == ArithmeticParser.MUL) {
            return lhs * rhs;
        }
        return lhs / rhs;
    }

    @Override
    public Integer visitAddSub(ArithmeticParser.AddSubContext ctx) {
        // Evaluate left and right sub expressions
        int lhs = visit(ctx.expr(0));
        int rhs = visit(ctx.expr(1));
        if (ctx.op.getType() == ArithmeticParser.ADD) {
            return lhs + rhs;
        }
        return lhs - rhs;
    }

    @Override
    public Integer visitInt(ArithmeticParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(ArithmeticParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        // TODO: It'd be better to throw an exception here,
        // but how to have the generated parent specify it throws?
        return 0;
    }

    @Override
    public Integer visitParens(ArithmeticParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}

