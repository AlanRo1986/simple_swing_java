package system.listener;

import system.lib.Console;
import system.lib.ICallbackListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alan on 2019/4/18.
 */
public class ClickActionListener extends Console implements ActionListener {

    private ICallbackListener callbackListener;

    public ClickActionListener(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        out(e.getActionCommand());
        callbackListener.success(e.getActionCommand());
//
//        switch (e.getActionCommand()) {
//            case "login":
//                callbackListener.success(e.getActionCommand());
//                break;
//            case "forget":
//                break;
//        }
    }

    public void setCallbackListener(ICallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    public ICallbackListener getCallbackListener() {
        return callbackListener;
    }
}
