package system.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 2019/4/23.
 */
public class TableDataModel extends AbstractTableModel {

    private List<LxUser> list;
    private List<String> names;

    public TableDataModel(List<LxUser> list) {
        this.list = list;
        names = new ArrayList<>();
        names.add("UID");
        names.add("用户名");
        names.add("密码");
        names.add("昵称");
        names.add("姓名");
        names.add("手机");
        names.add("邮箱");
        names.add("年龄");
        names.add("性别");
    }

    @Override
    public int getRowCount() {
        return list.size();
    }


    @Override
    public int getColumnCount() {
        return 9;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return list.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return list.get(rowIndex).getUsername();
        } else if (columnIndex == 2) {
            return list.get(rowIndex).getLoginPassword().substring(26) + "****";
        } else if (columnIndex == 3) {
            return list.get(rowIndex).getNickname();
        } else if (columnIndex == 4) {
            return list.get(rowIndex).getRealName();
        } else if (columnIndex == 5) {
            return list.get(rowIndex).getMobile();
        } else if (columnIndex == 6) {
            return list.get(rowIndex).getEmail();
        } else if (columnIndex == 7) {
            return list.get(rowIndex).getAge();
        } else if (columnIndex == 8) {
            return list.get(rowIndex).getSex();
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return names.get(column);
    }
}
