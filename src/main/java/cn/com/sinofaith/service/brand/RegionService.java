package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.dao.brand.RegionDao;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionDao rd;

    public RegionEntity getByname(String regionname){
        List<RegionEntity> list = rd.getByName(regionname);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    public List<String> getDopage(String regionName){
        List<RegionEntity> listr = rd.doPage("from RegionEntity where regionname like '%"+regionName+"%'",1,100);
        List<String> lists = new ArrayList<>();
        if(listr.size()>0){
            for(RegionEntity re:listr){
                lists.add(re.getRegionName());
            }
        }
        return lists;
    }

    public long add(String regionname){
        RegionEntity be = new RegionEntity();
        be.setRegionName(regionname);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        return (long)rd.save(be);
    }
}
