package system.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alan on 2019/4/25.
 */
public class ComponentX extends JComponent {

    private IDrawComponent drawComponent;

    public ComponentX() {
        this(800, 600);
    }

    public ComponentX(int w, int h) {
        this(w, h, null);
    }

    public ComponentX(int w, int h, IDrawComponent drawComponent) {
        this.setSize(w, h);
        this.drawComponent = drawComponent;
        this.init();
    }

    private void init() {
        this.setOpaque(true);
        this.setBackground(Color.lightGray);

    }


    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        drawComponent.draw(this, g);
    }
}
