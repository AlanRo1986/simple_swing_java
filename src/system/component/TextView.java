package system.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alan on 2019/4/18.
 */
public class TextView extends JLabel {
    public TextView() {
        this("标签", 12);
    }

    public TextView(String text) {
        this(text, 12);
    }

    public TextView(String text, int fontSize) {
        this(text, 12, SwingConstants.LEFT);
    }

    public TextView(String text, int FontSize, int horizontalAlignment) {
        super(text, horizontalAlignment);
        Font f = new Font("微软雅黑", Font.PLAIN, FontSize);
        this.setFont(f);
        this.setOpaque(true);
    }
}
