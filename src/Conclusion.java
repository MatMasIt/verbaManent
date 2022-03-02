import org.json.simple.JSONObject;

public class Conclusion extends DocElement{
    private String secretaryPresenceIndex, presidentPresenceIndex;

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("secretaryPresenceIndex", this.secretaryPresenceIndex);
        obj.put("presidentPresenceIndex", this.presidentPresenceIndex);
        obj.put("comment", super.annotations);
        obj.put("type", "conclusion");
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        if (!((String) json.get("type")).equals("conclusion")){
            throw new Exception();
        }
        this.secretaryPresenceIndex = (String) json.get("secretaryPresenceIndex");
        this.presidentPresenceIndex = (String) json.get("presidentPresenceIndex");
        super.annotations = (String) json.get("annotations");
    }
}