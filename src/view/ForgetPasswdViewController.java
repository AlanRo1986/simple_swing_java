package view;

import system.Application;
import system.component.Button;
import system.component.TextEdit;
import system.component.TextEditPasswd;
import system.component.TextView;
import system.lib.CompactController;
import system.lib.ICallbackListener;
import system.lib.IController;
import system.listener.ClickActionListener;
import system.mapper.UserMapper;
import system.model.LxUser;
import system.utils.MessageDigestUtils;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by alan on 2019/4/17.
 */
public class ForgetPasswdViewController extends CompactController implements IController, ICallbackListener {

    private TextView lab1, lab2, lab3;
    private TextEdit username;
    private TextEditPasswd pwd1, pwd2;

    private Button button;
    private ClickActionListener clickActionListener;

    public ForgetPasswdViewController() {
        super(350, 280);
    }

    @Override
    protected void createInit() {


        int left = 0, top = 20;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        lab1 = new TextView("用户名:", 16, SwingConstants.RIGHT);
        lab2 = new TextView("密码:", 16, SwingConstants.RIGHT);
        lab3 = new TextView("确认密码:", 16, SwingConstants.RIGHT);

        lab1.setBounds(left + 20, top + 20, 80, 28);
        lab2.setBounds(left + 20, top + 60, 80, 28);
        lab3.setBounds(left + 20, top + 100, 80, 28);

        username = new TextEdit(20, 16);
        pwd1 = new TextEditPasswd(20, 16);
        pwd2 = new TextEditPasswd(20, 16);

        username.setBounds(left + 110, top + 20, 150, 30);
        pwd1.setBounds(left + 110, top + 60, 150, 30);
        pwd2.setBounds(left + 110, top + 100, 150, 30);

        button = new Button("保存");
        button.setActionCommand("forget");
        button.setBounds(left + 180, top + 150, 80, 32);

        panel.add(lab1);
        panel.add(lab2);
        panel.add(lab3);
        panel.add(username);
        panel.add(pwd1);
        panel.add(pwd2);
        panel.add(button);

        this.setContentPane(panel);
    }

    @Override
    public void init() {
        this.setResizable(false);

        clickActionListener = new ClickActionListener();
        clickActionListener.setCallbackListener(this);
        button.addActionListener(clickActionListener);

    }

    @Override
    public void showX() {
        this.setTitle("忘记密码");
        this.setVisible(true);
        this.init();
    }

    @Override
    public void close() {

    }

    @Override
    public void hideX() {

    }


    @Override
    public void success(String command) {
        String u = username.getText();
        String p1 = String.valueOf(pwd1.getPassword());
        String p2 = String.valueOf(pwd2.getPassword());

        if (u == null || u.trim().equals("")) {
            warning("请输入用户名");
            return;
        }
        if (p1 == null || "".equals(p1.trim())) {
            warning("请输入新密码");
            return;
        }
        if (p1.length() < 6) {
            warning("密码长度必须大于或等于6");
            return;
        }
        if (!p1.equals(p2)) {
            warning("两次输入的密码不一致");
            return;
        }

        UserMapper userMapper = (UserMapper) Application.getFactory().make(UserMapper.class);
        try {
            LxUser user = userMapper.getRow(String.format("select * from lx_user where username='%s'", u.trim()));
            if (user == null || user.getId() == null) {
                dialog("查无此人");
                return;
            }

            user.setLoginPassword(MessageDigestUtils.md5(p1));
            int r = userMapper.update(user);
            if (r > 0) {
                dialog("修改成功");
                this.dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Override
    public void error(String command) {

    }
}
