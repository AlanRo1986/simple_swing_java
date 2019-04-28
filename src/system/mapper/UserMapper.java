package system.mapper;

import system.model.LxUser;
import system.db.DBService;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by alan on 2019/4/17.
 */
public class UserMapper extends DBService<LxUser> {
    @Override
    public LxUser parserClass(ResultSet res) throws SQLException {
        if (res.getRow()==0){
            return null;
        }
        try {
            ResultSetMetaData metaData = res.getMetaData();

            Class classz = Class.forName(LxUser.class.getName());
            LxUser user = (LxUser) classz.newInstance();
            Field[] fields = classz.getDeclaredFields();

            List<String> keys = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                keys.add(metaData.getColumnName(i));
            }

            for (Field f : fields) {
                if (keys.contains(f.getName())) {
                    f.setAccessible(true);
                    if ((f.getType().equals(String.class))) {
                        f.set(user, res.getString(f.getName()));
                    } else if ((f.getType().equals(Integer.class))) {
                        f.set(user, res.getInt(f.getName()));
                    }
                }
            }

            return user;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String parserClass(LxUser res, String action) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> data = new HashMap<>();
        try {
            Class classz = Class.forName(res.getClass().getName());
            Field[] fields = classz.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Object obj = f.get(res);
                if (obj != null) {
                    data.put(f.getName(), obj);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Set<String> keys = data.keySet();
        if (action.equals(ACTION_INSERT)) {
//            INSERT INTO `lx_user` (`id`, `username`, `loginPassword`, `nickname`, `realName`, `email`, `mobile`, `sex`, `age`, `createTime`) VALUES
//                    (119, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, 'UNSPECIFIED', 110, 1533607155);
            sql.append("INSERT INTO `lx_user` (`id`");
            String v = "null";
            for (String k : keys) {
                if ("id".equals(k)) {
                    continue;
                }
                sql.append(",")
                        .append("`")
                        .append(k)
                        .append("`");
                v += ",'" + data.get(k).toString() + "'";
            }
            sql.append(") VALUES");
            sql.append("(")
                    .append(v)
                    .append(");");

        } else {
            //UPDATE `lx_user` SET `id`=[value-1],`username`=[value-2],`loginPassword`=[value-3],`nickname`=[value-4],
            // `realName`=[value-5],`email`=[value-6],`mobile`=[value-7],`sex`=[value-8],`age`=[value-9],`createTime`=[value-10] WHERE 1
            sql.append("UPDATE `lx_user` SET id=").append(res.getId());
            for (String k : keys) {
                if ("id".equals(k)) {
                    continue;
                }
                sql.append(",").append(k)
                        .append("=")
                        .append("'")
                        .append(data.get(k).toString())
                        .append("'");
            }
            sql.append(" WHERE id=")
                    .append(res.getId());

        }

        return sql.toString();
    }
}
