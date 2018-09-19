package cn.com.sinofaith.service.brand;

import cn.com.sinofaith.bean.UnitEntity;
import cn.com.sinofaith.dao.brand.UnitDao;
import cn.com.sinofaith.util.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    private UnitDao ud;
    public UnitEntity getByname(String unitname){
        List<UnitEntity> list = ud.getByName(unitname);
        if(list.size()>0){
            return list.get(0);
        }else {
            return new UnitEntity();
        }
    }

    public long add(String unitname){
        UnitEntity be = new UnitEntity();
        be.setUnitName(unitname);
        be.setInserttime(TimeFormatUtil.getDate("/"));
        return (long)ud.save(be);
    }
}
