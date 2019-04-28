package view;

import system.Application;
import system.component.Button;
import system.component.GBC;
import system.component.TextEdit;
import system.component.TextView;
import system.lib.CompactController;
import system.lib.IController;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alan on 2019/4/21.
 */
public class AccountViewController extends CompactController implements IController {

    private TextView lab1, lab2, lab3, lab4, lab5;
    private TextEdit out, in, year;
    private JComboBox lan;
    private Button cl;
    private JTextArea logs;

    public AccountViewController() {
        super(800, 600);
    }

    @Override
    protected void createInit() {
        initView();
        setViewName();

    }

    private void setViewName() {
        lab1.setText(Application.getLang("lang_name").toString());
        lab2.setText(Application.getLang("money_out").toString());
        lab3.setText(Application.getLang("money_in").toString());
        lab4.setText(Application.getLang("year").toString());
        lab5.setText(Application.getLang("logs").toString());
        cl.setText(Application.getLang("button").toString());
        this.setTitle(Application.getLang("app_name").toString());

        out.setText("2500.00");
        in.setText("15000.00");
        year.setText("20.0");
        cl.setActionCommand("cl");
    }


    @Override
    public void init() {
        this.setLayout(new GridBagLayout());
        add(lab1, new GBC(0, 0).setAnchor(GBC.EAST));
        add(lan, new GBC(1, 0).setFill(GBC.HORIZONTAL));
        add(cl, new GBC(3, 0).setFill(GBC.HORIZONTAL));

        add(lab2, new GBC(0, 1).setAnchor(GBC.EAST));
        add(out, new GBC(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));
        add(lab3, new GBC(2, 1).setAnchor(GBC.EAST));
        add(in, new GBC(3, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL));

        add(lab4, new GBC(0, 2).setAnchor(GBC.EAST));
        add(year, new GBC(1, 2).setWeight(100, 0).setFill(GBC.HORIZONTAL));

        add(lab5, new GBC(0, 3).setAnchor(GBC.EAST));
        add(new JScrollPane(logs), new GBC(0, 4, 200, 100).setWeight(0, 100).setFill(GBC.BOTH));

        cl.addActionListener((event) -> {
            if (event.getActionCommand().equals("cl")) {
                cls();
            }
        });

        lan.addItemListener((e) -> {
            Locale locale = (Locale) e.getItem();
            Application.setLang(ResourceBundle.getBundle("lang/" + locale.getLanguage() + "_" + locale.getCountry()));
            setViewName();
        });
    }

    private void cls() {
        cl.setEnabled(false);
        logs.setText("");
        double y = Double.parseDouble(year.getText());
        double m_out = Double.parseDouble(out.getText());
        double m_in = Double.parseDouble(in.getText());
        int c = Double.valueOf(Math.ceil(y * 12.0)).intValue();

        String msg = "第{0}个月将支出{1,number,currency},将存入{2,number,currency};支出累计{3,number,currency},存款{4,number,currency};";
        MessageFormat format = new MessageFormat(msg, (Locale) lan.getItemAt(lan.getSelectedIndex()));

        double c_out = 0;
        double c_in = 0;
        double left = 0;
        for (int i = 1; i <= c; i++) {
            m_out = m_out + m_out * 0.0041;
            m_in = m_in + m_in * 0.01 /12;
            left = left + m_out * 0.0041;
            c_out = c_out + m_out ;
            c_in = c_in + m_in;
            logs.append(format.format(msg, i, m_out, m_in, c_out, c_in) + "\n");
        }
        logs.append("\n\n");
        logs.append(format.format("{0}年以后，你一共花了{1,number,currency}，并且存到了{2,number,currency}。\n", c / 12, c_out, c_in));
        logs.append(format.format("支出金额按照5%/年货币贬值，存入金额按照1%/年的浮动增加，贬值金额：{0,number,currency}。\n",left));

        print(format.getLocale());
        cl.setEnabled(true);
    }

    @Override
    public void showX() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.init();
    }

    @Override
    public void close() {

    }

    @Override
    public void hideX() {

    }

    private void initView() {
        out = new TextEdit(20, 16);
        in = new TextEdit(20, 16);
        year = new TextEdit(20, 16);

        lan = new JComboBox(new Locale[]{Locale.CHINA, Locale.US});
        cl = new Button("");
        logs = new JTextArea(20, 20);

        lab1 = new TextView("", 16);
        lab2 = new TextView("", 16);
        lab3 = new TextView("", 16);
        lab4 = new TextView("", 16);
        lab5 = new TextView("", 16);
        logs.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        logs.setOpaque(true);
        logs.setBackground(Color.BLACK);
        logs.setForeground(Color.GREEN);
    }
}
