package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.RegionEntity;
import cn.com.sinofaith.dao.brand.RegionDao;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegionService {
    @Autowired
    private RegionDao rd;
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
}
