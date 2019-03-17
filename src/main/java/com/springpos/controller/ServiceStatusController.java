package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceStatusService;

@Controller
public class ServiceStatusController {

    private ServiceStatusService serviceStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceStatusController.class);

    ArrayList<ServiceStatus> serviceStatusList = new ArrayList();

    @Autowired
    public void setServiceStatusService(ServiceStatusService serviceStatusService) {
        this.serviceStatusService = serviceStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceStatus/new")
    public String serviceStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("serviceStatus", new ServiceStatus());
        mainService.setInstitution(model);
        return "serviceStatus";
    }

    @GetMapping("/serviceStatuss")
    public ModelAndView showCategories(ServiceStatus serviceStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("serviceStatus", new ServiceStatus());
            mainService.setInstitution(mv);
            serviceStatusList.clear();
            serviceStatusList = (ArrayList<ServiceStatus>) this.serviceStatusService.findAll();
            mv.addObject("serviceStatuss", this.serviceStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceStatus")
    public String save(@Valid ServiceStatus serviceStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceStatus", new ServiceStatus());
            return "serviceStatus";
        }
        serviceStatusService.save(serviceStatus);
        model.addAttribute("serviceStatus", new ServiceStatus());
        model.addAttribute("addMessage", " ServiceStatus Added Successfull ");
        return "serviceStatus";

    }

    @RequestMapping("/updateServiceStatus/{id}")
    public String updateServiceStatus(@PathVariable("id") int id, @Valid ServiceStatus serviceStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceStatus", serviceStatus);
        this.serviceStatusService.update(serviceStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceStatuss", this.serviceStatusService.findAll());
        return "serviceStatuss";
    }

    @GetMapping("/removeServiceStatus/{id}")
    public String deleteServiceStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceStatus serviceStatus = this.serviceStatusService.find(id);
        if (serviceStatus == null) {
            model.addAttribute("addMessage", "Invalid serviceStatus Id:" + id);
            model.addAttribute("serviceStatuss", this.serviceStatusService.findAll());
            return "serviceStatuss";
        }
        this.serviceStatusService.delete(serviceStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceStatuss", this.serviceStatusService.findAll());
        return "serviceStatuss";
    }

    @GetMapping("/editServiceStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceStatus serviceStatus = this.serviceStatusService.find(id);
        if (serviceStatus == null) {
            model.addAttribute("addMessage", "Invalid serviceStatus Id:" + id);
            model.addAttribute("serviceStatuss", this.serviceStatusService.findAll());
            return "serviceStatuss";
        }
        model.addAttribute("serviceStatus", serviceStatus);
        return "updateServiceStatus";
    }

}
