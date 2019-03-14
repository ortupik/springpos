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
        setInstitution(mv);
        return mv;
    }

    @RequestMapping("/createOrder")
    public ModelAndView createOrder() {
        ModelAndView mv = new ModelAndView("createOrder");
        setInstitution(mv);
        return mv;
    }

    @RequestMapping("/newCustomer")
    public ModelAndView newOrder() {
        ModelAndView mv = new ModelAndView("newOrder");
        mv.addObject("customerSite", new CustomerSite());
        setInstitution(mv);
        return mv;
    }

    @RequestMapping("/regularCustomer")
    public ModelAndView regularCustomer() {
        ModelAndView mv = new ModelAndView("regularCustomer");
        mv.addObject("customerSite", new CustomerSite());
        mv.addObject("customerSites", this.customerSiteService.findAll());
        setInstitution(mv);
        return mv;
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return mainService.getLoggedOut();
    }

    void setInstitution(Model institution) {
        institution.addAttribute("institution", this.mainService.institutionName());
        institution.addAttribute("motto", this.mainService.institutionMotto());
    }

    void setInstitution(ModelAndView institution) {
        institution.addObject("institution", this.mainService.institutionName());
        institution.addObject("motto", this.mainService.institutionMotto());
    }
}
