package system.component;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by alan on 2019/4/23.
 */
public class Table extends JTable {

    public Table() {
        this.init();
    }


    public Table(TableModel dm) {
        super(dm);
        this.init();
    }

    public Table(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        this.init();
    }


    public Table(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        this.init();
    }

    private void init(){
        Font f = new Font("微软雅黑",Font.PLAIN,16);
        this.setFont(f);
        this.setRowHeight(30);
        this.setFillsViewportHeight(false);

    }
}
