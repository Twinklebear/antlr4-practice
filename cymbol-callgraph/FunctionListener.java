class FunctionListener extends CymbolBaseListener {
    CallGraph graph = new CallGraph();
    String currentFunctionName = null;

    public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
        currentFunctionName = ctx.ID().getText();
        graph.functions.add(currentFunctionName);
    }

    public void exitCall(CymbolParser.CallContext ctx) {
        String callee = ctx.ID().getText();
        graph.addEdge(currentFunctionName, callee);
    }
}

