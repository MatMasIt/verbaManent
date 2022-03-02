import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TranslationMan {
    public enum Language {
        English,
        Italian,
        French
    }
    private String file = "strings.json";
    private Language cl;
    private JSONObject jo;
    public TranslationMan(Language lang) throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser();
        File f = new File(this.file);
        Scanner scanner = new Scanner(new File(this.file));
        String text = scanner.useDelimiter("\\A").next();
        scanner.close(); // Put this call in a finally block
        jo = (JSONObject) parser.parse(text);
        this.cl = lang;
    }
    private String enumToLangString(Language l){
        if(l.equals(Language.English))
            return "en";
        else if(l.equals(Language.Italian))
            return "it";
        else if(l.equals(Language.French))
            return "fr";
        return "en";// Default
    }
    public String getValue(String key){
        JSONObject ldi = (JSONObject) this.jo.get(this.enumToLangString(this.cl));
        return (String) ldi.get(key);
    }
}
