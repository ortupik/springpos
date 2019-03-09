package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.CustomerSite;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CustomerSiteService;

@Controller
public class CustomerSiteController {

    private CustomerSiteService customerSiteService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSiteController.class);

    ArrayList<CustomerSite> customerSiteList = new ArrayList();

    @Autowired
    public void setCustomerSiteService(CustomerSiteService customerSiteService) {
        this.customerSiteService = customerSiteService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("customerSite/new")
    public String customerSitePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("customerSite", new CustomerSite());
        setInstitution(model);
        return "customerSite";
    }

    @GetMapping("/customerSites")
    public ModelAndView showCategories(CustomerSite customerSite) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customerSites");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("customerSite", new CustomerSite());
            setInstitution(mv);
            customerSiteList.clear();
            customerSiteList = (ArrayList<CustomerSite>) this.customerSiteService.findAll();
            mv.addObject("customerSites", this.customerSiteService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "customerSite")
    public String save(@Valid CustomerSite customerSite, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("customerSite", new CustomerSite());
            return "customerSite";
        }
        customerSiteService.save(customerSite);
        model.addAttribute("customerSite", new CustomerSite());
        model.addAttribute("addMessage", " CustomerSite Added Successfull ");
        return "customerSite";

    }

    @RequestMapping("/updateCustomerSite/{id}")
    public String updateCustomerSite(@PathVariable("id") int id, @Valid CustomerSite customerSite,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("customerSite", customerSite);
        this.customerSiteService.update(customerSite);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("customerSites", this.customerSiteService.findAll());
        return "customerSites";
    }

    @GetMapping("/removeCustomerSite/{id}")
    public String deleteCustomerSite(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        CustomerSite customerSite = this.customerSiteService.find(id);
        if (customerSite == null) {
            model.addAttribute("addMessage", "Invalid customerSite Id:" + id);
            model.addAttribute("customerSites", this.customerSiteService.findAll());
            return "customerSites";
        }
        this.customerSiteService.delete(customerSite);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("customerSites", this.customerSiteService.findAll());
        return "customerSites";
    }

    @GetMapping("/editCustomerSite/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        CustomerSite customerSite = this.customerSiteService.find(id);
        if (customerSite == null) {
            model.addAttribute("addMessage", "Invalid customerSite Id:" + id);
            model.addAttribute("customerSites", this.customerSiteService.findAll());
            return "customerSites";
        }
        model.addAttribute("customerSite", customerSite);
        return "updateCustomerSite";
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