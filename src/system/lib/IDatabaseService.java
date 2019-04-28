package system.lib;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alan on 2019/4/15.
 */
public interface IDatabaseService<T> {

    void connect() throws SQLException;

    T getRow(String sql)  throws SQLException;

    Object getOne(String sql)  throws SQLException;

    List<T> getAll(String sql) throws SQLException;

    int insert(T record)  throws SQLException;

    int update(T record) throws SQLException;

    int delete(String sql) throws SQLException;

    int exec(String sql,String action) throws SQLException;

    void close()  throws SQLException;
}
