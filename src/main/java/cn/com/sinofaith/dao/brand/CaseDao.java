package cn.com.sinofaith.dao.brand;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CaseDao extends BaseDao<CaseEntity>{

    public int getAllRowCount(String seachCode) {
        String sql = "select to_char(count(c.case_id)) num from t_case c " +
                " left join t_region r on c.region_id = r.region_id where 1=1 " + seachCode;
        List list = findBySQL(sql);
        Map map = (Map) list.get(0);
        return Integer.parseInt((String) map.get("NUM"));
    }


    public List getDoPage(String seach, int offset, int length) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM (SELECT a.*, ROWNUM rn ");
        sql.append("FROM (select c.* from t_case c ");
        sql.append(" where 1=1 " + seach + ") a ");
        sql.append("WHERE ROWNUM <= " + offset * length + ") WHERE rn >= " + ((offset - 1) * length + 1));
        return findBySQL(sql.toString());
    }
}
