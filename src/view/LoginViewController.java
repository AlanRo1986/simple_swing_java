package view;

import system.Application;
import system.conf.Constant;
import system.lib.CompactController;
import system.lib.ICallbackListener;
import system.lib.IController;
import system.listener.ClickActionListener;
import system.listener.MouseActionListener;
import system.mapper.UserMapper;
import system.model.LxUser;
import system.model.Session;
import system.utils.MessageDigestUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by alan on 2019/4/17.
 */
public class LoginViewController extends CompactController implements IController, ICallbackListener {

    private JButton login;
    private JTextField username;
    private JPasswordField userPwd;
    private JLabel uname, upwd, register, forgetPwd;
    private JPanel panel;

    private int len = 20;

    private MouseActionListener mouseActionListener;
    private ClickActionListener clickActionListener;

    public LoginViewController() {
        super(450, 280);
    }

    @Override
    protected void createInit() {

    }

    @Override
    public void init() {
        int top = 20;
        int left = 0;

        panel = new JPanel();
        Font f1 = new Font("微软雅黑", Font.PLAIN, 14);
//        panel.setBounds(50,50,600,400);

        uname = new JLabel("用户名:", SwingConstants.RIGHT);
        upwd = new JLabel("密码:", SwingConstants.RIGHT);
        uname.setBounds(10 + left, 20 + top, 80, 26);
        upwd.setBounds(10 + left, 75 + top, 80, 26);
        uname.setFont(f1);
        upwd.setFont(f1);

        username = new JTextField(len);
        userPwd = new JPasswordField(len);
        username.setBounds(95 + left, 20 + top, 150, 28);
        userPwd.setBounds(95 + left, 75 + top, 150, 28);
        username.setFont(f1);
        userPwd.setFont(f1);

        register = new JLabel("<html><u>注册</u></html>", SwingConstants.CENTER);
        register.setToolTipText("没有账号？注册一个");
        register.setBounds(300 + left, 30 + top, 100, 26);

        forgetPwd = new JLabel("<html><u>忘记密码</u></html>", SwingConstants.CENTER);
        forgetPwd.setToolTipText("忘记密码？找回");
        forgetPwd.setBounds(300 + left, 70 + top, 100, 26);

        Font f = new Font("微软雅黑", Font.PLAIN, 16);
        register.setFont(f);
        forgetPwd.setFont(f);
        register.setForeground(Color.BLUE);
        forgetPwd.setForeground(Color.BLUE);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgetPwd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        login = new JButton("登陆");
        login.setBounds(170 + left, 140 + top, 80, 32);
        login.setActionCommand("login");



        JLabel l = new JLabel();
        l.setOpaque(true);
        l.setBackground(Color.lightGray);
        l.setBounds(290 + left, 15 + top, 1, 100);

        panel.add(l);

        panel.add(uname);
        panel.add(upwd);
        panel.add(username);
        panel.add(userPwd);
        panel.add(register);
        panel.add(forgetPwd);
        panel.add(login);
        panel.setLayout(null);
//        this.setLayout(new BorderLayout());
//        Container container = this.getContentPane();
//        container.add(panel,BorderLayout.WEST);
        this.setContentPane(panel);

        mouseActionListener = new MouseActionListener();
        clickActionListener = new ClickActionListener();
        clickActionListener.setCallbackListener(this);

        login.addActionListener(clickActionListener);
        register.addMouseListener(mouseActionListener);
        forgetPwd.addMouseListener(mouseActionListener);
    }

    @Override
    public void showX() {
        this.setTitle(Constant.TITLE_LOGIN);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public void hideX() {
        this.setVisible(false);
    }

    private void doLogin() {
        String name = username.getText();
        String pwd = String.valueOf(userPwd.getPassword());

        if ("".equals(name) || "".equals(name.trim())) {
            dialog("请输入用户名");
            return;
        }

        if ("".equals(pwd) || "".equals(pwd.trim())) {
            dialog("请输入用户密码");
            return;
        }

        out(name + " : " + pwd);

        UserMapper userMapper = (UserMapper) Application.getFactory().make(UserMapper.class);
        try {
            LxUser user = userMapper.getRow(String.format("select * from lx_user where username='%s'", name.trim()));
            print(user);
            if (user == null || user.getId() == null) {
                warning("查无此人");
                return;
            }
            if (!MessageDigestUtils.md5(pwd).equals(user.getLoginPassword())) {
                warning("用户密码错误");
                return;
            }

            Session session = Application.getSession();
            session.setUid(user.getId());
            session.setUsername(user.getUsername());
            session.setUserpwd(user.getLoginPassword());
//            session.setLoginTime();
            session.setLogin(true);
            Application.setSession(session);

            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.close();

            IndexViewController index = (IndexViewController) Application.popStack();
            index.showX();
        } catch (SQLException e) {
            e.printStackTrace();
            warning(e.getMessage());
        } finally {

        }
    }


    @Override
    public void success(String command) {
        if ("login".equals(command)) {
            this.doLogin();
        }
    }

    @Override
    public void error(String command) {

    }
}
