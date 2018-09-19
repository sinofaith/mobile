package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.UnitEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitDao extends BaseDao<UnitEntity>{

    public List<UnitEntity> getByName(String unitname){
        return find("from UnitEntity where unit_name ='"+unitname+"'");
    }
}
