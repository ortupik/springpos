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

@Controller
public class ContractorController {

    private ContractorService contractorService;

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

    @RequestMapping("contractor/new")
    public String contractorPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("contractor", new Contractor());
        setInstitution(model);
        return "contractor";
    }

    @GetMapping("/contractors")
    public ModelAndView showCategories(Contractor contractor) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractors");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("contractor", new Contractor());
            setInstitution(mv);
            contractorList.clear();
            contractorList = (ArrayList<Contractor>) this.contractorService.findAll();
            mv.addObject("contractors", this.contractorService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "contractor")
    public String save(@Valid Contractor contractor, BindingResult result, Model model) {
        setInstitution(model);
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
        setInstitution(model);
        model.addAttribute("contractor", contractor);
        this.contractorService.update(contractor);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractors", this.contractorService.findAll());
        return "contractors";
    }

    @GetMapping("/removeContractor/{id}")
    public String deleteContractor(@PathVariable("id") int id, Model model) {
        setInstitution(model);
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
        setInstitution(model);
        Contractor contractor = this.contractorService.find(id);
        if (contractor == null) {
            model.addAttribute("addMessage", "Invalid contractor Id:" + id);
            model.addAttribute("contractors", this.contractorService.findAll());
            return "contractors";
        }
        model.addAttribute("contractor", contractor);
        return "updateContractor";
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
