package cn.com.sinofaith.service.mobile;

import cn.com.sinofaith.bean.TAutoJzxxEntity;
import cn.com.sinofaith.dao.mobile.JzxxDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机主信息业务层
 */
@Service
public class JzxxService {

    @Autowired
    private JzxxDao jzxxDao;

    /**
     * 获取分页数据
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Page page = new Page();
        int rowAll = jzxxDao.getRowAll(dc);
        List<TAutoJzxxEntity> jzxxs = null;
        // 当有数据时
        if(rowAll>0){
            jzxxs = jzxxDao.getDoPage(currentPage,pageSize,dc);
            for (int i = 0; i <jzxxs.size(); i++) {
                jzxxs.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            page.setPageSize(pageSize);
            page.setTotalRecords(rowAll);
            page.setList(jzxxs);
            page.setPageNo(currentPage);
        }
        return page;
    }
}
