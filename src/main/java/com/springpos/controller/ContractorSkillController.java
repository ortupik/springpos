package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContractorSkill;
import com.springpos.service.ContractorService;
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
import com.springpos.service.SkillService;

@Controller
public class ContractorSkillController {

    private ContractorSkillService contractorSkillService;
    private SkillService skillService;
    private ContractorService contractorService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorSkillController.class);

    ArrayList<ContractorSkill> contractorSkillList = new ArrayList();

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setContractorService(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

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
            return "redirect:/";
        }
        model.addAttribute("contractors", contractorService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("contractorSkill", new ContractorSkill());
        mainService.setInstitution(model);
        return "contractorSkill";
    }

    @GetMapping("/contractorSkills")
    public ModelAndView showCategories(ContractorSkill contractorSkill) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractorSkills");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("contractorSkill", new ContractorSkill());
            mainService.setInstitution(mv);
            contractorSkillList.clear();
            contractorSkillList = (ArrayList<ContractorSkill>) this.contractorSkillService.findAll();
            mv.addObject("contractorSkills", this.contractorSkillService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contractorSkill")
    public String save(@Valid ContractorSkill contractorSkill, BindingResult result, Model model) {
        mainService.setInstitution(model);
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
        mainService.setInstitution(model);
        model.addAttribute("contractorSkill", contractorSkill);
        this.contractorSkillService.update(contractorSkill);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
        return "contractorSkills";
    }

    @GetMapping("/removeContractorSkill/{id}")
    public String deleteContractorSkill(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
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
        mainService.setInstitution(model);
        ContractorSkill contractorSkill = this.contractorSkillService.find(id);
        if (contractorSkill == null) {
            model.addAttribute("addMessage", "Invalid contractorSkill Id:" + id);
            model.addAttribute("contractorSkills", this.contractorSkillService.findAll());
            return "contractorSkills";
        }
        model.addAttribute("contractorSkill", contractorSkill);
        return "updateContractorSkill";
    }

 

}
