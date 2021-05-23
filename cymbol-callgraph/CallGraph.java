import java.util.*;

class CallGraph {
    Set<String> functions = new HashSet<>();
    // The other functions each function calls, map is {function, [callees]}
    Map<String, List<String>> edges = new HashMap<>();

    public void addEdge(String source, String target) {
        List<String> fcnEdges = null;
        if (!edges.containsKey(source)) {
            fcnEdges = new ArrayList<String>();
            edges.put(source, fcnEdges);
        } else {
            fcnEdges = edges.get(source);
        }
        fcnEdges.add(target);
    }

    public String toDOT() {
        StringBuilder buf = new StringBuilder();
        buf.append("digraph G {\n");
        buf.append("    ranksep=.25;\n");
        buf.append("    edge [arrowsize=.5]\n");
        buf.append("    node [shape=circle, fontname=\"ArialNarrow\",\n");
        buf.append("          fontsize=12, fixedsize=true, height=.45];\n");

        // Append our list of nodes that are in the graph
        buf.append("    ");
        for (String f : functions) {
            buf.append(f);
            buf.append("; ");
        }
        buf.append("\n");

        // Now append the list of edges for each node
        for (Map.Entry<String, List<String>> entry : edges.entrySet()) {
            for (String t : entry.getValue()) {
                buf.append("    ");
                buf.append(entry.getKey());
                buf.append(" -> ");
                buf.append(t);
                buf.append(";\n");
            }
        }
        buf.append("}\n");
        return buf.toString();
    }
}

