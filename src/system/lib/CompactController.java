package system.lib;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by alan on 2019/4/17.
 */
public abstract class CompactController extends JFrame {

    private final Logger logger = Logger.getLogger(CompactController.class.getName());
    private Toolkit toolkit = this.getToolkit();

    private int width, height;

    public CompactController(int w, int h) {
        this.width = w;
        this.height = h;

        this.setBounds((getWindowWidth() - w) / 2, (getWindowHeight() - h) / 2, w, h);
        this.createInit();
    }

    protected abstract void createInit();

    protected void print(Object obj) {
        if (obj == null) {
            logger.warning("nul");
        } else {
            logger.info(obj.toString());
        }
    }

    protected void out(Object obj) {
        if (obj == null) {
            System.out.println("nul");
        } else {
            System.out.println(obj.toString());
        }
    }

    protected int getWindowWidth() {
        return toolkit.getScreenSize().width;
    }

    protected int getWindowHeight() {
        return toolkit.getScreenSize().height;
    }

    protected void dialog(String msg) {
        JOptionPane.showMessageDialog(this.getContentPane(), msg);
    }

    protected void warning(String msg) {
        JOptionPane.showMessageDialog(this.getContentPane(), msg, "警告", JOptionPane.WARNING_MESSAGE);
    }

    public int getHeightX() {
        return height;
    }

    public int getWidthX() {
        return width;
    }
}
