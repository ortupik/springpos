package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceOrderLine;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceOrderLineService;

@Controller
public class ServiceOrderLineController {

    private ServiceOrderLineService serviceOrderLineService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOrderLineController.class);

    ArrayList<ServiceOrderLine> serviceOrderLineList = new ArrayList();

    @Autowired
    public void setServiceOrderLineService(ServiceOrderLineService serviceOrderLineService) {
        this.serviceOrderLineService = serviceOrderLineService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceOrderLine/new")
    public String serviceOrderLinePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("serviceOrderLine", new ServiceOrderLine());
        mainService.setInstitution(model);
        return "serviceOrderLine";
    }

    @GetMapping("/serviceOrderLines")
    public ModelAndView showCategories(ServiceOrderLine serviceOrderLine) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceOrderLines");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("serviceOrderLine", new ServiceOrderLine());
            mainService.setInstitution(mv);
            serviceOrderLineList.clear();
            serviceOrderLineList = (ArrayList<ServiceOrderLine>) this.serviceOrderLineService.findAll();
            mv.addObject("serviceOrderLines", this.serviceOrderLineService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceOrderLine")
    public String save(@Valid ServiceOrderLine serviceOrderLine, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceOrderLine", new ServiceOrderLine());
            return "serviceOrderLine";
        }
        serviceOrderLineService.save(serviceOrderLine);
        model.addAttribute("serviceOrderLine", new ServiceOrderLine());
        model.addAttribute("addMessage", " ServiceOrderLine Added Successfull ");
        return "serviceOrderLine";

    }

    @RequestMapping("/updateServiceOrderLine/{id}")
    public String updateServiceOrderLine(@PathVariable("id") int id, @Valid ServiceOrderLine serviceOrderLine,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceOrderLine", serviceOrderLine);
        this.serviceOrderLineService.update(serviceOrderLine);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceOrderLines", this.serviceOrderLineService.findAll());
        return "serviceOrderLines";
    }

    @GetMapping("/removeServiceOrderLine/{id}")
    public String deleteServiceOrderLine(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderLine serviceOrderLine = this.serviceOrderLineService.find(id);
        if (serviceOrderLine == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderLine Id:" + id);
            model.addAttribute("serviceOrderLines", this.serviceOrderLineService.findAll());
            return "serviceOrderLines";
        }
        this.serviceOrderLineService.delete(serviceOrderLine);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceOrderLines", this.serviceOrderLineService.findAll());
        return "serviceOrderLines";
    }

    @GetMapping("/editServiceOrderLine/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderLine serviceOrderLine = this.serviceOrderLineService.find(id);
        if (serviceOrderLine == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderLine Id:" + id);
            model.addAttribute("serviceOrderLines", this.serviceOrderLineService.findAll());
            return "serviceOrderLines";
        }
        model.addAttribute("serviceOrderLine", serviceOrderLine);
        return "updateServiceOrderLine";
    }



}
