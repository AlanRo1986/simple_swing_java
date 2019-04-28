package system.component.render;

import system.component.Fontx;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by alan on 2019/4/24.
 */
public class TreeCellRender extends DefaultTreeCellRenderer {

    public TreeCellRender(){
//        setClosedIcon(new ImageIcon("D:\\WWW\\zhinanzhen\\public\\emoticons\\10.gif"));
//        setLeafIcon(new ImageIcon("D:\\WWW\\zhinanzhen\\public\\emoticons\\12.gif"));
//        setOpenIcon(new ImageIcon("D:\\WWW\\zhinanzhen\\public\\emoticons\\26.gif"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        component.setFont(Fontx.newInstance(22));

        return component;
    }
}
