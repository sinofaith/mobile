package cn.com.sinofaith.dao.data;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.PlotForm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataPlotDao extends BaseDao<BrandEntity>{

    /**
     * 获取绘图数据
     * @return
     */
    public List<PlotForm> getPlotForm() {
        String sql = "select unit_name name,count(1) value from T_BRAND group by unit_name";
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql)
                    .addScalar("name")
                    .addScalar("value", StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }

    /**
     * 地图数据
     * @return
     */
    public List<PlotForm> getPlotMapForm() {
        StringBuffer sql = new StringBuffer();
        sql.append("select brand_name,CONCAT(case_name,name1) as synthesize,name,value from (");
        sql.append("select b.brand_name,concat('案件:',c.case_name) case_name,concat(' 市:',a1.name) name1,a1.name,count(*) value from t_brand b");
        sql.append(" inner join t_case c on b.brand_id = c.brand_id left join t_region r on c.case_id = r. case_id");
        sql.append(" left join t_area a on r.area_id = a.id left join t_area a1 on a1.id = a.pid");
        sql.append(" group by  b.brand_name,c.case_name,a1.name)");
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql.toString())
                    .addScalar("brand_name")
                    .addScalar("synthesize")
                    .addScalar("name")
                    .addScalar("value", StandardBasicTypes.LONG)
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }

    public List<PlotForm> getPlotBrandForm() {
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct(b.brand_name) from t_brand b inner join t_case c on b.brand_id = c.brand_id ");
        sql.append(" left join t_region r on c.case_id = r. case_id left join t_area a on r.area_id = a.id");
        List<PlotForm> plotForms = null;
        // 获取session
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            plotForms = session.createSQLQuery(sql.toString())
                    .addScalar("brand_name")
                    .setResultTransformer(Transformers.aliasToBean(PlotForm.class)).list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return plotForms;
    }
}
