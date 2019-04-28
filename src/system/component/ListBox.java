package system.component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by alan on 2019/4/22.
 */
public class ListBox<T> extends JList<T> {

    public ListBox(){

    }

    public ListBox(T[] arr){
        super(arr);
    }

    public void setFont(int size){
        Font f = new Font("微软雅黑",Font.PLAIN,size);
        this.setFont(f);
    }
}
