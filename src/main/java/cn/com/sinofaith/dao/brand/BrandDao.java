package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class BrandDao extends BaseDao<BrandEntity>{

    public int getAllRowCount(String seachCode) {
        String sql = "select to_char(count(c.brand_id)) num from t_brand c where 1=1" + seachCode;
        List list = findBySQL(sql);
        Map map = (Map) list.get(0);
        return Integer.parseInt((String) map.get("NUM"));
    }

    public List getDoPage(String seach, int offset, int length) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM (SELECT a.*, ROWNUM rn ");
        sql.append("FROM (select c.* from t_brand c ");
        sql.append(" where 1=1 " + seach + ") a ");
        sql.append("WHERE ROWNUM <= " + offset * length + ") WHERE rn >= " + ((offset - 1) * length + 1));
        return findBySQL(sql.toString());
    }


    public List<BrandEntity> getByName(String brandName,String unitName){
        return find("from BrandEntity where brand_name ='"+brandName+"' and unit_name='"+unitName+"'");
    }

    public long insert(BrandEntity be){
        long a = (long)save(be);
        return a;
    }
}
