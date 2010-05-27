package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceTypeService;

@Controller
public class ServiceTypeController {

    private ServiceTypeService serviceTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTypeController.class);

    ArrayList<ServiceType> serviceTypeList = new ArrayList();

    @Autowired
    public void setServiceTypeService(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceType/new")
    public String serviceTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("serviceType", new ServiceType());
        mainService.setInstitution(model);
        return "serviceType";
    }

    @GetMapping("/serviceTypes")
    public ModelAndView showCategories(ServiceType serviceType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("serviceType", new ServiceType());
            mainService.setInstitution(mv);
            serviceTypeList.clear();
            serviceTypeList = (ArrayList<ServiceType>) this.serviceTypeService.findAll();
            mv.addObject("serviceTypes", this.serviceTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceType")
    public String save(@Valid ServiceType serviceType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceType", new ServiceType());
            return "serviceType";
        }
        serviceTypeService.save(serviceType);
        model.addAttribute("serviceType", new ServiceType());
        model.addAttribute("addMessage", " ServiceType Added Successfull ");
        return "serviceType";

    }

    @RequestMapping("/updateServiceType/{id}")
    public String updateServiceType(@PathVariable("id") int id, @Valid ServiceType serviceType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceType", serviceType);
        this.serviceTypeService.update(serviceType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
        return "serviceTypes";
    }

    @GetMapping("/removeServiceType/{id}")
    public String deleteServiceType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceType serviceType = this.serviceTypeService.find(id);
        if (serviceType == null) {
            model.addAttribute("addMessage", "Invalid serviceType Id:" + id);
            model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
            return "serviceTypes";
        }
        this.serviceTypeService.delete(serviceType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
        return "serviceTypes";
    }

    @GetMapping("/editServiceType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceType serviceType = this.serviceTypeService.find(id);
        if (serviceType == null) {
            model.addAttribute("addMessage", "Invalid serviceType Id:" + id);
            model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
            return "serviceTypes";
        }
        model.addAttribute("serviceType", serviceType);
        return "updateServiceType";
    }


}
