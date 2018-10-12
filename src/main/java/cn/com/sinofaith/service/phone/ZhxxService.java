package cn.com.sinofaith.service.phone;

import cn.com.sinofaith.bean.TAutoQqZhxxEntity;
import cn.com.sinofaith.dao.phone.ZhxxDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  qq账户信息业务层
 */
@Service
public class ZhxxService {
    @Autowired
    private ZhxxDao zhxxDao;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, DetachedCriteria dc) {
        // 分页对象
        Page page = new Page();
        // 获取总条数
        int rowAll = zhxxDao.getRowCount(dc);
        // 封装wuliu_relation表
        List<TAutoQqZhxxEntity> zhxxs = null;
        if (rowAll>0) {
            zhxxs = zhxxDao.getDoPage(currentPage,pageSize,dc);
            for (int i = 0; i <zhxxs.size(); i++) {
                 zhxxs.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            // 封装page
            page.setPageSize(pageSize);
            page.setTotalRecords(rowAll);
            page.setList(zhxxs);
            page.setPageNo(currentPage);
            return page;
        }
        return page;
    }
}
