package cn.com.sinofaith.controller.brandController;

import cn.com.sinofaith.service.brand.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/caseRegion")
public class RegionController {
    @Autowired
    private RegionService rs;

}
