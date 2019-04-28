package system;

import system.conf.Constant;
import system.db.DBService;
import system.lib.CompactController;
import system.model.Session;
import view.AccountViewController;
import view.IndexViewController;
import view.TestViewController;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by alan on 2019/4/17.
 */
public class Application {

    private static Session session;

    private static FactoryControls factory;

    private static Stack<CompactController> stack = new Stack<>();

    private static Locale locale = Locale.getDefault();

    private static ResourceBundle lang;

    public static void main(String[] args) {

        factory = new FactoryControls();
        session = new Session();
        init();

        EventQueue.invokeLater(() -> {
//            IndexViewController index = (IndexViewController) factory.make(IndexViewController.class);
//            index.showX();
            TestViewController account = (TestViewController) factory.make(TestViewController.class);
            account.showX();
        });
    }

    public static FactoryControls getFactory() {
        return factory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Application.session = session;
    }

    public static void pushStack(CompactController cl) {
        stack.push(cl);
    }

    public static CompactController popStack() {
        return stack.pop();
    }

    public static Object getLang(String key) {
        if (lang.containsKey(key.toLowerCase())) {
            return lang.getObject(key.toLowerCase());
        }
        return key.toUpperCase();
    }

    public static void setLang(ResourceBundle lang) {
        Application.lang = lang;
    }

    private static void init() {
        lang = ResourceBundle.getBundle("lang/" + locale.getLanguage() + "_" + locale.getCountry());
    }


}
