package system.component;

import javax.swing.*;
import java.text.Format;
import java.text.NumberFormat;

/**
 * Created by alan on 2019/4/24.
 */
public class TextEditFormat extends JFormattedTextField {

    public TextEditFormat() {
        this.init();
    }

    private void init() {
        setFont(Fontx.newInstance(16));
    }


    public TextEditFormat(Object value) {
        super(value);
        this.init();
    }


    public TextEditFormat(Format format) {
        super(format);
        this.init();

    }


    public TextEditFormat(AbstractFormatter formatter) {
        super(formatter);
        this.init();

    }


    public TextEditFormat(AbstractFormatterFactory factory) {
        super(factory);
        this.init();

    }


    public TextEditFormat(AbstractFormatterFactory factory, Object currentValue) {
        super(factory, currentValue);
        this.init();

    }

    public Double getDoubleValue() {
        Number number = (Number) this.getValue();
        return number.doubleValue();
    }

    public Integer getIntegerValue() {
        Number number = (Number) this.getValue();
        return number.intValue();
    }

    public static TextEditFormat newInstanceForInteger(){
        TextEditFormat format = new TextEditFormat(NumberFormat.getIntegerInstance());
        format.setColumns(11);
        format.setValue(0);
        return format;
    }

    public static TextEditFormat newInstanceForCurrency(){
        TextEditFormat format = new TextEditFormat(NumberFormat.getCurrencyInstance());
        format.setColumns(32);
        format.setValue(0);
        return format;
    }
}
