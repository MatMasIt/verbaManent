import org.json.simple.JSONObject;

public class Image extends DocElement{
    private String fileN;
    private int width, height;
    private String caption;
    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("fileN", this.fileN);
        obj.put("width", this.width);
        obj.put("height", this.height);
        obj.put("comment", super.annotations);
        obj.put("caption", this.caption);
        obj.put("type", "image");
        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        if (!((String) json.get("type")).equals("image")){
            throw new Exception();
        }
        this.fileN = (String) json.get("fileN");
        this.width = (int) json.get("width");
        this.height = (int) json.get("height");
        super.annotations = (String) json.get("annotations");
        this.caption = (String) json.get("caption");
    }
}
