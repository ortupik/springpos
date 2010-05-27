package com.springpos.controller;

import com.springpos.bean.CustomerSite;
import com.springpos.service.CustomerSiteService;
import com.springpos.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwService;

@Controller
public class MainController {

    private HwService hwService;

    private MainService mainService;

    private CustomerSiteService customerSiteService;

    @Autowired
    public void setHwService(HwService hwService) {
        this.hwService = hwService;
    }

    @Autowired
    public void setCustomerSiteService(CustomerSiteService customerSiteService) {
        this.customerSiteService = customerSiteService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mainService.setInstitution(mv);
        return mv;
    }

  
    @GetMapping("/logout")
    public String logoutPage() {
        return mainService.getLoggedOut();
    }
 
}
