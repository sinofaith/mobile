package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.brand.BrandDao;
import cn.com.sinofaith.form.BrandUnitForm;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {
    @Autowired
    private BrandDao bd;


    public String getSeach(String seachCode, String seachCondition){
        StringBuffer seach = new StringBuffer();

        if(seachCode!=null){
            seachCode = seachCode.replace("\r\n","").replace("，","").replace(" ","").replace(" ","").replace("\t","");
                seach.append(" and c."+seachCondition + " like '%"+seachCode+"%'");

        }else{
            seach.append(" and ( 1=1 ) ");
        }

        return seach.toString();
    }

    public Page queryForPage(int currentPage, int pageSize, String seach){
        Page page = new Page();
        List<BrandUnitForm> zzFs = new ArrayList<>();
        BrandUnitForm zzf = new BrandUnitForm();
        int allRow = bd.getAllRowCount(seach);
        if(allRow>0){
            List zzList = bd.getDoPage(seach,currentPage,pageSize);
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

    public BrandEntity getByname(String brandname,String unitname){
        List<BrandEntity> list = bd.getByName(brandname,unitname);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new BrandEntity();
        }
    }

    public List<String> getDopage(String brandName,String type){

        List<BrandEntity> listb = bd.doPage("from BrandEntity where "+type+" like '%"+brandName+"%'",1,100);
        List<String> lists = new ArrayList<>();
        if(listb.size()>0){
            for(BrandEntity be:listb){
                if("brand_name".equals(type)) {
                    lists.add(be.getBrandName());
                }else if("unit_name".equals(type)){
                    lists.add(be.getUnitName());
                }
            }
        }
        LinkedHashSet<String> set = new LinkedHashSet<String>(lists.size());
        set.addAll(lists);
        lists.clear();
        lists.addAll(set);
        return  lists;
    }

    public long add(String brandname,String unitname){
        BrandEntity be = new BrandEntity();
        be.setBrandName(brandname);
        be.setUnitName(unitname);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        long a = bd.insert(be);
        return a;
    }

}
