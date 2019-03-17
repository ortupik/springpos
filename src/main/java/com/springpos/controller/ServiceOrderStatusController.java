package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceOrderStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceOrderStatusService;

@Controller
public class ServiceOrderStatusController {

    private ServiceOrderStatusService serviceOrderStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOrderStatusController.class);

    ArrayList<ServiceOrderStatus> serviceOrderStatusList = new ArrayList();

    @Autowired
    public void setServiceOrderStatusService(ServiceOrderStatusService serviceOrderStatusService) {
        this.serviceOrderStatusService = serviceOrderStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceOrderStatus/new")
    public String serviceOrderStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("serviceOrderStatus", new ServiceOrderStatus());
        mainService.setInstitution(model);
        return "serviceOrderStatus";
    }

    @GetMapping("/serviceOrderStatuss")
    public ModelAndView showCategories(ServiceOrderStatus serviceOrderStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceOrderStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("serviceOrderStatus", new ServiceOrderStatus());
            mainService.setInstitution(mv);
            serviceOrderStatusList.clear();
            serviceOrderStatusList = (ArrayList<ServiceOrderStatus>) this.serviceOrderStatusService.findAll();
            mv.addObject("serviceOrderStatuss", this.serviceOrderStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceOrderStatus")
    public String save(@Valid ServiceOrderStatus serviceOrderStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceOrderStatus", new ServiceOrderStatus());
            return "serviceOrderStatus";
        }
        serviceOrderStatusService.save(serviceOrderStatus);
        model.addAttribute("serviceOrderStatus", new ServiceOrderStatus());
        model.addAttribute("addMessage", " ServiceOrderStatus Added Successfull ");
        return "serviceOrderStatus";

    }

    @RequestMapping("/updateServiceOrderStatus/{id}")
    public String updateServiceOrderStatus(@PathVariable("id") int id, @Valid ServiceOrderStatus serviceOrderStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceOrderStatus", serviceOrderStatus);
        this.serviceOrderStatusService.update(serviceOrderStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceOrderStatuss", this.serviceOrderStatusService.findAll());
        return "serviceOrderStatuss";
    }

    @GetMapping("/removeServiceOrderStatus/{id}")
    public String deleteServiceOrderStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderStatus serviceOrderStatus = this.serviceOrderStatusService.find(id);
        if (serviceOrderStatus == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderStatus Id:" + id);
            model.addAttribute("serviceOrderStatuss", this.serviceOrderStatusService.findAll());
            return "serviceOrderStatuss";
        }
        this.serviceOrderStatusService.delete(serviceOrderStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceOrderStatuss", this.serviceOrderStatusService.findAll());
        return "serviceOrderStatuss";
    }

    @GetMapping("/editServiceOrderStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderStatus serviceOrderStatus = this.serviceOrderStatusService.find(id);
        if (serviceOrderStatus == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderStatus Id:" + id);
            model.addAttribute("serviceOrderStatuss", this.serviceOrderStatusService.findAll());
            return "serviceOrderStatuss";
        }
        model.addAttribute("serviceOrderStatus", serviceOrderStatus);
        return "updateServiceOrderStatus";
    }

}
