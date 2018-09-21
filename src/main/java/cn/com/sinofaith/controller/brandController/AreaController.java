package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.service.brand.AreaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/Area")
@Controller
public class AreaController {
    @Autowired
    private AreaService as;
    @RequestMapping(value = "/getArea",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRegion(){
        Gson gson = new Gson();
        return gson.toJson(as.getRegion());
    }
}
