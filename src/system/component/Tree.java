package system.component;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by alan on 2019/4/24.
 */
public class Tree extends JTree {

    public Tree() {
        super();
        this.init();
    }

    public Tree(Object[] value) {
        super(value);
        this.init();
    }


    public Tree(TreeModel newModel) {
        super(newModel);
        this.init();
    }


    public Tree(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);
        this.init();
    }


    public Tree(TreeNode root) {
        super(root);
        this.init();
    }


    public Tree(Hashtable<?, ?> value) {
        super(value);
        this.init();
    }

    public Tree(Vector<?> value) {
        super(value);
        this.init();
    }

    private void init(){
        this.setFont(Fontx.newInstance(16));
//        this.putClientProperty("JTree.lineStyle","None");
        this.putClientProperty("JTree.lineStyle","Angled");
        this.setShowsRootHandles(true);
//        this.setRootVisible(false);
    }
}
