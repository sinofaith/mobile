package cn.com.sinofaith.dao.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class WxZhxxDao extends BaseDao<TAutoWechatZhxxEntity> {


    /**
     * 获取所有条目
     * @return
     */
    /*public int getRowAll() {
        Session session = getSession();
        Long rowAll = 0l;
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TAutoQqZhxxEntity.class);
            // 设置聚合查询函数
            criteria.setProjection(Projections.rowCount());
            rowAll = (Long) criteria.uniqueResult();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return rowAll.intValue();
    }*/

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    /*public List<TAutoQqZhxxEntity> getDoPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Session session = getSession();
        List<TAutoQqZhxxEntity> zhxxs = null;
        try {
            // 开启事务
            Transaction tx = session.beginTransaction();
            // 关联session
            Criteria criteria = dc.getExecutableCriteria(session);
            criteria.setFirstResult((currentPage-1)*pageSize);
            criteria.setMaxResults(pageSize);
            // 创建对象
            zhxxs = criteria.list();
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
        return zhxxs;
    }*/
}
