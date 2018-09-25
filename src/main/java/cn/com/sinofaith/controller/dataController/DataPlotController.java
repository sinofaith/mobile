package cn.com.sinofaith.controller.dataController;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.bean.CaseEntity;
import cn.com.sinofaith.form.PlotForm;
import cn.com.sinofaith.service.data.DataPlotService;
import com.google.gson.Gson;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataPlotController {

    @Autowired
    private DataPlotService dpService;

    @RequestMapping()
    public ModelAndView data(){
        ModelAndView mav = new ModelAndView("data/dataPlot");
        return mav;
    }

    /**
     * 数据回显
     * @return
     */
    @RequestMapping("/gain")
    private @ResponseBody List<PlotForm> gainData(){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
        List<PlotForm> plotForms = dpService.getPlotForm(dc);
        return plotForms;
    }

    /**
     * 地图数据
     * @return
     */
    @RequestMapping("/area")
    public @ResponseBody List<PlotForm> areaData(){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
        List<PlotForm> plotForms = dpService.getPlotMapForm(dc);
        return plotForms;
    }
    /**
     * 获取品牌
     * @return
     */
    @RequestMapping("/brand")
    public @ResponseBody List<PlotForm> brandData(){
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
        List<PlotForm> plotForms = dpService.getPlotBrandForm(dc);
        return plotForms;
    }
}
