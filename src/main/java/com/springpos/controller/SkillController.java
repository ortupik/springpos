package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Skill;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.SkillService;

@Controller
public class SkillController {

    private SkillService skillService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    ArrayList<Skill> skillList = new ArrayList();

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("skill/new")
    public String skillPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("skill", new Skill());
        mainService.setInstitution(model);
        return "skill";
    }

    @GetMapping("/skills")
    public ModelAndView showCategories(Skill skill) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("skills");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("skill", new Skill());
            mainService.setInstitution(mv);
            skillList.clear();
            skillList = (ArrayList<Skill>) this.skillService.findAll();
            mv.addObject("skills", this.skillService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "skill")
    public String save(@Valid Skill skill, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("skill", new Skill());
            return "skill";
        }
        skillService.save(skill);
        model.addAttribute("skill", new Skill());
        model.addAttribute("addMessage", " Skill Added Successfull ");
        return "skill";

    }

    @RequestMapping("/updateSkill/{id}")
    public String updateSkill(@PathVariable("id") int id, @Valid Skill skill,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("skill", skill);
        this.skillService.update(skill);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("skills", this.skillService.findAll());
        return "skills";
    }

    @GetMapping("/removeSkill/{id}")
    public String deleteSkill(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Skill skill = this.skillService.find(id);
        if (skill == null) {
            model.addAttribute("addMessage", "Invalid skill Id:" + id);
            model.addAttribute("skills", this.skillService.findAll());
            return "skills";
        }
        this.skillService.delete(skill);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("skills", this.skillService.findAll());
        return "skills";
    }

    @GetMapping("/editSkill/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Skill skill = this.skillService.find(id);
        if (skill == null) {
            model.addAttribute("addMessage", "Invalid skill Id:" + id);
            model.addAttribute("skills", this.skillService.findAll());
            return "skills";
        }
        model.addAttribute("skill", skill);
        return "updateSkill";
    }


}
