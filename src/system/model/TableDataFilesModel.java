package system.model;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 2019/4/23.
 */
public class TableDataFilesModel extends AbstractTableModel {
    private List<File> list;
    private List<String> names;

    public TableDataFilesModel(List<File> list) {
        this.list = list;
        names = new ArrayList<>();
        names.add("ID");
        names.add("文件名");
        names.add("路径");
        names.add("大小");
    }

    @Override
    public int getRowCount() {
        return list.size();
    }


    @Override
    public int getColumnCount() {
        return names.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else if (columnIndex == 1) {
            return list.get(rowIndex).getName();
        } else if (columnIndex == 2) {
            return list.get(rowIndex).getAbsolutePath();
        } else if (columnIndex == 3) {
            return list.get(rowIndex).length() / 1024;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return names.get(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex==0){
            return Boolean.class;
        }
        return String.class;
    }

    public void addData(File file) {
        list.add(file);
        this.fireTableDataChanged();
    }
}
