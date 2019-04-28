package system.lib;

/**
 * Created by alan on 2019/4/16.
 */
public interface ILogger {

    void info(String tag,Object object);

    void error(String tag,Object object);

}
