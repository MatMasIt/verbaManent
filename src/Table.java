

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Table extends DocElement{
    private ArrayList<ArrayList<String>> data;

    @Override
    public JSONObject toJson() {
        JSONArray rows = new JSONArray();
        for (ArrayList<String> datum : this.data) {
            JSONArray cols = new JSONArray();
            for (String s : datum) {
                cols.add(s);
            }
            rows.add(cols);
        }
        JSONObject obj = new JSONObject();
        obj.put("type","table");
        obj.put("data",rows);
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        if (!((String) json.get("type")).equals("table")){
            throw new Exception();
        }
        JSONArray rows = (JSONArray) json.get("data");
        this.data.clear();
        for (Object row : rows) {
            ArrayList<String> currentRow = new ArrayList<String>();
            JSONArray cols = (JSONArray) row;
            for (Object col : cols) {
                currentRow.add((String) col);
            }
            this.data.add(currentRow);
        }
    }
}
