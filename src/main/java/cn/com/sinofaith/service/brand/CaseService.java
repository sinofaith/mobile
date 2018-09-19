package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.dao.brand.CaseDao;
import cn.com.sinofaith.form.CaseForm;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.DBUtil;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CaseService {
    @Autowired
    private CaseDao cd;

    public CaseEntity getCase(String caseName,long regionId){
        DetachedCriteria dc = DetachedCriteria.forClass(CaseEntity.class);
        dc.add(Restrictions.eq("caseName",caseName));
        dc.add(Restrictions.eq("regionId",regionId));
        List<CaseEntity> list = cd.getDoPage(1,10,dc);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new CaseEntity();
        }
    }

    public List<CaseEntity> getCaseByName(String casename){
       return cd.find("from CaseEntity where casename = '"+casename+"'");
    }

    public Page queryForPage(int currentPage, int pageSize, String seach){
        Page page = new Page();
        List<CaseForm> zzFs = new ArrayList<>();
        CaseForm zzf = new CaseForm();
        int allRow = cd.getAllRowCount(seach);
        if(allRow>0){
            List zzList = cd.getDoPage(seach,currentPage,pageSize);
            int xh = 1;
            for(int i=0;i<zzList.size();i++) {
                Map map = (Map) zzList.get(i);
                zzf = zzf.mapToForm(map);
                zzf.setXh(xh+(currentPage-1)*pageSize);
                zzFs.add(zzf);
                xh++;
            }
        }
        page.setPageSize(pageSize);
        page.setPageNo(currentPage);
        page.setTotalRecords(allRow);
        page.setList(zzFs);
        return page;
    }


    public String getSeach(String seachCode, String seachCondition){
        StringBuffer seach = new StringBuffer();

        if(seachCode!=null){
            seachCode = seachCode.replace("\r\n","").replace("，","").replace(" ","").replace(" ","").replace("\t","");
            if("casename".equals(seachCondition)){
                seach.append(" and c."+seachCondition + " like '%"+seachCode+"%'");
            } else if("brandname".equals(seachCondition)){
                seach.append(" and b." + seachCondition + " like '%"  + seachCode + "%'");
            }else if("regionname".equals(seachCondition)){
                seach.append(" and r."+seachCondition+" like '%"+seachCode+"%'");
            }
        }else{
            seach.append(" and ( 1=1 ) ");
        }

        return seach.toString();
    }

    public long add(String caseName,String creater,long region_id){
        CaseEntity be = new CaseEntity();
        be.setCaseName(caseName);
        be.setCreater(creater);
        be.setRegionId(region_id);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        return (long)cd.save(be);
    }
}
