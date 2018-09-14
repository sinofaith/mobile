package cn.com.sinofaith.service.mobile;

import cn.com.sinofaith.bean.TAutoDxEntity;
import cn.com.sinofaith.bean.TAutoThqdEntity;
import cn.com.sinofaith.dao.mobile.ThqdDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThqdService {

    @Autowired
    private ThqdDao thqdDao;

    /**
     * 获取分页数据
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Page page = new Page();
        int rowAll = thqdDao.getRowAll(dc);
        List<TAutoThqdEntity> jzxxs = null;
        // 当有数据时
        if(rowAll>0){
            jzxxs = thqdDao.getDoPage(currentPage,pageSize,dc);
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
