package cn.com.sinofaith.dao.wxPhone;

import cn.com.sinofaith.bean.TAutoQqZhxxEntity;
import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WxZhxxDao extends BaseDao<TAutoWechatZhxxEntity> {


    /**
     * 获取所有条目
     * @return
     */
    public int getRowAll() {
        Session session = getSession();
        Long rowAll = 0l;
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TAutoWechatZhxxEntity.class);
            // 设置聚合查询函数
            criteria.setProjection(Projections.countDistinct("wxh"));
            rowAll = (Long) criteria.uniqueResult();
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
    public List<TAutoWechatZhxxEntity> getDoPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Session session = getSession();
        List<TAutoWechatZhxxEntity> zhxxs = null;
        try {
            // 开启事务
            Transaction tx = session.beginTransaction();
            // 关联session
            Criteria criteria = dc.getExecutableCriteria(session);
            // 设置去重列
            ProjectionList proList = Projections.projectionList();
            proList.add(Projections.property("name"));
            proList.add(Projections.property("sfzhm"));
            proList.add(Projections.property("sjhm"));
            proList.add(Projections.property("sex"));
            proList.add(Projections.property("wxh"));
            proList.add(Projections.property("nicheng"));
            proList.add(Projections.property("qq"));
            proList.add(Projections.property("email"));
            proList.add(Projections.property("gxqm"));
            // 分页
            criteria.setFirstResult((currentPage-1)*pageSize);
            criteria.setMaxResults(pageSize);
            // 去重
            criteria.setProjection(Projections.distinct(proList));
            // 创建对象
            List list = criteria.list();
            tx.commit();
            // 数据转换
            TAutoWechatZhxxEntity zhxx = new TAutoWechatZhxxEntity();
            zhxxs = zhxx.distinctToList(list);
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }

        return zhxxs;
    }
}
