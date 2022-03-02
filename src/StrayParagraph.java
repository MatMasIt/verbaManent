import org.json.simple.JSONObject;

public class StrayParagraph extends DocElement{
    private String text;

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("text", this.text);
        obj.put("comment", super.annotations);
        obj.put("type", "strayParagraph");
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        if (!((String) json.get("type")).equals("strayParagraph")){
            throw new Exception();
        }
        this.text = (String) json.get("text");
        super.annotations = (String) json.get("annotations");
    }
}
