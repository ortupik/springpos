package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.RequiredSkill;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.RequiredSkillService;

@Controller
public class RequiredSkillController {

    private RequiredSkillService requiredSkillService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequiredSkillController.class);

    ArrayList<RequiredSkill> requiredSkillList = new ArrayList();

    @Autowired
    public void setRequiredSkillService(RequiredSkillService requiredSkillService) {
        this.requiredSkillService = requiredSkillService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("requiredSkill/new")
    public String requiredSkillPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("requiredSkill", new RequiredSkill());
        setInstitution(model);
        return "requiredSkill";
    }

    @GetMapping("/requiredSkills")
    public ModelAndView showCategories(RequiredSkill requiredSkill) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("requiredSkills");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("requiredSkill", new RequiredSkill());
            setInstitution(mv);
            requiredSkillList.clear();
            requiredSkillList = (ArrayList<RequiredSkill>) this.requiredSkillService.findAll();
            mv.addObject("requiredSkills", this.requiredSkillService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "requiredSkill")
    public String save(@Valid RequiredSkill requiredSkill, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("requiredSkill", new RequiredSkill());
            return "requiredSkill";
        }
        requiredSkillService.save(requiredSkill);
        model.addAttribute("requiredSkill", new RequiredSkill());
        model.addAttribute("addMessage", " RequiredSkill Added Successfull ");
        return "requiredSkill";

    }

    @RequestMapping("/updateRequiredSkill/{id}")
    public String updateRequiredSkill(@PathVariable("id") int id, @Valid RequiredSkill requiredSkill,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("requiredSkill", requiredSkill);
        this.requiredSkillService.update(requiredSkill);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("requiredSkills", this.requiredSkillService.findAll());
        return "requiredSkills";
    }

    @GetMapping("/removeRequiredSkill/{id}")
    public String deleteRequiredSkill(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        RequiredSkill requiredSkill = this.requiredSkillService.find(id);
        if (requiredSkill == null) {
            model.addAttribute("addMessage", "Invalid requiredSkill Id:" + id);
            model.addAttribute("requiredSkills", this.requiredSkillService.findAll());
            return "requiredSkills";
        }
        this.requiredSkillService.delete(requiredSkill);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("requiredSkills", this.requiredSkillService.findAll());
        return "requiredSkills";
    }

    @GetMapping("/editRequiredSkill/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        RequiredSkill requiredSkill = this.requiredSkillService.find(id);
        if (requiredSkill == null) {
            model.addAttribute("addMessage", "Invalid requiredSkill Id:" + id);
            model.addAttribute("requiredSkills", this.requiredSkillService.findAll());
            return "requiredSkills";
        }
        model.addAttribute("requiredSkill", requiredSkill);
        return "updateRequiredSkill";
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
