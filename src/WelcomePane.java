import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class WelcomePane extends JPanel {
    private  JLabel  recentsTitle;
    private JThumbnail welcome;
    private JPanel recents;
    private JEditorPane editorPane;
    private JScrollPane editorScrollPane;
    public WelcomePane(MainFrame mf){
        recents = new JPanel();
        editorPane = new JEditorPane();
        editorScrollPane = new JScrollPane();

        welcome = new JThumbnail(System.getProperty("user.dir")+"/logo.png","logo",100,100);

        recentsTitle = new JLabel(mf.getTm().getValue("recent"), SwingConstants.CENTER);
        recentsTitle.setFont(new Font("", Font.PLAIN, 20));

        ArrayList<String> pl = new ArrayList<>();
        pl.add("a");
        pl.add("b");
        pl.add("c");
        pl.add("d");
        pl.add("e");
        pl.add("f");
        pl.add("g");

        try {
            editorPane.setPage("file://"+System.getProperty("user.dir")+"/ver.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        int nrows = pl.size() / 2;
        if(pl.size() % 2!=0){
            nrows++;
        }
        recents.setLayout(new GridLayout(nrows,2));
        for (String s : pl) {
            recents.add(new JButton(s));
        }
        this.setLayout(new GridLayout(4,1));

        this.add(welcome);
        this.add(editorScrollPane);
        this.add(recentsTitle);
        this.add(recents);
    }
}
