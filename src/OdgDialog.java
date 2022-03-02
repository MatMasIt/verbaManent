import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OdgDialog extends JDialog{
    private JTable table;
    private DefaultTableModel dtm;
    public JButton add, remove, edit;
    private JPanel buttons;
    private JScrollPane tsp;
    public OdgDialog(MainFrame mf){
        super(mf, mf.getTm().getValue("dayOrder"), true);
        Object[][] data = {};
        String[] columnNames = {mf.getTm().getValue("pointNumber"), mf.getTm().getValue("title"), mf.getTm().getValue("usedInDoc") };
        dtm = new DefaultTableModel(data, columnNames);
        table = new JTable(dtm){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        Object row [] = {"1","Discussione del sis e del sos","Sì"};
        dtm.addRow(row);
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
