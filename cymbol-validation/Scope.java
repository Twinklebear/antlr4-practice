import java.util.*;

public class Scope {
    Scope enclosingScope = null;
    Map<String, Symbol> symbols = new HashMap<>();

    public Scope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    public Symbol resolve(String name) {
        Symbol s = symbols.get(name);
        if (s != null) {
            return s;
        }
        if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        }
        return null;
    }

    public void define(Symbol sym) {
        symbols.put(sym.name, sym);
    }

    public String toString() {
        return symbols.keySet().toString();
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }
}

