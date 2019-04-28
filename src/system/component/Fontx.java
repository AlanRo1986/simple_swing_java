package system.component;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * Created by alan on 2019/4/24.
 */
public class Fontx extends Font {

    public Fontx(int size) {
        this("微软雅黑", PLAIN, size);
    }

    public Fontx(String name, int style, int size) {
        super(name, style, size);
    }


    public Fontx(Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
        super(attributes);
    }


    protected Fontx(Font font) {
        super(font);
    }

    public static Fontx newInstance(int size) {
        return new Fontx(size);
    }
}
