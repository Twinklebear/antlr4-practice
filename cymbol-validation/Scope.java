import java.util.*;

public class Scope { 
    Scope enclosingScope = null;
    Map<String, String> symbols = new HashMap<>();

    public Scope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }
}

