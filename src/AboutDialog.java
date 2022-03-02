import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class AboutDialog extends JDialog{
    private JEditorPane editorPane;
    private JScrollPane editorScrollPane;
    public AboutDialog(MainFrame parent) {
        super(parent, parent.getTm().getValue("about"), true);

        editorPane = new JEditorPane();
        editorScrollPane = new JScrollPane();

        try {
            editorPane.setPage("file://"+System.getProperty("user.dir")+"/"+parent.getTm().getValue("aboutFile")+".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        this.setLayout(new GridLayout(1,1));
        this.add(editorScrollPane);
        setSize(500, 500);
        this.setResizable(false);
        setVisible(true);

        editorPane.setEditable(false);//so its not editable
        editorPane.setOpaque(false);//so we dont see with background
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent hle) {

                System.out.println(hle.getURL());
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    System.out.println(hle.getURL());
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(hle.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }


}
           
         
    
    