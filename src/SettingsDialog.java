import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog implements ActionListener {

    public JButton cancel,save;
    private JPanel buttons, actions;
    private JComboBox<String> lef;
    public SettingsDialog(MainFrame mf){
        super(mf, mf.getTm().getValue("settings"), true);
        actions = new JPanel();
        actions = new JPanel();
        String[] lefs = {"Sistema", "Metal", "Motif"};

        lef = new JComboBox<>(lefs);
        lef.addActionListener(this);
        this.setLayout(new BorderLayout());
        this.add(actions,BorderLayout.NORTH);
        buttons = new JPanel();
        cancel = new JButton(mf.getTm().getValue("cancel"));
        save = new JButton(mf.getTm().getValue("save"));
        buttons.setLayout(new GridLayout(1,2));
        buttons.add(cancel);
        buttons.add(save);
        this.add(buttons, BorderLayout.SOUTH);
        cancel.addActionListener(this);
        setSize(500, 500);
        this.setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cancel)){
            this.dispose();
        }
    }
}
