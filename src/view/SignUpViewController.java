package view;

import system.Application;
import system.component.Button;
import system.component.TextEdit;
import system.component.TextView;
import system.lib.CompactController;
import system.lib.ICallbackListener;
import system.lib.IController;
import system.lib.Sex;
import system.listener.ClickActionListener;
import system.mapper.UserMapper;
import system.model.LxUser;
import system.utils.MessageDigestUtils;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by alan on 2019/4/17.
 */
public class SignUpViewController extends CompactController implements IController, ICallbackListener {

    private TextView lab1, lab2, lab3, lab4, lab5, lab6, lab7, lab8;
    private TextEdit username, userpwd, realname, mobile, email, nickname;
    private JComboBox<Integer> age;
    private JComboBox<String> sex;

    private Button button;
    private ClickActionListener clickActionListener = new ClickActionListener();

    public SignUpViewController() {
        super(400, 520);
    }

    @Override
    protected void createInit() {
        this.initView();
        int top = 0, left = 0;

        lab1.setBounds(left + 20, top + 20, 80, 28);
        lab2.setBounds(left + 20, top + 60, 80, 28);
        lab3.setBounds(left + 20, top + 100, 80, 28);
        lab4.setBounds(left + 20, top + 140, 80, 28);
        lab5.setBounds(left + 20, top + 180, 80, 28);
        lab6.setBounds(left + 20, top + 220, 80, 28);
        lab7.setBounds(left + 20, top + 260, 80, 28);
        lab8.setBounds(left + 20, top + 300, 80, 28);

        username.setBounds(left + 110, top + 20, 200, 30);
        userpwd.setBounds(left + 110, top + 60, 200, 30);
        nickname.setBounds(left + 110, top + 100, 200, 30);
        realname.setBounds(left + 110, top + 140, 200, 30);
        mobile.setBounds(left + 110, top + 180, 200, 30);
        email.setBounds(left + 110, top + 220, 200, 30);


        age.setBounds(left + 110, top + 260, 150, 30);
        sex.setBounds(left + 110, top + 300, 150, 30);
        button.setBounds(left + 130, top + 380, 120, 36);

        JPanel panel = new JPanel();
        panel.add(lab1);
        panel.add(lab2);
        panel.add(lab3);
        panel.add(lab4);
        panel.add(lab5);
        panel.add(lab6);
        panel.add(lab7);
        panel.add(lab8);
        panel.add(username);
        panel.add(userpwd);
        panel.add(nickname);
        panel.add(realname);
        panel.add(mobile);
        panel.add(email);
        panel.add(age);
        panel.add(sex);
        panel.add(button);

        panel.setLayout(null);
        this.setContentPane(panel);
    }


    @Override
    public void init() {
        clickActionListener.setCallbackListener(this);
        button.setActionCommand("register");
        button.addActionListener(clickActionListener);
    }

    @Override
    public void showX() {
        this.setTitle("注册");
        this.setVisible(true);
        this.setResizable(false);
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
        LxUser user = new LxUser();
        user.setUsername(username.getText());
        user.setLoginPassword(userpwd.getText());
        user.setNickname(nickname.getText());
        user.setRealName(realname.getText());
        user.setMobile(mobile.getText());
        user.setEmail(email.getText());
        user.setAge(age.getItemAt(age.getSelectedIndex()));
        user.setSex(sex.getItemAt(sex.getSelectedIndex()));

        if (user.getUsername() == null || "".equals(user.getUsername())) {
            warning("请输入用户名");
            return;
        }
        if (user.getUsername().length() > 20) {
            warning("用户名长度不允许超过20");
            return;
        }

        if (user.getLoginPassword() == null || "".equals(user.getLoginPassword())) {
            warning("请输入用户密码");
            return;
        }
        if (user.getLoginPassword().length() < 6) {
            warning("密码长度必须大于5");
            return;
        }
        if (user.getMobile()!=null&&user.getMobile().length()>11){
            warning("手机号码格式错误");
            return;
        }

        UserMapper userMapper = (UserMapper) Application.getFactory().make(UserMapper.class);
        try {
            Object has = userMapper.getOne(String.format("select id from lx_user where username='%s'", user.getUsername()));
            if (has != null) {
                warning("用户名已存在");
                return;
            }

            user.setLoginPassword(MessageDigestUtils.md5(user.getLoginPassword()));
            int r = userMapper.insert(user);
            if (r > 0) {
                dialog("注册成功");
                this.dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void error(String command) {

    }

    private void initView() {
        lab1 = new TextView("用户名:", 16, SwingConstants.RIGHT);
        lab2 = new TextView("密码:", 16, SwingConstants.RIGHT);
        lab3 = new TextView("昵称:", 16, SwingConstants.RIGHT);
        lab4 = new TextView("姓名:", 16, SwingConstants.RIGHT);
        lab5 = new TextView("手机号码:", 16, SwingConstants.RIGHT);
        lab6 = new TextView("E-mail:", 16, SwingConstants.RIGHT);
        lab7 = new TextView("年龄:", 16, SwingConstants.RIGHT);
        lab8 = new TextView("性别:", 16, SwingConstants.RIGHT);

        username = new TextEdit(20, 16);
        userpwd = new TextEdit(20, 16);
        nickname = new TextEdit(50, 16);
        realname = new TextEdit(50, 16);
        mobile = new TextEdit(11, 16);
        email = new TextEdit(50, 16);

        age = new JComboBox<>();
        sex = new JComboBox<>();
        button = new Button("注册");
        sex.addItem(Sex.FEMALE.name());
        sex.addItem(Sex.MALE.name());
        sex.addItem(Sex.UNKNOWN.name());

        for (int i = 6; i <= 120; i++) {
            age.addItem(i);
        }

    }
}
