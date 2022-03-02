
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class MainFrame extends JFrame implements ActionListener {
    private JMenuBar jmb;
    private JMenu file, export, help;
    private JMenuItem open, save, saveAs, pdf, word, odt, exit, about, settings, guide;
    private JPanel root;
    private CardLayout cardLayout;
    public TranslationMan getTm() {
        return tm;
    }

    private TranslationMan tm;
    private JTabbedPane tabbedPane;
    public static float verbaManentVersion = 1;
    private JFileChooser jfc;
    private DocumentPanel dpanel;
    private MinutesAttribute ma;
    private AttachmentsPanel atp;
    private WelcomePane wp;
    private GuideDialog gd;
    private Document doc;
    public MainFrame() throws MalformedURLException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.tm = new TranslationMan(TranslationMan.Language.English);
        } catch (FileNotFoundException | ParseException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,
                    "Could not access language file",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
        jfc = new JFileChooser();
        wp = new WelcomePane(this);
        FileFilter vmfilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().endsWith(".wmpf") || f.getName().endsWith(".wmmf");
            }

            @Override
            public String getDescription() {
                return "Progetti e Modelli di Verbamanent";
            }
        };
        jfc.setFileFilter(vmfilter);
        jfc.setAcceptAllFileFilterUsed(false);
        URL url = new URL("file://"+System.getProperty("user.dir")+"/logo.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        this.setIconImage(img);


        jmb = new JMenuBar();
        file = new JMenu(tm.getValue("file"));
        open = new JMenuItem(tm.getValue("open"));
        save = new JMenuItem(tm.getValue("save"));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        //save.setIcon(UIManager.getIcon("FileView.floppyDriveIcon");
        saveAs = new JMenuItem(tm.getValue("saveAs"));
        saveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        export = new JMenu(tm.getValue("export"));
        pdf = new JMenuItem("PDF");
        pdf.setAccelerator(KeyStroke.getKeyStroke("control alt P"));
        word = new JMenuItem("Word");
        word.setAccelerator(KeyStroke.getKeyStroke("control alt W"));
        odt = new JMenuItem("Odt");
        odt.setAccelerator(KeyStroke.getKeyStroke("control alt O"));
        exit = new JMenuItem(tm.getValue("exit"));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        export.add(pdf);
        export.add(word);
        export.add(odt);
        settings = new JMenuItem(tm.getValue("settings"));
        guide = new JMenuItem(tm.getValue("guide"));
        //settings.setMaximumSize(new Dimension(100, settings.getPreferredSize().height));
        settings.setAccelerator(KeyStroke.getKeyStroke("control T"));
        help = new JMenu(tm.getValue("help"));
        about = new JMenuItem(tm.getValue("about"));
        about.setAccelerator(KeyStroke.getKeyStroke("control shift H"));
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(export);
        file.add(exit);
        jmb.add(file);
        jmb.add(settings);
        help.add(guide);
        help.add(about);
        jmb.add(help);
        this.setJMenuBar(jmb);
        this.setSize(800,700);
        this.setVisible(true);
        this.setTitle("VerbaManent");
        exit.addActionListener(this);
        about.addActionListener(this);
        open.addActionListener(this);
        settings.addActionListener(this);
        guide.addActionListener(this);
        tabbedPane = new JTabbedPane();
        ImageIcon icon = null;
        ma = new MinutesAttribute(this);
        dpanel = new DocumentPanel(this);
        atp = new AttachmentsPanel(this);
        tabbedPane.addTab(tm.getValue("document"), icon, dpanel,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab(tm.getValue("attributes"), icon, ma,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab(tm.getValue("attachments"), icon, atp,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        cardLayout = new CardLayout();
        root = new JPanel();
        root.setLayout(cardLayout);
        root.add(tabbedPane,"tp");
        root.add(wp,"wp");
        this.setLayout(new GridLayout(1,1));
        this.add(root);
        this.recentView();
        this.docView();
        this.setSize(this.getWidth(),this.getHeight()+1); // stupid hack to fix obnoxious swing bug that prevented the tabbed pane from displaying
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    public static void main(String[] args){
        Locale.setDefault(new Locale("it", "IT"));

        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        try {
            MainFrame mf = new MainFrame();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit)){
            //check save first
            System.exit(1);
        }
        else if(e.getSource().equals(about)){
            new AboutDialog(this);
        }
        else if(e.getSource().equals(settings)){
            new SettingsDialog(this);
        }
        else if(e.getSource().equals(guide)){
            if(gd== null || !gd.isShowing()) {
                gd = new GuideDialog(this);
            }
        }
        else if (e.getSource().equals(open)){

            int returnVal = jfc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println("Opening: " + file.getName() + "." + "\n");
            } else {
                System.out.println("Open command cancelled by user." + "\n");
            }

        }
    }
    private void recentView(){
        cardLayout.show(root, "wp");
        /*tabbedPane.setVisible(false);
        wp.setVisible(true);
        save.setVisible(false);
        saveAs.setVisible(false);
        export.setVisible(false);*/
    }
    private void docView(){
        cardLayout.show(root, "tp");
        /*
        wp.setVisible(false);
        tabbedPane.setVisible(true);
        save.setVisible(true);
        saveAs.setVisible(true);
        export.setVisible(true);*/
    }
}
