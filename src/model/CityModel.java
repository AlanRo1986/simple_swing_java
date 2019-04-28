package model;

import java.util.List;

/**
 * Created by alan on 2018/12/15.
 */
public class CityModel {

    private AreaNode city;

    private List<AreaNode> areas;

    public CityModel(){}

    public CityModel(AreaNode city, List<AreaNode> areas) {
        this.city = city;
        this.areas = areas;
    }

    public AreaNode getCity() {
        return city;
    }

    public void setCity(AreaNode city) {
        this.city = city;
    }

    public List<AreaNode> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaNode> areas) {
        this.areas = areas;
    }
}
