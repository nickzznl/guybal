package dataBal.gui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private PlayerTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener PersonTableListener;



    public TablePanel()
    {
        tableModel = new PlayerTableModel();
        table = new JTable(tableModel);
        popup = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int row = table.rowAtPoint(e.getPoint());

                table.getSelectionModel().setSelectionInterval(row, row);

                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if(PersonTableListener != null)
                {
                    PersonTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
            }
        });

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public void setData(List<Player> db)
    {
        tableModel.setData(db);
    }

    public void refresh()
    {
       tableModel.fireTableDataChanged();
    }

    public void setPersonTableListener(PersonTableListener listener)
    {
        this.PersonTableListener = listener;
    }
}
