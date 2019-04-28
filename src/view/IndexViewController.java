package view;

import system.Application;
import system.component.Button;
import system.conf.Constant;
import system.lib.CompactController;
import system.lib.ICallbackListener;
import system.lib.IController;
import system.listener.ClickActionListener;
import system.mapper.UserMapper;
import system.model.LxUser;
import system.model.TableDataModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by alan on 2019/4/17.
 */
public class IndexViewController extends CompactController implements IController, ICallbackListener {

    private int page = 1, pageSize = 20, top = 2, left = 0;

    private JTable table;
    private Button prev, next;
    private JScrollPane scrollPane;
    private JPanel panel;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();


    private UserMapper userMapper;
    private ClickActionListener clickActionListener = new ClickActionListener();


    public IndexViewController() {
        super(1024, 726);
    }

    @Override
    protected void createInit() {
        this.initView();

        Font f = new Font("微软雅黑", Font.PLAIN, 16);

        table.setOpaque(true);
        table.setBackground(Color.CYAN);
        table.setFont(f);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        table.setColumnSelectionAllowed(true);
//        scrollPane.setViewportView(table);

//        panel = new JPanel();
//        panel.add(prev);
//        panel.add(next);

        scrollPane.add(table);
        scrollPane.setViewportView(table);

//        this.setContentPane(scrollPane);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(prev);
        this.getContentPane().add(next);
        table.setRowHeight(32);

//        panel.setLayout(null);
//        panel.setOpaque(true);
//        panel.setBackground(Color.blue);

    }


    @Override
    public void init() {
        print("index.init");
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                table.setBounds(left + 0, top + 0, getWidth(), getHeight() - 115);
                scrollPane.setBounds(left + 0, top + 0, getWidth(), getHeight() - 115);
//                panel.setBounds(left, top+getHeight() - 115, getWidth(), 100);
                prev.setBounds(left + getWidth() - 280, top + getHeight() - 100, 80, 32);
                next.setBounds(left + getWidth() - 180, top + getHeight() - 100, 80, 32);

                defaultTableModel.fireTableStructureChanged();
                table.updateUI();

                super.componentResized(e);
            }

        });

        userMapper = (UserMapper) Application.getFactory().make(UserMapper.class);

        this.read(1);
        clickActionListener.setCallbackListener(this);
        prev.addActionListener(clickActionListener);
        next.addActionListener(clickActionListener);
        if (page == 1) {
            prev.setEnabled(false);
        }
    }

    @Override
    public void showX() {
        if (!Application.getSession().isLogin()) {
            LoginViewController login = (LoginViewController) Application.getFactory().make(LoginViewController.class);
            this.hideX();
            login.showX();
            Application.pushStack(this);
            return;
        }

        this.setTitle(Constant.APP_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.init();
    }

    @Override
    public void close() {
        this.dispose();
    }

    @Override
    public void hideX() {
        this.setVisible(false);
    }

    private void initView() {
        table = new JTable();
        prev = new Button("上一页");
        next = new Button("下一页");
        prev.setActionCommand("prev");
        next.setActionCommand("next");
        scrollPane = new JScrollPane(table);

    }

    private void read(int page) {
        if (page <= 0) {
            return;
        }
        int p = (page - 1) * pageSize;
        int pz = (pageSize);

        StringBuffer sql = new StringBuffer();
        sql.append("select * from lx_user where 1 order by id desc limit ")
                .append(p)
                .append(",")
                .append(pz)
                .append(";");

        try {
            java.util.List<LxUser> userList = userMapper.getAll(sql.toString());
            TableDataModel model = new TableDataModel(userList);
//            Vector names = new Vector();
//            names.add("UID");
//            names.add("用户名");
//            names.add("密码");
//            names.add("昵称");
//            names.add("姓名");
//            names.add("手机");
//            names.add("邮箱");
//            names.add("年龄");
//            names.add("性别");
//
//            Vector data = new Vector();
//            for (LxUser u : userList) {
//                Vector row = new Vector();
//                row.add(u.getId());
//                row.add(u.getUsername());
//                row.add("***");
//                row.add(u.getNickname());
//                row.add(u.getRealName());
//                row.add(u.getMobile());
//                row.add(u.getEmail());
//                row.add(u.getAge());
//                row.add(u.getSex());
//                data.add(row);
//            }

//            defaultTableModel.setDataVector(data, names);
//            table.setModel(defaultTableModel);
            table.setModel(model);
            this.page = page;
            if (page > 1) {
                prev.setEnabled(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void success(String command) {
        if (command.equals("prev")) {
            this.read(--page);
        } else {
            this.read(++page);
        }
    }

    @Override
    public void error(String command) {

    }

}
