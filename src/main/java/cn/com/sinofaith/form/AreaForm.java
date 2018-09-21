package cn.com.sinofaith.form;

import cn.com.sinofaith.bean.AreaEntity;

import java.util.List;


public class AreaForm {
    private long id;
    private String pName;
    private List<AreaEntity> listCity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public List<AreaEntity> getListCity() {
        return listCity;
    }

    public void setListCity(List<AreaEntity> listCity) {
        this.listCity = listCity;
    }
}
