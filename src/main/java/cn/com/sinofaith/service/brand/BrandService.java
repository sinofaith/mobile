package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.dao.brand.BrandDao;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandDao bd;

    public BrandEntity getByname(String brandname){
        List<BrandEntity> list = bd.getByName(brandname);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new BrandEntity();
        }
    }

    public List<String> getDopage(String brandName){
        List<BrandEntity> listb = bd.doPage("from BrandEntity where brandname like '%"+brandName+"%'",1,100);
        List<String> lists = new ArrayList<>();
        if(listb.size()>0){
            for(BrandEntity be:listb){
                lists.add(be.getBrandName());
            }
        }
        return lists;
    }

    public long add(String brandname){
        BrandEntity be = new BrandEntity();
        be.setBrandName(brandname);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        long a = bd.insert(be);
        System.out.println(a);
        return a;
    }

}
