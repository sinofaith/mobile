package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.dao.brand.RegionDao;
import cn.com.sinofaith.form.CaseRegionForm;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Service
public class RegionService {
    @Autowired
    private RegionDao rd;
    public RegionEntity getRegion(long unitId,String regionName){
        DetachedCriteria dc = DetachedCriteria.forClass(RegionEntity.class);
        dc.add(Restrictions.eq("unitId",unitId));
        dc.add(Restrictions.eq("regionName",regionName));
        List<RegionEntity> list = rd.getDoPage(1,10,dc);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new RegionEntity();
        }

    }

    public String getSeach(String seachCode, String seachCondition, BrandEntity be){
        StringBuffer seach = new StringBuffer();

        if(seachCode!=null){
            seachCode = seachCode.replace("\r\n","").replace("，","").replace(" ","").replace(" ","").replace("\t","");
                seach.append(" and c." + seachCondition + " like '%"  + seachCode + "%'");
        }else{
            seach.append(" and ( 1=1 ) ");
        }
        seach.append(" and c.unit_id ="+be.getBrandId());
        return seach.toString();
    }

    public Page queryForPage(int currentPage, int pageSize, String seach){
        Page page = new Page();
        List<CaseRegionForm> zzFs = new ArrayList<>();
        CaseRegionForm zzf = new CaseRegionForm();
        int allRow = rd.getAllRowCount(seach);
        if(allRow>0){
            List zzList = rd.getDoPage(seach,currentPage,pageSize);
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

    public RegionEntity getById(long regionId){
        RegionEntity re = rd.get(regionId);
        return re;
    }

    public RegionEntity getByname(String regionname){
        List<RegionEntity> list = rd.getByName(regionname);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new RegionEntity();
        }
    }

    public List<String> getDopage(String regionName,String type){
        List<RegionEntity> listr = rd.doPage("from RegionEntity where "+type+" like '%"+regionName+"%'",1,100);
        List<String> lists = new ArrayList<>();
        if(listr.size()>0){
            for(RegionEntity re:listr){
                if("region_name".equals(type)) {
                    lists.add(re.getRegionName());
                }else if("role_name".equals(type)){
                    lists.add(re.getRoleName());
                }
            }
        }
        LinkedHashSet<String> set = new LinkedHashSet<String>(lists.size());
        set.addAll(lists);
        lists.clear();
        lists.addAll(set);
        return lists;
    }

    public long add(long unitId,String regionName,String roleName){
        RegionEntity be = new RegionEntity();
        be.setUnitId(unitId);
        be.setRegionName(regionName);
        be.setRoleName(roleName);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        return (long)rd.save(be);
    }
}
