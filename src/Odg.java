import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Odg implements JsonSerializeable{
    private ArrayList<String> indices;
    private ArrayList<String> texts;

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        /*obj.put("approve", this.approve);
        obj.put("reject", this.reject);
        obj.put("notvoting", this.notvoting);
        obj.put("comment", super.annotations);*/
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {

    }
}
