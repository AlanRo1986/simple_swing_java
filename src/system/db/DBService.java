package system.db;

import com.sun.org.apache.bcel.internal.util.ClassLoaderRepository;
import system.lib.Console;
import system.model.LxUser;
import system.lib.IDatabaseService;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by alan on 2019/4/16.
 */
public abstract class DBService<T> extends Console implements IDatabaseService<T> {

    protected static final String TAG = "DBService:";
    protected static final String ACTION_INSERT = "INSERT";
    protected static final String ACTION_QUERY = "QUERY";
    protected static final String ACTION_UPDATE = "UPDATE";
    protected static final String ACTION_DELETE = "DELETE";

    private String driver;
    private String url;
    private String username;
    private String userpwd;

    private Connection conn;
    private Statement statement;

    public DBService() {
        Properties properties = new Properties();
        InputStream in = null;
        in = ClassLoaderRepository.class.getResourceAsStream("/db.properties");
        try {
            properties.load(in);
            setDriver(properties.get("driver").toString());
            setUrl(properties.get("url").toString());
            setUsername(properties.get("username").toString());
            setUserpwd(properties.get("userpwd").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DBService(String url, String username, String userpwd) {
        this.url = url;
        this.username = username;
        this.userpwd = userpwd;
    }

    @Override
    public void connect() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, userpwd);
            conn.setAutoCommit(false);
        }
        statement = conn.createStatement();
    }

    @Override
    public T getRow(String sql) throws SQLException {
        int r = exec(sql, ACTION_QUERY);
        T t = null;
        if (r > 0) {
            ResultSet res = statement.getResultSet();
            res.next();
            t = parserClass(res);
            res.close();
        }

        statement.close();

        return t;
    }


    @Override
    public Object getOne(String sql) throws SQLException {
        exec(sql, ACTION_QUERY);
        ResultSet res = statement.getResultSet();
        if (res.next()){
            Object o = res.getString(1);
            res.close();
            statement.close();
            return o;
        }
        return null;
    }

    @Override
    public List<T> getAll(String sql) throws SQLException {
        List<T> list = new ArrayList<>();
        int r = exec(sql, ACTION_QUERY);
        ResultSet res = statement.getResultSet();
        while (res.next()) {
            list.add(parserClass(res));
        }
        res.close();
        statement.close();
        return list;
    }

    @Override
    public int insert(T record) throws SQLException {
        String sql = this.parserClass(record, ACTION_INSERT);
        return exec(sql, ACTION_INSERT);
    }

    @Override
    public int update(T record) throws SQLException {
        String sql = this.parserClass(record, ACTION_UPDATE);
        return exec(sql, ACTION_UPDATE);
    }

    @Override
    public int delete(String sql) throws SQLException {
        return exec(sql, ACTION_DELETE);
    }

    @Override
    public int exec(String sql, String action) throws SQLException {
        if (sql == null) {
            throw new SQLException("SQL string must be require.");
        }
        this.connect();

        int i = 0;
        info(TAG, sql);
        try {
            if (action.equals(ACTION_QUERY)) {
                ResultSet set = statement.executeQuery(sql);
                i = 1;
            } else {
                i = statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (!action.equals(ACTION_QUERY)) {
                conn.rollback();
            }
        } finally {
            if (!action.equals(ACTION_QUERY)) {
                conn.commit();
            }
        }
        return i;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public abstract T parserClass(ResultSet res) throws SQLException;

    public abstract String parserClass(T res, String action);


//    private LxUser formClass(ResultSet res) throws SQLException {
//        try {
//            ResultSetMetaData metaData = res.getMetaData();
//
//            Class classz = Class.forName(LxUser.class.getName());
//            LxUser user = (LxUser) classz.newInstance();
//            Field[] fields = classz.getDeclaredFields();
//
//            for (Field f : fields) {
//                for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                    if (f.getName().equals(metaData.getColumnLabel(i))) {
//                        f.setAccessible(true);
//                        if ((f.getType().equals(String.class))) {
//                            f.set(user, res.getString(f.getName()));
//                        } else if ((f.getType().equals(Integer.class))) {
//                            f.set(user, res.getInt(f.getName()));
//                        }
//                        break;
//                    }
//                }
//
//            }
//
//            return user;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
