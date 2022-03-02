import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AttachmentsPanel extends JPanel{
    private JTable table;
    private DefaultTableModel dtm;
    public JButton add, remove, edit;
    private JPanel buttons;
    private JScrollPane tsp;
    public AttachmentsPanel(MainFrame mf){
        Object[][] data = {};
        String[] columnNames = { mf.getTm().getValue("attachmentName"), mf.getTm().getValue("attachmentType"),mf.getTm().getValue("attachmentSize") };
        dtm = new DefaultTableModel(data, columnNames);
        table = new JTable(dtm){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        Object row [] = {"a","b","loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"};
        dtm.addRow(row);
        tsp = new JScrollPane(table);
        this.setLayout(new BorderLayout());
        this.add(tsp,BorderLayout.NORTH);
        buttons = new JPanel();
        add = new JButton( mf.getTm().getValue("add"));
        remove = new JButton( mf.getTm().getValue("delete"));
        edit = new JButton( mf.getTm().getValue("edit"));
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(add);
        buttons.add(remove);
        buttons.add(edit);
        this.add(buttons, BorderLayout.SOUTH);
        table.getTableHeader().setReorderingAllowed(false);
    }
}
