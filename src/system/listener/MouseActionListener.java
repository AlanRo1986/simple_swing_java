package system.listener;

import system.Application;
import system.lib.Console;
import view.ForgetPasswdViewController;
import view.SignUpViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by alan on 2019/4/18.
 */
public class MouseActionListener extends Console implements MouseListener {

    private String id;

    public MouseActionListener(){

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof JLabel){
            JLabel label = (JLabel) e.getComponent();
            if (label.getText().contains("注册")){
                SignUpViewController signUp = (SignUpViewController) Application.getFactory().make(SignUpViewController.class);
                signUp.showX();

            }else if (label.getText().contains("忘记密码")){
                ForgetPasswdViewController forget = (ForgetPasswdViewController) Application.getFactory().make(ForgetPasswdViewController.class);
                forget.showX();
            }
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited(MouseEvent e) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
