package cn.com.sinofaith.service.wxPhone;

import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.bean.TAutoWechatZhxxEntity;
import cn.com.sinofaith.dao.phone.ZhxxDao;
import cn.com.sinofaith.dao.wxPhone.WxZhxxDao;
import cn.com.sinofaith.page.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  qq账户信息业务层
 */
@Service
public class WxZhxxService {
    @Autowired
    private WxZhxxDao zhxxDao;

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
        int rowAll = zhxxDao.getRowAll(dc);
        // 封装wuliu_relation表
        List<TAutoWechatZhxxEntity> zhxxs = null;
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
