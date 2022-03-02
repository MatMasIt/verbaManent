import org.json.simple.JSONObject;

public class Deliberation extends DocElement{
    private int approve, reject, notvoting;

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("approve", this.approve);
        obj.put("reject", this.reject);
        obj.put("notvoting", this.notvoting);
        obj.put("comment", super.annotations);
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) {
        this.approve = (int) json.get("approve");
        this.reject = (int) json.get("reject");
        this.notvoting = (int) json.get("notvoting");
        super.annotations = (String) json.get("annotations");
    }
}
