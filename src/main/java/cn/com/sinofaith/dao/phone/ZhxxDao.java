package cn.com.sinofaith.dao.phone;

import cn.com.sinofaith.bean.TAutoQqZhxxEntity;
import cn.com.sinofaith.dao.BaseDao;
import cn.com.sinofaith.form.AnnualDataForm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ZhxxDao extends BaseDao<TAutoQqZhxxEntity> {


    /**
     * 获取所有条目
     * @return
     */
    public int getRowCount(DetachedCriteria dc) {
        Session session = getSession();
        Long rowAll = 0l;
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = dc.getExecutableCriteria(session);
            // 设置聚合查询函数
            criteria.setProjection(Projections.countDistinct("qq"));
            rowAll = (Long) criteria.uniqueResult();
            // 将条件清空，用于dc查询分页数据
            criteria.setProjection(null);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return rowAll.intValue();
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public List<TAutoQqZhxxEntity> getDoPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Session session = getSession();
        List<TAutoQqZhxxEntity> zhxxs = null;
        try {
            // 关联session
            Criteria criteria = dc.getExecutableCriteria(session);
            // 开启事务
            Transaction tx = session.beginTransaction();
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("name"));
            proList.add(Projections.property("sfzhm"));
            proList.add(Projections.property("sjhm"));
            proList.add(Projections.property("sex"));
            proList.add(Projections.property("qq"));
            proList.add(Projections.property("nicheng"));
            proList.add(Projections.property("dataType"));
            proList.add(Projections.property("age"));
            proList.add(Projections.property("szd"));
            proList.add(Projections.property("birthday"));
            proList.add(Projections.property("gxqm"));
            criteria.setProjection(Projections.distinct(proList));
            criteria.setFirstResult((currentPage-1)*pageSize);
            criteria.setMaxResults(pageSize);
            List list = criteria.list();
            // 创建对象
            tx.commit();
            TAutoQqZhxxEntity zhxx = new TAutoQqZhxxEntity();
            zhxxs = zhxx.distinctToList(list);
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return zhxxs;
    }
}
