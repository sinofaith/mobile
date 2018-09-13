package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RegionDao extends BaseDao<RegionEntity> {

    public List<RegionEntity> getByName(String regionname){
        return find("from RegionEntity where regionname ='"+regionname+"'");
    }
}
