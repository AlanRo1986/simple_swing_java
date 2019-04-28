package model;

import java.util.List;

/**
 * Created by alan on 2018/12/15.
 */
public class AreaModel {

    private AreaNode province;

    private List<AreaNode> citys;

    public AreaModel(){}

    public AreaModel(AreaNode province, List<AreaNode> citys) {
        this.province = province;
        this.citys = citys;
    }

    public AreaNode getProvince() {
        return province;
    }

    public void setProvince(AreaNode province) {
        this.province = province;
    }

    public List<AreaNode> getCitys() {
        return citys;
    }

    public void setCitys(List<AreaNode> citys) {
        this.citys = citys;
    }
}
