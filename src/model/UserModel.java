package model;

import java.io.Serializable;

/**
 * Created by alan on 2018/12/12.
 */
public class UserModel implements Serializable {


    private int id;
    private String name;

    private UserModel parent;

    public UserModel() {
    }

    public UserModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel getParent() {
        return parent;
    }

    public void setParent(UserModel parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "{id:" + this.id + ",name:" + this.name + "}";
    }
}
