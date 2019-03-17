package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.CustomerSiteStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CustomerSiteStatusService;

@Controller
public class CustomerSiteStatusController {

    private CustomerSiteStatusService customerSiteStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSiteStatusController.class);

    ArrayList<CustomerSiteStatus> customerSiteStatusList = new ArrayList();

    @Autowired
    public void setCustomerSiteStatusService(CustomerSiteStatusService customerSiteStatusService) {
        this.customerSiteStatusService = customerSiteStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("customerSiteStatus/new")
    public String customerSiteStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("customerSiteStatus", new CustomerSiteStatus());
        mainService.setInstitution(model);
        return "customerSiteStatus";
    }

    @GetMapping("/customerSiteStatuss")
    public ModelAndView showCategories(CustomerSiteStatus customerSiteStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customerSiteStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("customerSiteStatus", new CustomerSiteStatus());
            mainService.setInstitution(mv);
            customerSiteStatusList.clear();
            customerSiteStatusList.addAll(this.customerSiteStatusService.findAll());
            mv.addObject("customerSiteStatuss", this.customerSiteStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "customerSiteStatus")
    public String save(@Valid CustomerSiteStatus customerSiteStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("customerSiteStatus", new CustomerSiteStatus());
            return "customerSiteStatus";
        }
        customerSiteStatusService.save(customerSiteStatus);
        model.addAttribute("customerSiteStatus", new CustomerSiteStatus());
        model.addAttribute("addMessage", " CustomerSiteStatus Added Successfull ");
        return "customerSiteStatus";

    }

    @RequestMapping("/updateCustomerSiteStatus/{id}")
    public String updateCustomerSiteStatus(@PathVariable("id") int id, @Valid CustomerSiteStatus customerSiteStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("customerSiteStatus", customerSiteStatus);
        this.customerSiteStatusService.update(customerSiteStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("customerSiteStatuss", this.customerSiteStatusService.findAll());
        return "customerSiteStatuss";
    }

    @GetMapping("/removeCustomerSiteStatus/{id}")
    public String deleteCustomerSiteStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        CustomerSiteStatus customerSiteStatus = this.customerSiteStatusService.find(id);
        if (customerSiteStatus == null) {
            model.addAttribute("addMessage", "Invalid customerSiteStatus Id:" + id);
            model.addAttribute("customerSiteStatuss", this.customerSiteStatusService.findAll());
            return "customerSiteStatuss";
        }
        this.customerSiteStatusService.delete(customerSiteStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("customerSiteStatuss", this.customerSiteStatusService.findAll());
        return "customerSiteStatuss";
    }

    @GetMapping("/editCustomerSiteStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        CustomerSiteStatus customerSiteStatus = this.customerSiteStatusService.find(id);
        if (customerSiteStatus == null) {
            model.addAttribute("addMessage", "Invalid customerSiteStatus Id:" + id);
            model.addAttribute("customerSiteStatuss", this.customerSiteStatusService.findAll());
            return "customerSiteStatuss";
        }
        model.addAttribute("customerSiteStatus", customerSiteStatus);
        return "updateCustomerSiteStatus";
    }


}
