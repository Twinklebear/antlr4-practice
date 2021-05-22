import java.util.*;

public class LoadCSV extends CSVBaseListener {
    public static final String EMPTY = "";

    // The list of rows in the file, mapped as header=value
    List<Map<String, String>> rows = new ArrayList<Map<String, String>>();

    // The file header values
    List<String> header;

    // The current row being parser
    List<String> currentRowFields;

    /**
     * When we enter the header, set the currentRowFields to the header
     * list of fields to parse it
     */
    @Override
    public void enterHdr(CSVParser.HdrContext ctx) {
        header = new ArrayList<String>();
        currentRowFields = header;
    }
    /**
     * When we enter a row, allocate a new array for the row's data and
     * set it to our currentRowFields
     */
    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        // Header sets its header list to the currentRowFields to parse,
        // so don't allocate a new row if we've got the header already
        if (ctx.getParent().getRuleIndex() != CSVParser.RULE_hdr) {
            currentRowFields = new ArrayList<String>();
        }
    }
    /**
     * When we exit a row, build the header=value map and add it to the
     * list of rows
     */
    @Override
    public void exitRow(CSVParser.RowContext ctx) {
        // If we're in the header rule, don't add it to the map
        if (ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr) {
            return;
        }
        Map<String, String> rowFields = new HashMap<String, String>();
        for (int i = 0; i < header.size(); ++i) {
            rowFields.put(header.get(i), currentRowFields.get(i));
        }
        rows.add(rowFields);
    }
    /**
     * Add the string onto the list
     */
    @Override
    public void exitText(CSVParser.TextContext ctx) {
        currentRowFields.add(ctx.TEXT().getText());
    }
    /**
     * Add the string onto the list
     */
    @Override
    public void exitString(CSVParser.StringContext ctx) {
        currentRowFields.add(ctx.STRING().getText());
    }
    /**
     * Empty string added to the list
     */
    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
        currentRowFields.add(EMPTY);
    }
}

