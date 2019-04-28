package system.lib;

import java.util.logging.Logger;

/**
 * Created by alan on 2019/4/16.
 */
public class Console implements ILogger {

    private final Logger logger = Logger.getLogger(Console.class.getName());

    @Override
    public void info(String tag, Object object) {
        if (object == null) {
            logger.info(tag + " nul");

            return;
        }
        logger.info(tag + " " + (object.toString()));
    }

    @Override
    public void error(String tag, Object object) {
        if (object == null) {
            logger.warning(tag + " nul");

            return;
        }
        logger.warning(tag + " " + (object.toString()));
    }

    protected void out(Object obj) {
        if (obj == null) {
            System.err.println("nul");
        } else {
            System.out.println(obj.toString());
        }
    }
}
