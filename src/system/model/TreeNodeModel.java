package system.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alan on 2019/4/24.
 */
public class TreeNodeModel extends DefaultMutableTreeNode {

    public TreeNodeModel() {

    }


    public TreeNodeModel(Object userObject) {
        super(userObject);
    }


    public TreeNodeModel(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }
}
