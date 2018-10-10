package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.AreaEntity;
import cn.com.sinofaith.dao.brand.AreaDao;
import cn.com.sinofaith.form.AreaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaDao ad;
    public List<AreaForm> getRegion(){
        List<AreaForm> listRf = new ArrayList<>();
        List<AreaEntity> listp = ad.find("from AreaEntity where type = 1");
        for(int i=0;i<listp.size();i++){
            AreaEntity ae = listp.get(i);
            AreaForm rf = new AreaForm();
            rf.setId(ae.getId());
            rf.setpName(ae.getName());
            listRf.add(rf);
        }
        List<AreaEntity> listc = ad.find("from AreaEntity where type=2");
        for (int j=0;j<listRf.size();j++){
            AreaForm rf = listRf.get(j);
            List<AreaEntity> res = new ArrayList<>();
            for(int n=0;n<listc.size();n++){
                AreaEntity ae = listc.get(n);
                if(rf.getId() == ae.getPid()){
                    res.add(ae);
                }
            }
            rf.setListCity(res);
        }

        return listRf;
    }
}
