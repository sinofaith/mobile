package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.bean.RoleEntity;
import cn.com.sinofaith.dao.brand.RegionDao;
import cn.com.sinofaith.dao.brand.RoleDao;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegionService {
    @Autowired
    private RegionDao rd;
    @Autowired
    private RoleDao roleDao;

     public void add(long caseId,String areaId){
        String[] listArea = areaId.split(",");
        for(int i=0;i<listArea.length;i++){
            RegionEntity re = new RegionEntity();
            re.setCaseId(caseId);
            re.setAreaId(Long.parseLong(listArea[i]));
            re.setInserttime(TimeFormatUtil.getDate("/"));
            rd.save(re);
        }
    }

    /**
     * 获取分页对象
     * @param currentPage
     * @param pageSize
     * @param dc
     * @return
     */
    public Page getDoPage(int currentPage, int pageSize, DetachedCriteria dc) {
        int rowAll = rd.getRowAll(dc);
        Page page = null;
        if(rowAll>0){
            page = new Page();
            List<RoleEntity> roles = roleDao.getDoPage(currentPage, pageSize, dc);
            page.setPageNo(currentPage);
            page.setTotalRecords(rowAll);
            page.setPageSize(pageSize);
            page.setList(roles);
        }
        return page;
    }
}
