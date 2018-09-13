package cn.com.sinofaith.controller;

import cn.com.sinofaith.service.brand.BrandService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService bs;

    @RequestMapping(value = "/getBrandName" ,method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getBrandName(@RequestParam("term")String term){
        Gson gson = new Gson();
        return gson.toJson(bs.getDopage(term));
    }
}
