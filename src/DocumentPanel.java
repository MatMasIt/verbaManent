import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentPanel extends JPanel {
    private JSplitPane splitPane;
    private JList list;
    private JPanel docPane, listHolderPane, listActions;
    private JScrollPane listScroller;
    private JButton add, delete, moveUp, moveDown;
    private DefaultListModel<String> listModel;
    public DocumentPanel(MainFrame mf) {
        super(false);
        docPane = new JPanel();
        docPane.add(new JLabel("SASS"));
        listHolderPane = new JPanel();
        listActions = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                listHolderPane, docPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);

//Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(250, 50);
        listHolderPane.setMinimumSize(minimumSize);
        docPane.setMinimumSize(minimumSize);
        listModel = new DefaultListModel<String>();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        list = new JList(listModel); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        listScroller = new JScrollPane(list);
        listHolderPane.setLayout(new BorderLayout());

        listHolderPane.add(listScroller,BorderLayout.CENTER);

        add = new JButton( mf.getTm().getValue("add"));
        delete = new JButton( mf.getTm().getValue("delete"));
        moveUp = new JButton(mf.getTm().getValue("moveUp"));
        moveDown = new JButton(mf.getTm().getValue("moveDown"));
        listActions.add(add);
        listActions.add(delete);
        listActions.add(moveUp);
        listActions.add(moveDown);
        listActions.setLayout(new GridLayout(2,2));

        listHolderPane.add(listActions,BorderLayout.SOUTH);
        this.setLayout(new GridLayout(1,1));
        this.add(splitPane);



    }

}
