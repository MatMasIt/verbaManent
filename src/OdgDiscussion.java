import org.json.simple.JSONObject;

public class OdgDiscussion extends DocElement{
    private int odgIndex;
    private Deliberation deliberation;
    private String text;

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("odgIndex", this.odgIndex);
        obj.put("deliberation", this.deliberation.toJson());
        obj.put("text", this.text);
        obj.put("comment", super.annotations);
        obj.put("type", "odgDiscussion");
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        if (!((String) json.get("type")).equals("odgDiscussion")){
            throw new Exception();
        }
        Deliberation d = new Deliberation();
        this.odgIndex = (int) json.get("odgIndex");
        d.fromJson((JSONObject) json.get("deliberation"));
        this.deliberation =  d;
        this.text = (String) json.get("text");
        super.annotations = (String) json.get("annotations");

    }
}
