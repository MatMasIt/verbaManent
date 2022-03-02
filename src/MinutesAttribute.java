
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class MinutesAttribute extends JPanel implements ActionListener {
    private JLabel titleL, shortDescriptionL,authorNameL, authorEmailL, minutesNumberL, yearL, institutionL, emittingOrganL,meetingDatetimeL, meetingDatetimeEndL, meetingL, cStatusL;
    private JTextField title,authorName,authorEmail, year,institution, emittingOrgan;
    private JTextArea shortDescription;
    private JFormattedTextField minutesNumber;
    private JScrollPane shortDescriptionP;
    private DateTimePicker meetingDatetime, meetingDatetimeEnd;
    private JCheckBox showToc, isModel;
    private JComboBox mtype, cstatus;
    private JButton odgBtn, presencebtn;
    private MainFrame mf;
    public MinutesAttribute(MainFrame mf){
        this.mf = mf;
        titleL = new JLabel(mf.getTm().getValue("title"));
        title = new JTextField(15);
        shortDescriptionL = new JLabel(mf.getTm().getValue("shortDesc"));
        shortDescription = new JTextArea(15,20);
        authorNameL = new JLabel(mf.getTm().getValue("authorName"));
        authorName = new JTextField(15);
        authorEmailL = new JLabel(mf.getTm().getValue("authorEmail"));
        authorEmail = new JTextField(15);
        shortDescriptionP = new JScrollPane(shortDescription);
        minutesNumberL = new JLabel(mf.getTm().getValue("minuteNumber"));
        NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance();
        integerFieldFormatter.setMaximumFractionDigits(0);
        integerFieldFormatter.setGroupingUsed(false);
        minutesNumber = new JFormattedTextField(integerFieldFormatter);
        yearL = new JLabel(mf.getTm().getValue("year"));
        year = new JTextField(15);
        institutionL = new JLabel(mf.getTm().getValue("institution"));
        institution = new JTextField(15);
        emittingOrganL = new JLabel(mf.getTm().getValue("emittingOrgan"));
        emittingOrgan = new JTextField(15);
        meetingDatetimeL = new JLabel(mf.getTm().getValue("beginMeeting"));
        meetingDatetime = new DateTimePicker(mf);
        meetingDatetimeEndL = new JLabel(mf.getTm().getValue("endMeeting"));
        meetingDatetimeEnd = new DateTimePicker(mf);
        showToc = new JCheckBox(mf.getTm().getValue("showTableOfContents"));
        isModel = new JCheckBox(mf.getTm().getValue("useAsModel"));
        meetingL = new JLabel(mf.getTm().getValue("meetingType"));
        mtype = new JComboBox<>(new Object[]{mf.getTm().getValue("inPerson"), mf.getTm().getValue("videocall")});
        cStatusL = new JLabel(mf.getTm().getValue("completionStatus"));
        cstatus = new JComboBox<>(new Object[]{mf.getTm().getValue("sketch"), mf.getTm().getValue("draft"), mf.getTm().getValue("almostComplete"), mf.getTm().getValue("complete"), mf.getTm().getValue("submitted")});
        odgBtn = new JButton(mf.getTm().getValue("odg")+" (Vuoto)");
        presencebtn = new JButton(mf.getTm().getValue("presences")+" (Vuoto)");
        this.setLayout(new GridLayout(14 ,2));
        this.add(titleL);
        this.add(title);
        this.add(shortDescriptionL);
        this.add(shortDescriptionP);
        this.add(authorNameL);
        this.add(authorName);
        this.add(authorEmailL);
        this.add(authorEmail);
        this.add(minutesNumberL);
        this.add(minutesNumber);
        this.add(yearL);
        this.add(year);
        this.add(institutionL);
        this.add(institution);
        this.add(emittingOrganL);
        this.add(emittingOrgan);
        this.add(meetingDatetimeL);
        this.add(meetingDatetime);
        this.add(meetingDatetimeEndL);
        this.add(meetingDatetimeEnd);
        this.add(showToc);
        this.add(isModel);
        this.add(meetingL);
        this.add(mtype);
        this.add(cStatusL);
        this.add(cstatus);
        this.add(odgBtn);
        this.add(presencebtn);
        odgBtn.addActionListener(this);
        presencebtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(odgBtn)){
            new OdgDialog(this.mf);
        }
        else  if(e.getSource().equals(presencebtn)){
            new PresenceDialog(this.mf);
        }
    }
}
