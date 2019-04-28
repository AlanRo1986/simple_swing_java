package system.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alan on 2019/4/18.
 */
public class TextEditPasswd extends JPasswordField {
    public TextEditPasswd() {
        this(100,12);
    }

    public TextEditPasswd(int len, int fontSize) {
        super(len);

        Font f = new Font("微软雅黑", Font.PLAIN, fontSize);
        this.setFont(f);
    }
}
