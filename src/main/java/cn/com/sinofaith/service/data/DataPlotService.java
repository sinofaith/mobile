package cn.com.sinofaith.service.data;

import cn.com.sinofaith.dao.data.DataPlotDao;
import cn.com.sinofaith.form.AnnualDataForm;
import cn.com.sinofaith.form.Brandperson;
import cn.com.sinofaith.form.PlotForm;
import cn.com.sinofaith.form.StaffForm;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataPlotService {
    @Autowired
    private DataPlotDao dataPlotDao;

    /**
     * 获取绘图数据
     * @param dc
     * @return
     */
    public List<PlotForm> getPlotForm(DetachedCriteria dc) {
        List<PlotForm> plotForms = null;
        // 获取表中总条数
        int rowAll = dataPlotDao.getRowAll(dc);
        if(rowAll>0){
            plotForms = dataPlotDao.getPlotForm();
        }
        return plotForms;
    }

    /**
     * 地图数据
     * @param dc
     * @return
     */
    public List<PlotForm> getPlotMapForm(DetachedCriteria dc) {
        List<PlotForm> plotForms = null;
        // 获取表中总条数
        int rowAll = dataPlotDao.getRowAll(dc);
        if(rowAll>0){
            plotForms = dataPlotDao.getPlotMapForm();
        }
        return plotForms;
    }

    public List<PlotForm> getPlotBrandForm(DetachedCriteria dc) {
        List<PlotForm> plotForms = null;
        // 获取表中总条数
        int rowAll = dataPlotDao.getRowAll(dc);
        if(rowAll>0){
            plotForms = dataPlotDao.getPlotBrandForm();
        }
        return plotForms;
    }

    /**
     * 年度数据
     * @return
     */
    public Map<String,List<AnnualDataForm>> getMapAnnualData() {
        Map<String,List<AnnualDataForm>> mapAnnualData = dataPlotDao.getMapAnnualData();
        return mapAnnualData;
    }

    /**
     * 人员数据
     * @return
     */
    public List<Brandperson> getStaff() {
        List<Brandperson> bps = dataPlotDao.getStaff();
        for (Brandperson bp : bps) {
            bp.setNum(bp.getQfriendNum(),bp.getQltjlNum(),bp.getWfriendNum(),
                    bp.getWltjlNum(),bp.getDxNum(),bp.getThqdNum(),bp.getTxlNum());
        }
        return bps;
    }
}
