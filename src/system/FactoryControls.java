package system;

import system.lib.CompactController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan on 2019/4/17.
 */
public class FactoryControls {

    Map<String,Object> map = Collections.synchronizedMap(new HashMap<>());

    public FactoryControls(){

    }

    public Object make(Class classz){
        if (map.containsKey(classz.getName())){
            return map.get(classz.getName());
        }

        try {
            Class c = Class.forName(classz.getName());
            Object cc = c.newInstance();
            map.put(classz.getName(), cc);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map.get(classz.getName());
    }


}
