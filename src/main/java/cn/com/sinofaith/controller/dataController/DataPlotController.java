package cn.com.sinofaith.controller.dataController;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.form.AnnualDataForm;
import cn.com.sinofaith.form.Brandperson;
import cn.com.sinofaith.form.PlotForm;
import cn.com.sinofaith.service.data.DataPlotService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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

    /**
     * 年度数据获取
     * @return
     */
    @RequestMapping("/annualData")
    public @ResponseBody Map<String,List<AnnualDataForm>> annualData(){
        // 接收数据
        Map<String,List<AnnualDataForm>> annualDatas = dpService.getMapAnnualData();
        return annualDatas;
    }

    @RequestMapping("/staff")
    public @ResponseBody List<Brandperson> staff(){
        List<Brandperson> bps = dpService.getStaff();
        return bps;
    }

    /*
     */
/**
 * 数据回显
 * @return
 *//*

    @RequestMapping("/gain")
    private @ResponseBody String gainData(){
        //先从redis缓存中查询 有直接使用 没有从数据库中查询再存到缓存中
        Jedis jedis = JedisPoolUtils.getJedis();
        String gainList = jedis.get("gainList");
        if(gainList==null){
            System.out.println("从数据库中读取gain数据");
            // 创建离线查询对象
            DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
            List<PlotForm> plotForms = dpService.getPlotForm(dc);
            Gson gson = new Gson();
            String json = gson.toJson(plotForms);
            jedis.set("gainList",json);
            gainList = json;
        }
        JedisPoolUtils.returnResource(jedis);
        return gainList;
    }

    */
/**
 * 地图数据
 * @return
 *//*

    @RequestMapping("/area")
    public @ResponseBody String areaData(){
        //先从redis缓存中查询 有直接使用 没有从数据库中查询再存到缓存中
        Jedis jedis = JedisPoolUtils.getJedis();
        String areaList = jedis.get("areaList");
        if(areaList==null){
            System.out.println("从数据库中读取area数据");
            // 创建离线查询对象
            DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
            List<PlotForm> plotForms = dpService.getPlotMapForm(dc);
            Gson gson = new Gson();
            String json = gson.toJson(plotForms);
            jedis.set("areaList",json);
            areaList = json;
        }
        JedisPoolUtils.returnResource(jedis);
        return areaList;
    }
    */
/**
 * 获取品牌
 * @return
 *//*

    @RequestMapping("/brand")
    public @ResponseBody String brandData(){
        Jedis jedis = JedisPoolUtils.getJedis();
        String brandList = jedis.get("brandList");
        if(brandList==null){
            System.out.println("从数据库中读取brand数据");
            // 创建离线查询对象
            DetachedCriteria dc = DetachedCriteria.forClass(BrandEntity.class);
            List<PlotForm> plotForms = dpService.getPlotBrandForm(dc);
            Gson gson = new Gson();
            String json = gson.toJson(plotForms);
            jedis.set("brandList",json);
            brandList = json;
        }
        JedisPoolUtils.returnResource(jedis);
        return brandList;
    }

    */
/**
 * 年度数据获取
 * @return
 *//*

    @RequestMapping("/annualData")
    public @ResponseBody String annualData(){
        Jedis jedis = JedisPoolUtils.getJedis();
        String annualDataList = jedis.get("annualDataList");
        if(annualDataList==null){
            System.out.println("从数据库中读取annualData数据");
            // 接收数据
            Map<String,List<AnnualDataForm>> annualDatas = dpService.getMapAnnualData();
            Gson gson = new Gson();
            String json = gson.toJson(annualDatas);
            jedis.set("annualDataList",json);
            annualDataList = json;
        }
        JedisPoolUtils.returnResource(jedis);
        return annualDataList;
    }

    @RequestMapping("/staff")
    public @ResponseBody String staff(){
        Jedis jedis = JedisPoolUtils.getJedis();
        String staffList = jedis.get("staffList");
        if(staffList==null) {
            System.out.println("从数据库中读取staff数据");
            List<Brandperson> bps = dpService.getStaff();
            Gson gson = new Gson();
            String json = gson.toJson(bps);
            jedis.set("staffList",json);
            staffList = json;
        }
        JedisPoolUtils.returnResource(jedis);
        return staffList;
    }
*/
}












