package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BrandDao extends BaseDao<BrandEntity>{
    public List<BrandEntity> getByName(String brandName){
        return find("from BrandEntity where brandname ='"+brandName+"'");
    }

    public long insert(BrandEntity be){
        return (long)save(be);
    }
}
