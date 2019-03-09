package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.IncidentContractor;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.IncidentContractorService;

@Controller
public class IncidentContractorController {

    private IncidentContractorService incidentContractorService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentContractorController.class);

    ArrayList<IncidentContractor> incidentContractorList = new ArrayList();

    @Autowired
    public void setIncidentContractorService(IncidentContractorService incidentContractorService) {
        this.incidentContractorService = incidentContractorService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("incidentContractor/new")
    public String incidentContractorPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("incidentContractor", new IncidentContractor());
        setInstitution(model);
        return "incidentContractor";
    }

    @GetMapping("/incidentContractors")
    public ModelAndView showCategories(IncidentContractor incidentContractor) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("incidentContractors");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("incidentContractor", new IncidentContractor());
            setInstitution(mv);
            incidentContractorList.clear();
            incidentContractorList = (ArrayList<IncidentContractor>) this.incidentContractorService.findAll();
            mv.addObject("incidentContractors", this.incidentContractorService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "incidentContractor")
    public String save(@Valid IncidentContractor incidentContractor, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("incidentContractor", new IncidentContractor());
            return "incidentContractor";
        }
        incidentContractorService.save(incidentContractor);
        model.addAttribute("incidentContractor", new IncidentContractor());
        model.addAttribute("addMessage", " IncidentContractor Added Successfull ");
        return "incidentContractor";

    }

    @RequestMapping("/updateIncidentContractor/{id}")
    public String updateIncidentContractor(@PathVariable("id") int id, @Valid IncidentContractor incidentContractor,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("incidentContractor", incidentContractor);
        this.incidentContractorService.update(incidentContractor);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("incidentContractors", this.incidentContractorService.findAll());
        return "incidentContractors";
    }

    @GetMapping("/removeIncidentContractor/{id}")
    public String deleteIncidentContractor(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        IncidentContractor incidentContractor = this.incidentContractorService.find(id);
        if (incidentContractor == null) {
            model.addAttribute("addMessage", "Invalid incidentContractor Id:" + id);
            model.addAttribute("incidentContractors", this.incidentContractorService.findAll());
            return "incidentContractors";
        }
        this.incidentContractorService.delete(incidentContractor);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("incidentContractors", this.incidentContractorService.findAll());
        return "incidentContractors";
    }

    @GetMapping("/editIncidentContractor/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        IncidentContractor incidentContractor = this.incidentContractorService.find(id);
        if (incidentContractor == null) {
            model.addAttribute("addMessage", "Invalid incidentContractor Id:" + id);
            model.addAttribute("incidentContractors", this.incidentContractorService.findAll());
            return "incidentContractors";
        }
        model.addAttribute("incidentContractor", incidentContractor);
        return "updateIncidentContractor";
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
