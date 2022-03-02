import org.json.simple.JSONObject;

public interface JsonSerializeable {

    public abstract JSONObject toJson();
    public abstract void fromJson(JSONObject json) throws Exception;
}
