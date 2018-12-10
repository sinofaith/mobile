package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.bean.BrandEntity;
import cn.com.sinofaith.page.Page;
import cn.com.sinofaith.service.brand.BrandService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/caseBrand")
public class BrandController {
    @Autowired
    private BrandService bs;

    @RequestMapping()
    public ModelAndView home(HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/caseBrand/seach?pageNo=1");
        httpSession.removeAttribute("cbseachCode");
        httpSession.removeAttribute("cbseachCondition");

        return mav;
    }

    @RequestMapping(value = "/seach")
    public ModelAndView getCase(@RequestParam("pageNo") String pageNo, HttpServletRequest req){
        ModelAndView mav = new ModelAndView("case/caseBrand");

        String seachCondition = (String) req.getSession().getAttribute("cbseachCondition");
        String seachCode = (String) req.getSession().getAttribute("cbseachCode");
        String seach = bs.getSeach(seachCode,seachCondition);
        Page page = bs.queryForPage(parseInt(pageNo),10,seach);
        mav.addObject("page",page);
        mav.addObject("cbseachCode",seachCode);
        mav.addObject("cbseachCondition",seachCondition);
        mav.addObject("detailinfo",page.getList());
        return mav;
    }
    @RequestMapping(value = "/SeachCode" , method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView seachCode(String seachCode,String seachCondition,HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/caseBrand/seach?pageNo=1");
        if(seachCode == null || seachCode.isEmpty()){
            httpSession.removeAttribute("cbseachCode");
            httpSession.removeAttribute("cbseachCondition");
            return mav;
        }

        httpSession.setAttribute("cbseachCondition",seachCondition);
        httpSession.setAttribute("cbseachCode",seachCode);
        return mav;
    }
    @RequestMapping(value = "/brand")
    public ModelAndView jump(@RequestParam("brandName") String brandName,@RequestParam("unitName") String unitName, HttpSession httpSession){
        ModelAndView mav = new ModelAndView("redirect:/case/seach?pageNo=1");

        httpSession.removeAttribute("rbseachCode");
        httpSession.removeAttribute("rbseachCondition");
        httpSession.setAttribute("brand",bs.getByname(brandName,unitName));
        return mav;
    }


    @RequestMapping(value = "/getBrandName" ,method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getBrandName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(bs.getDopage(term,"brand_name"));
    }

    @RequestMapping(value = "/getUnitName" ,method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getUnitName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(bs.getDopage(term,"unit_name"));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam("brandname")String brandname,
                      @RequestParam("unitname") String unitname){

        BrandEntity be = bs.getByname(brandname,unitname);
        if(be.getBrandId()==-1){
            bs.add(brandname,unitname);
            // 删除gainList
//            Jedis jedis = JedisPoolUtils.getJedis();
//            jedis.del("gainList");
//            jedis.del("annualDataList");
//            JedisPoolUtils.returnResource(jedis);
            return "200";
        }else {
            return "303";
        }
    }
}
