import org.json.simple.JSONObject;

public class PresenceItem implements JsonSerializeable{
    private String surname;
    private String name;
    private String nameTitle; // Prof., etc
    private String joinedAt; // nullable
    private String leftAt; // nullable
    private String comment;
    private boolean present;

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("surname", this.surname);
        obj.put("name", this.name);
        obj.put("nameTitle", this.nameTitle);
        obj.put("joinedAt", this.joinedAt);
        obj.put("leftAt", this.leftAt);
        obj.put("comment", this.comment);
        obj.put("present", this.present);
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        this.surname = (String) json.get("surname");
        this.name = (String) json.get("name");
        this.nameTitle = (String) json.get("nameTitle");
        this.nameTitle = (String) json.get("nameTitle");
        this.joinedAt = (String) json.get("joinedAt");
        this.leftAt = (String) json.get("leftAt");
        this.comment = (String) json.get("comment");
        this.present = (boolean) json.get("present");
    }
}
