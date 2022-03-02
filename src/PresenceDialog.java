import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PresenceDialog extends JDialog{
    private JTable table;
    private DefaultTableModel dtm;
    public JButton add, remove, edit;
    private JPanel buttons;
    private JScrollPane tsp;
    public PresenceDialog(MainFrame mf){

        super(mf, mf.getTm().getValue("presences"), true);
        Object[][] data = {};
        String[] columnNames = { mf.getTm().getValue("title"), mf.getTm().getValue("name"), mf.getTm().getValue("surname"), mf.getTm().getValue("present"), mf.getTm().getValue("title"), mf.getTm().getValue("arrival"), mf.getTm().getValue("departure") };
        dtm = new DefaultTableModel(data, columnNames);
        table = new JTable(dtm){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new WordWrapCellRenderer());

        Object row [] = { "Prof.", "Giacomo", "Rossi", "Sì", "-", "-", "-" };
        dtm.addRow(row);
        Object rr [] = { "Prof.", "Giorgio", "Verdi", "Sì", "10:20", "-", "-" };
        dtm.addRow(rr);
        tsp = new JScrollPane(table);
        this.setLayout(new BorderLayout());
        this.add(tsp,BorderLayout.NORTH);
        buttons = new JPanel();
        add = new JButton(mf.getTm().getValue("add"));
        remove = new JButton(mf.getTm().getValue("delete"));
        edit = new JButton(mf.getTm().getValue("edit"));
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(add);
        buttons.add(remove);
        buttons.add(edit);
        this.add(buttons, BorderLayout.SOUTH);
        table.getTableHeader().setReorderingAllowed(false);

        setSize(500, 500);
        this.setResizable(false);
        setVisible(true);
    }
}
