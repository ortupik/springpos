package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.IncidentType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.IncidentTypeService;

@Controller
public class IncidentTypeController {

    private IncidentTypeService incidentTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentTypeController.class);

    ArrayList<IncidentType> incidentTypeList = new ArrayList();

    @Autowired
    public void setIncidentTypeService(IncidentTypeService incidentTypeService) {
        this.incidentTypeService = incidentTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("incidentType/new")
    public String incidentTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("incidentType", new IncidentType());
        mainService.setInstitution(model);
        return "incidentType";
    }

    @GetMapping("/incidentTypes")
    public ModelAndView showCategories(IncidentType incidentType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("incidentTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("incidentType", new IncidentType());
            mainService.setInstitution(mv);
            incidentTypeList.clear();
            incidentTypeList = (ArrayList<IncidentType>) this.incidentTypeService.findAll();
            mv.addObject("incidentTypes", this.incidentTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "incidentType")
    public String save(@Valid IncidentType incidentType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("incidentType", new IncidentType());
            return "incidentType";
        }
        incidentTypeService.save(incidentType);
        model.addAttribute("incidentType", new IncidentType());
        model.addAttribute("addMessage", " IncidentType Added Successfull ");
        return "incidentType";

    }

    @RequestMapping("/updateIncidentType/{id}")
    public String updateIncidentType(@PathVariable("id") int id, @Valid IncidentType incidentType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("incidentType", incidentType);
        this.incidentTypeService.update(incidentType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("incidentTypes", this.incidentTypeService.findAll());
        return "incidentTypes";
    }

    @GetMapping("/removeIncidentType/{id}")
    public String deleteIncidentType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        IncidentType incidentType = this.incidentTypeService.find(id);
        if (incidentType == null) {
            model.addAttribute("addMessage", "Invalid incidentType Id:" + id);
            model.addAttribute("incidentTypes", this.incidentTypeService.findAll());
            return "incidentTypes";
        }
        this.incidentTypeService.delete(incidentType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("incidentTypes", this.incidentTypeService.findAll());
        return "incidentTypes";
    }

    @GetMapping("/editIncidentType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        IncidentType incidentType = this.incidentTypeService.find(id);
        if (incidentType == null) {
            model.addAttribute("addMessage", "Invalid incidentType Id:" + id);
            model.addAttribute("incidentTypes", this.incidentTypeService.findAll());
            return "incidentTypes";
        }
        model.addAttribute("incidentType", incidentType);
        return "updateIncidentType";
    }

}
