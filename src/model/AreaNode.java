package model;

import java.util.List;

/**
 * Created by alan on 2018/12/15.
 */
public class AreaNode {

    private String name;

    private Integer postCode;

    private List<AreaNode> child;

    public AreaNode() {
    }

    public AreaNode(String name, Integer postCode) {
        this.name = name;
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public List<AreaNode> getChild() {
        return child;
    }

    public void setChild(List<AreaNode> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        String r = "{name:\"%s\",postCode:\"%s\"}";
        String str = String.format(r, this.getName(), this.getPostCode());
        return str;
    }
}
