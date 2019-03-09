package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.CustomerSiteType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CustomerSiteTypeService;

@Controller
public class CustomerSiteTypeController {

    private CustomerSiteTypeService customerSiteTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSiteTypeController.class);

    ArrayList<CustomerSiteType> customerSiteTypeList = new ArrayList();

    @Autowired
    public void setCustomerSiteTypeService(CustomerSiteTypeService customerSiteTypeService) {
        this.customerSiteTypeService = customerSiteTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("customerSiteType/new")
    public String customerSiteTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("customerSiteType", new CustomerSiteType());
        setInstitution(model);
        return "customerSiteType";
    }

    @GetMapping("/customerSiteTypes")
    public ModelAndView showCategories(CustomerSiteType customerSiteType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customerSiteTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("customerSiteType", new CustomerSiteType());
            setInstitution(mv);
            customerSiteTypeList.clear();
            customerSiteTypeList = (ArrayList<CustomerSiteType>) this.customerSiteTypeService.findAll();
            mv.addObject("customerSiteTypes", this.customerSiteTypeService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "customerSiteType")
    public String save(@Valid CustomerSiteType customerSiteType, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("customerSiteType", new CustomerSiteType());
            return "customerSiteType";
        }
        customerSiteTypeService.save(customerSiteType);
        model.addAttribute("customerSiteType", new CustomerSiteType());
        model.addAttribute("addMessage", " CustomerSiteType Added Successfull ");
        return "customerSiteType";

    }

    @RequestMapping("/updateCustomerSiteType/{id}")
    public String updateCustomerSiteType(@PathVariable("id") int id, @Valid CustomerSiteType customerSiteType,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("customerSiteType", customerSiteType);
        this.customerSiteTypeService.update(customerSiteType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("customerSiteTypes", this.customerSiteTypeService.findAll());
        return "customerSiteTypes";
    }

    @GetMapping("/removeCustomerSiteType/{id}")
    public String deleteCustomerSiteType(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        CustomerSiteType customerSiteType = this.customerSiteTypeService.find(id);
        if (customerSiteType == null) {
            model.addAttribute("addMessage", "Invalid customerSiteType Id:" + id);
            model.addAttribute("customerSiteTypes", this.customerSiteTypeService.findAll());
            return "customerSiteTypes";
        }
        this.customerSiteTypeService.delete(customerSiteType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("customerSiteTypes", this.customerSiteTypeService.findAll());
        return "customerSiteTypes";
    }

    @GetMapping("/editCustomerSiteType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        CustomerSiteType customerSiteType = this.customerSiteTypeService.find(id);
        if (customerSiteType == null) {
            model.addAttribute("addMessage", "Invalid customerSiteType Id:" + id);
            model.addAttribute("customerSiteTypes", this.customerSiteTypeService.findAll());
            return "customerSiteTypes";
        }
        model.addAttribute("customerSiteType", customerSiteType);
        return "updateCustomerSiteType";
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
