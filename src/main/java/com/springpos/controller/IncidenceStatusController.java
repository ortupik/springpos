package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.IncidenceStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.IncidenceStatusService;

@Controller
public class IncidenceStatusController {

    private IncidenceStatusService incidentStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidenceStatusController.class);

    ArrayList<IncidenceStatus> incidentStatusList = new ArrayList();

    @Autowired
    public void setIncidenceStatusService(IncidenceStatusService incidentStatusService) {
        this.incidentStatusService = incidentStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("incidentStatus/new")
    public String incidentStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("incidentStatus", new IncidenceStatus());
        setInstitution(model);
        return "incidentStatus";
    }

    @GetMapping("/incidentStatuss")
    public ModelAndView showCategories(IncidenceStatus incidentStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("incidentStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("incidentStatus", new IncidenceStatus());
            setInstitution(mv);
            incidentStatusList.clear();
            incidentStatusList = (ArrayList<IncidenceStatus>) this.incidentStatusService.findAll();
            mv.addObject("incidentStatuss", this.incidentStatusService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "incidentStatus")
    public String save(@Valid IncidenceStatus incidentStatus, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("incidentStatus", new IncidenceStatus());
            return "incidentStatus";
        }
        incidentStatusService.save(incidentStatus);
        model.addAttribute("incidentStatus", new IncidenceStatus());
        model.addAttribute("addMessage", " IncidenceStatus Added Successfull ");
        return "incidentStatus";

    }

    @RequestMapping("/updateIncidenceStatus/{id}")
    public String updateIncidenceStatus(@PathVariable("id") int id, @Valid IncidenceStatus incidentStatus,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("incidentStatus", incidentStatus);
        this.incidentStatusService.update(incidentStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("incidentStatuss", this.incidentStatusService.findAll());
        return "incidentStatuss";
    }

    @GetMapping("/removeIncidenceStatus/{id}")
    public String deleteIncidenceStatus(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        IncidenceStatus incidentStatus = this.incidentStatusService.find(id);
        if (incidentStatus == null) {
            model.addAttribute("addMessage", "Invalid incidentStatus Id:" + id);
            model.addAttribute("incidentStatuss", this.incidentStatusService.findAll());
            return "incidentStatuss";
        }
        this.incidentStatusService.delete(incidentStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("incidentStatuss", this.incidentStatusService.findAll());
        return "incidentStatuss";
    }

    @GetMapping("/editIncidenceStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        IncidenceStatus incidentStatus = this.incidentStatusService.find(id);
        if (incidentStatus == null) {
            model.addAttribute("addMessage", "Invalid incidentStatus Id:" + id);
            model.addAttribute("incidentStatuss", this.incidentStatusService.findAll());
            return "incidentStatuss";
        }
        model.addAttribute("incidentStatus", incidentStatus);
        return "updateIncidenceStatus";
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
