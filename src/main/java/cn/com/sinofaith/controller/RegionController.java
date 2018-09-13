package cn.com.sinofaith.controller;

import cn.com.sinofaith.service.brand.RegionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService rs;
    @RequestMapping(value = "/getRegionName",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRegionName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(rs.getDopage(term));
    }
}
