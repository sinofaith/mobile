package cn.com.sinofaith.service.mobile;

import cn.com.sinofaith.bean.TAutoTxlEntity;
import cn.com.sinofaith.dao.mobile.TxlDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通讯录信息业务层
 */
@Service
public class TxlService {

    @Autowired
    private TxlDao txlDao;

    /**
     * 获取分页数据
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page queryForPage(int currentPage, int pageSize, DetachedCriteria dc) {
        Page page = new Page();
        int rowAll = txlDao.getRowAll(dc);
        List<TAutoTxlEntity> txls = null;
        // 当有数据时
        if(rowAll>0){
            txls = txlDao.getDoPage(currentPage,pageSize,dc);
            for (int i = 0; i <txls.size(); i++) {
                txls.get(i).setId((currentPage-1)*pageSize+i+1);
            }
            page.setPageSize(pageSize);
            page.setTotalRecords(rowAll);
            page.setList(txls);
            page.setPageNo(currentPage);
        }
        return page;
    }
}