package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.CustomerSiteHw;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CustomerSiteHwService;

@Controller
public class CustomerSiteHwController {

    private CustomerSiteHwService customerSiteHwService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSiteHwController.class);

    ArrayList<CustomerSiteHw> customerSiteHwList = new ArrayList();

    @Autowired
    public void setCustomerSiteHwService(CustomerSiteHwService customerSiteHwService) {
        this.customerSiteHwService = customerSiteHwService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("customerSiteHw/new")
    public String customerSiteHwPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("customerSiteHw", new CustomerSiteHw());
        mainService.setInstitution(model);
        return "customerSiteHw";
    }

    @GetMapping("/customerSiteHws")
    public ModelAndView showCategories(CustomerSiteHw customerSiteHw) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customerSiteHws");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("customerSiteHw", new CustomerSiteHw());
            mainService.setInstitution(mv);
            customerSiteHwList.clear();
            customerSiteHwList = (ArrayList<CustomerSiteHw>) this.customerSiteHwService.findAll();
            mv.addObject("customerSiteHws", this.customerSiteHwService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "customerSiteHw")
    public String save(@Valid CustomerSiteHw customerSiteHw, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("customerSiteHw", new CustomerSiteHw());
            return "customerSiteHw";
        }
        customerSiteHwService.save(customerSiteHw);
        model.addAttribute("customerSiteHw", new CustomerSiteHw());
        model.addAttribute("addMessage", " CustomerSiteHw Added Successfull ");
        return "customerSiteHw";

    }

    @RequestMapping("/updateCustomerSiteHw/{id}")
    public String updateCustomerSiteHw(@PathVariable("id") int id, @Valid CustomerSiteHw customerSiteHw,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("customerSiteHw", customerSiteHw);
        this.customerSiteHwService.update(customerSiteHw);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("customerSiteHws", this.customerSiteHwService.findAll());
        return "customerSiteHws";
    }

    @GetMapping("/removeCustomerSiteHw/{id}")
    public String deleteCustomerSiteHw(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        CustomerSiteHw customerSiteHw = this.customerSiteHwService.find(id);
        if (customerSiteHw == null) {
            model.addAttribute("addMessage", "Invalid customerSiteHw Id:" + id);
            model.addAttribute("customerSiteHws", this.customerSiteHwService.findAll());
            return "customerSiteHws";
        }
        this.customerSiteHwService.delete(customerSiteHw);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("customerSiteHws", this.customerSiteHwService.findAll());
        return "customerSiteHws";
    }

    @GetMapping("/editCustomerSiteHw/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        CustomerSiteHw customerSiteHw = this.customerSiteHwService.find(id);
        if (customerSiteHw == null) {
            model.addAttribute("addMessage", "Invalid customerSiteHw Id:" + id);
            model.addAttribute("customerSiteHws", this.customerSiteHwService.findAll());
            return "customerSiteHws";
        }
        model.addAttribute("customerSiteHw", customerSiteHw);
        return "updateCustomerSiteHw";
    }


}
