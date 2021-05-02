public class ShortToUnicodeString extends ArrayInitBaseListener {
    int nestedDepth = 0;

    /** Translate { to " */
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        if (nestedDepth == 0) {
            System.out.print('"');
        }
        ++nestedDepth;
    }

    /** Translate } to " */
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        --nestedDepth;
        if (nestedDepth == 0) {
            System.out.print('"');
        }
    }

    /** Translate ints to their char16_t representation */
    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        if (ctx.INT() != null) {
            // Get the value of the integer (assuming no nested arrays)
            int value = Integer.valueOf(ctx.INT().getText());
            System.out.printf("\\u%04x", value);
        }
    }
}

