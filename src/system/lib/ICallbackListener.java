package system.lib;

/**
 * Created by alan on 2019/4/18.
 */
public interface ICallbackListener {
    void success(String command);

    void error(String command);
}
