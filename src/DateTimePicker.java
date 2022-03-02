import com.toedter.calendar.JDateChooser;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimePicker extends JPanel {
    String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    String[] minutes = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "60"};
    private JDateChooser jc;
    private JLabel date, time, colon;
    private JComboBox<String> h, m;
    private JPanel datePanel;
    private JPanel timePanel;
    public DateTimePicker(MainFrame mf){
        jc = new JDateChooser();
        h = new JComboBox<>(hours);
        m = new JComboBox<>(minutes);

        this.setLayout(new BorderLayout());
        date = new JLabel( mf.getTm().getValue("date"));
        time = new JLabel( mf.getTm().getValue("time"));
        colon = new JLabel(":", SwingConstants.CENTER);

        datePanel = new JPanel();
        timePanel = new JPanel();
        datePanel.setLayout(new GridLayout(1,2));
        datePanel.add(date);
        datePanel.add(jc);

        timePanel.setLayout(new GridLayout(1,4));
        timePanel.add(time);
        timePanel.add(h);
        timePanel.add(colon);
        timePanel.add(m);

        this.setLayout(new GridLayout(2,1));

        this.add(datePanel);
        this.add(timePanel);

    }
    public Instant getValue(){
        Date d = this.jc.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(d);
        String stf = strDate +" "+ this.h.getSelectedItem()+":"+this.m.getSelectedItem()+":00";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return  Instant.parse(stf, formatter);
    }

    public void resetValue(){
        this.m.setSelectedIndex(0);
        this.h.setSelectedIndex(0);
        this.jc.cleanup();
    }
}
