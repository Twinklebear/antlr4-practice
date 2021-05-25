public class Symbol {
    public static enum Type { INVALID, VOID, INT, FLOAT, FUNCTION }

    String name;
    Type type;

    public Symbol(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public static Type stringToType(String str) {
        if (str == "void") {
            return Type.VOID;
        }
        if (str == "int") {
            return Type.INT;
        }
        if (str == "float") {
            return Type.FLOAT;
        }
        return Type.INVALID;
    }
}

