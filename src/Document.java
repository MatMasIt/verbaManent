import org.joda.time.Instant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.Doc;
import java.io.File;
import java.util.ArrayList;
// minutes = verbale
public class Document implements JsonSerializeable{
    private String title;
    private String shortDescription;
    private String authorName;
    private String authorEmail;
    private int minutesNumber;
    private String minutesYear;
    private String institution;
    private String emittingOrgan;
    private MeetingType meetingType;
    private Instant meetingStart;
    private Instant meetingEnd;
    public boolean showToc;
    private float verbaManentVersion;
    private Instant created;
    private Instant lastEdit;
    private Odg odg;
    private ArrayList<PresenceItem> presenceTable;
    private ArrayList<DocElement> elements;
    private int lastSeenElementIndex;
    public boolean isModel;
    private String filePath;
    private CompletionStatus cstatus;
    public Document(){
        this.elements = new ArrayList<DocElement>();
        this.presenceTable =  new ArrayList<PresenceItem>();
    }
    public void load(File f) {
        this.filePath = f.getAbsolutePath();
    }

    public void save() {
        this.saveAs(new File(this.filePath));
    }

    public void saveAs(File f) {
        this.filePath = f.getAbsolutePath();
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("title", this.title);
        obj.put("shortDescription", this.shortDescription);
        obj.put("authorName", this.authorName);
        obj.put("authorEmail", this.authorEmail);
        obj.put("minutesNumber", this.minutesNumber);
        obj.put("minutesYear", this.minutesYear);
        obj.put("institution", this.institution);
        obj.put("emittingOrgan", this.emittingOrgan);
        if(this.meetingType.equals(MeetingType.phisical))
            obj.put("meetingType", "p");
        else if(this.meetingType.equals(MeetingType.videoconference))
            obj.put("meetingType", "v");
        obj.put("verbaManentVersion", this.verbaManentVersion);
        obj.put("created", this.created.getMillis());
        obj.put("lastEdit", this.lastEdit.getMillis());
        obj.put("meetingStart", this.meetingStart.getMillis());
        obj.put("meetingEnd", this.meetingEnd.getMillis());
        obj.put("odg", this.odg.toJson());
        JSONArray ja = new JSONArray();
        for (PresenceItem element : this.presenceTable) {
            ja.add(element.toJson());
        }
        obj.put("presenceTable", ja);
        ja = new JSONArray();
        for (DocElement element : this.elements) {
            ja.add(element.toJson());
        }
        obj.put("elements", ja);
        obj.put("lastSeenElementIndex", this.lastSeenElementIndex);
        obj.put("showToc", this.showToc);
        obj.put("isModel", this.isModel);
        switch (this.cstatus) {
            case SKETCH:
                obj.put("completionStatus", "s");
                break;
            case DRAFT:
                obj.put("completionStatus", "d");
                break;
            case ALMOST_COMPLETE:
                obj.put("completionStatus", "ac");
                break;
            case COMPLETE:
                obj.put("completionStatus", "c");
                break;
            case SUBMITTED:
                obj.put("completionStatus", "su");
        }

        return obj;
    }

    @Override
    public void fromJson(JSONObject json) throws Exception {
        this.title = (String) json.get("title");
        this.shortDescription = (String) json.get("shortDescription");
        this.authorName = (String) json.get("authorName");
        this.authorEmail = (String) json.get("authorEmail");
        this.minutesNumber = (int) json.get("minutesNumber");
        this.minutesYear = (String) json.get("minutesYear");
        this.institution = (String) json.get("institution");
        this.emittingOrgan = (String) json.get("emittingOrgan");
        this.showToc = (boolean) json.get("showToc");
        String mtp = (String) json.get("meetingType");
        if(mtp.equals("p"))
            this.meetingType = MeetingType.phisical;
        else if(mtp.equals("v"))
            this.meetingType = MeetingType.videoconference;
        String comps = (String) json.get("completionStatus");
        switch (comps) {
            case "s":
                this.cstatus = CompletionStatus.SKETCH;
                break;
            case "d":
                this.cstatus = CompletionStatus.DRAFT;
                break;
            case "ac":
                this.cstatus = CompletionStatus.ALMOST_COMPLETE;
                break;
            case "c":
                this.cstatus = CompletionStatus.COMPLETE;
                break;
            case "su":
                this.cstatus = CompletionStatus.SUBMITTED;
        }
        this.created = new Instant((long) json.get("created"));
        this.lastEdit = new Instant((long) json.get("lastEdit"));
        this.meetingStart = new Instant((long) json.get("meetingStart"));
        this.meetingEnd = new Instant((long) json.get("meetingEnd"));
        Odg od = new Odg();
        od.fromJson((JSONObject) json.get("odg"));
        this.odg = od;
        this.presenceTable.clear();
        JSONArray pt = (JSONArray) json.get("presenceTable");
        for (Object value : pt) {
            JSONObject o = (JSONObject) value;
            PresenceItem pi = new PresenceItem();
            pi.fromJson(o);
            this.presenceTable.add(pi);
        }
        this.elements.clear();
        JSONArray je = (JSONArray) json.get("elements");
        for (Object value : je) {
            JSONObject o = (JSONObject) value;
            if (o.get("type").equals("conclusion")) {
                Conclusion c = new Conclusion();
                c.fromJson(o);
                this.elements.add(c);
            }
            else if (o.get("type").equals("odgDiscussion")) {
                OdgDiscussion c = new OdgDiscussion();
                c.fromJson(o);
                this.elements.add(c);
            }
            else if (o.get("type").equals("table")) {
                Table c = new Table();
                c.fromJson(o);
                this.elements.add(c);
            }
            else if (o.get("type").equals("image")) {
                Table c = new Table();
                c.fromJson(o);
                this.elements.add(c);
            }
            else if (o.get("type").equals("strayParagraph")) {
                StrayParagraph c = new StrayParagraph();
                c.fromJson(o);
                this.elements.add(c);
            }
        }
        this.lastSeenElementIndex = (int) json.get("lastSeenElementIndex");
        this.verbaManentVersion = (int) json.get("verbaManentVersion");
        this.isModel = (boolean) json.get("isModel");
    }

    public void makeNew(){

    }
}
