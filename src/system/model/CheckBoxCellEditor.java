package system.model;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

/**
 * Created by alan on 2019/4/23.
 */
public class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox checkBox;

    public CheckBoxCellEditor(){
        checkBox = new JCheckBox();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.print(isSelected);
        return checkBox;
    }


    @Override
    public Object getCellEditorValue() {
        System.out.print("123");
        return !checkBox.isSelected();
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        System.out.print("shouldSelectCell");
        return super.shouldSelectCell(anEvent);
    }

    @Override
    public void cancelCellEditing() {
        System.out.print("cancelCellEditing");

        super.cancelCellEditing();
    }

    @Override
    public boolean stopCellEditing() {
        System.out.print("stopCellEditing");

        return super.stopCellEditing();
    }

}
