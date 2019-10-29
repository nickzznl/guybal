package dataBal.gui;

import model.Player;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PlayerTableModel extends AbstractTableModel {

    private List<Player> db;

    private String[] colNames = {"ID", "Name", "Position",
            "Age category", "Back number", "KNVB member", "KNVB Number"};

    public PlayerTableModel()
    {
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<Player> db)
    {
        this.db = db;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = db.get(rowIndex);

        switch(columnIndex)
        {
            case 0:
                return player.getId();
            case 1:
                return player.getName();
            case 2:
                return player.getPosition();
            case 3:
                return player.getAgeCategory();
            case 4:
                return player.getBackNumber();
            case 5:
                return player.isKvnbMember();
            case 6:
                return player.getKnvbNumber();
        }

        return null;
    }
}
