package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContractorSkill;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContractorSkillService;

@Controller
public class ContractorSkillController {

    private ContractorSkillService contractorSkillService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorSkillController.class);

    ArrayList<ContractorSkill> contractorSkillList = new ArrayList();

    @Autowired
    public void setContractorSkillService(ContractorSkillService contractorSkillService) {
        this.contractorSkillService = contractorSkillService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("contractorSkill/new")
    public String contractorSkillPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("contractorSkill", new ContractorSkill());
        setInstitution(model);
        return "contractorSkill";
    }

    @GetMapping("/contractorSkills")
    public ModelAndView showCategories(ContractorSkill contractorSkill) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractorSkills");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("contractorSkill", new ContractorSkill());
            setInstitution(mv);
            contractorSkillList.clear();
            contractorSkillList = (ArrayList<ContractorSkill>) this.contractorSkillService.findAll();
            mv.addObject("contractorSkills", this.contractorSkillService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "contractorSkill")
    public String save(@Valid ContractorSkill contractorSkill, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("contractorSkill", new ContractorSkill());
            return "contractorSkill";
        }
        contractorSkillService.save(contractorSkill);
        model.addAttribute("contractorSkill", new ContractorSkill());
        model.addAttribute("addMessage", " ContractorSkill Added Successfull ");
        return "contractorSkill";

    }

    @RequestMapping("/updateContractorSkill/{id}")
    public String updateContractorSkill(@PathVariable("id") int id, @Valid ContractorSkill contractorSkill,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("contractorSkill", contractorSkill);
        this.contractorSkillService.update(contractorSkill);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
        return "contractorSkills";
    }

    @GetMapping("/removeContractorSkill/{id}")
    public String deleteContractorSkill(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        ContractorSkill contractorSkill = this.contractorSkillService.find(id);
        if (contractorSkill == null) {
            model.addAttribute("addMessage", "Invalid contractorSkill Id:" + id);
            model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
            return "contractorSkills";
        }
        this.contractorSkillService.delete(contractorSkill);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
        return "contractorSkills";
    }

    @GetMapping("/editContractorSkill/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        ContractorSkill contractorSkill = this.contractorSkillService.find(id);
        if (contractorSkill == null) {
            model.addAttribute("addMessage", "Invalid contractorSkill Id:" + id);
            model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
            return "contractorSkills";
        }
        model.addAttribute("contractorSkill", contractorSkill);
        return "updateContractorSkill";
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
