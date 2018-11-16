package cn.com.sinofaith.dao.mobile;

import cn.com.sinofaith.bean.TAutoJzxxEntity;
import cn.com.sinofaith.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机主信息持久层
 */
@Repository
public class JzxxDao extends BaseDao<TAutoJzxxEntity>{

    /**
     * 获取要修改的机主信息
     * @param id
     * @return
     */
    public TAutoJzxxEntity getEditPerson(long id) {
        List list = find("from TAutoJzxxEntity where id=" + id);
        TAutoJzxxEntity jzxxEntity = (TAutoJzxxEntity) list.get(0);
        return jzxxEntity;
    }
}
