package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Incident;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.IncidentService;

@Controller
public class IncidentController {

    private IncidentService incidentService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentController.class);

    ArrayList<Incident> incidentList = new ArrayList();

    @Autowired
    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("incident/new")
    public String incidentPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("incident", new Incident());
        mainService.setInstitution(model);
        return "incident";
    }

    @GetMapping("/incidents")
    public ModelAndView showCategories(Incident incident) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("incidents");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("incident", new Incident());
            mainService.setInstitution(mv);
            incidentList.clear();
            incidentList = (ArrayList<Incident>) this.incidentService.findAll();
            mv.addObject("incidents", this.incidentService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "incident")
    public String save(@Valid Incident incident, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("incident", new Incident());
            return "incident";
        }
        incidentService.save(incident);
        model.addAttribute("incident", new Incident());
        model.addAttribute("addMessage", " Incident Added Successfull ");
        return "incident";

    }

    @RequestMapping("/updateIncident/{id}")
    public String updateIncident(@PathVariable("id") int id, @Valid Incident incident,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("incident", incident);
        this.incidentService.update(incident);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("incidents", this.incidentService.findAll());
        return "incidents";
    }

    @GetMapping("/removeIncident/{id}")
    public String deleteIncident(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Incident incident = this.incidentService.find(id);
        if (incident == null) {
            model.addAttribute("addMessage", "Invalid incident Id:" + id);
            model.addAttribute("incidents", this.incidentService.findAll());
            return "incidents";
        }
        this.incidentService.delete(incident);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("incidents", this.incidentService.findAll());
        return "incidents";
    }

    @GetMapping("/editIncident/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Incident incident = this.incidentService.find(id);
        if (incident == null) {
            model.addAttribute("addMessage", "Invalid incident Id:" + id);
            model.addAttribute("incidents", this.incidentService.findAll());
            return "incidents";
        }
        model.addAttribute("incident", incident);
        return "updateIncident";
    }

 
}
