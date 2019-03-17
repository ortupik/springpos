package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Contractor;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContractorService;
import com.springpos.service.ContractorStatusService;
import com.springpos.service.ContractorTypeService;
import com.springpos.service.CountryService;
import com.springpos.service.StateService;

@Controller
public class ContractorController {

    private ContractorService contractorService;
    private ContractorTypeService typeService;
    private ContractorStatusService statusService;
    private CountryService countryService;
    private StateService stateService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorController.class);

    ArrayList<Contractor> contractorList = new ArrayList();

    @Autowired
    public void setContractorService(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setTypeService(ContractorTypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setStatusService(ContractorStatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping("contractor/new")
    public String contractorPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("contractorTypes", this.typeService.findAll());
        model.addAttribute("contractorStatuss", this.statusService.findAll());
        model.addAttribute("countries", this.countryService.findAll());
        model.addAttribute("states", this.stateService.findAll());
        model.addAttribute("contractor", new Contractor());
        mainService.setInstitution(model);
        return "contractor";
    }

    @GetMapping("/contractors")
    public ModelAndView showCategories(Contractor contractor) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractors");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("contractor", new Contractor());
            mainService.setInstitution(mv);
            contractorList.clear();
            contractorList = (ArrayList<Contractor>) this.contractorService.findAll();
            mv.addObject("contractors", this.contractorService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contractor")
    public String save(@Valid Contractor contractor, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("contractor", new Contractor());
            return "contractor";
        }
        contractorService.save(contractor);
        model.addAttribute("contractor", new Contractor());
        model.addAttribute("addMessage", " Contractor Added Successfull ");
        return "contractor";

    }

    @RequestMapping("/updateContractor/{id}")
    public String updateContractor(@PathVariable("id") int id, @Valid Contractor contractor,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("contractor", contractor);
        this.contractorService.update(contractor);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractors", this.contractorService.findAll());
        return "contractors";
    }

    @GetMapping("/removeContractor/{id}")
    public String deleteContractor(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Contractor contractor = this.contractorService.find(id);
        if (contractor == null) {
            model.addAttribute("addMessage", "Invalid contractor Id:" + id);
            model.addAttribute("contractors", this.contractorService.findAll());
            return "contractors";
        }
        this.contractorService.delete(contractor);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contractors", this.contractorService.findAll());
        return "contractors";
    }

    @GetMapping("/editContractor/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Contractor contractor = this.contractorService.find(id);
        if (contractor == null) {
            model.addAttribute("addMessage", "Invalid contractor Id:" + id);
            model.addAttribute("contractors", this.contractorService.findAll());
            return "contractors";
        }
        model.addAttribute("contractor", contractor);
        return "updateContractor";
    }



}
