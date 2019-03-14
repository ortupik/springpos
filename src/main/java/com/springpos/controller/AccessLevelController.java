package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.AccessLevel;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.AccessLevelService;

@Controller
public class AccessLevelController {

    private AccessLevelService accessLevelService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLevelController.class);

    ArrayList<AccessLevel> accessLevelList = new ArrayList();

    @Autowired
    public void setAccessLevelService(AccessLevelService accessLevelService) {
        this.accessLevelService = accessLevelService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("accessLevel/new")
    public String accessLevelPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("accessLevel", new AccessLevel());
        setInstitution(model);
        return "accessLevel";
    }

    @GetMapping("/accessLevels")
    public ModelAndView showCategories(AccessLevel accessLevel) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("accessLevels");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("accessLevel", new AccessLevel());
            setInstitution(mv);
            accessLevelList.clear();
            accessLevelList = (ArrayList<AccessLevel>) this.accessLevelService.findAll();
            mv.addObject("accessLevels", this.accessLevelService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "accessLevel")
    public String save(@Valid AccessLevel accessLevel, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("accessLevel", new AccessLevel());
            return "accessLevel";
        }
        accessLevelService.save(accessLevel);
        model.addAttribute("accessLevel", new AccessLevel());
        model.addAttribute("addMessage", " AccessLevel Added Successfull ");
        return "redirect:/accessLevel";

    }

    @RequestMapping("/updateAccessLevel/{id}")
    public String updateAccessLevel(@PathVariable("id") int id, @Valid AccessLevel accessLevel,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("accessLevel", accessLevel);
        this.accessLevelService.update(accessLevel);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("accessLevels", this.accessLevelService.findAll());
        return "accessLevels";
    }

    @GetMapping("/removeAccessLevel/{id}")
    public String deleteAccessLevel(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        AccessLevel accessLevel = this.accessLevelService.find(id);
        if (accessLevel == null) {
            model.addAttribute("addMessage", "Invalid accessLevel Id:" + id);
            model.addAttribute("accessLevels", this.accessLevelService.findAll());
            return "accessLevels";
        }
        this.accessLevelService.delete(accessLevel);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("accessLevels", this.accessLevelService.findAll());
        return "accessLevels";
    }

    @GetMapping("/editAccessLevel/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        AccessLevel accessLevel = this.accessLevelService.find(id);
        if (accessLevel == null) {
            model.addAttribute("addMessage", "Invalid accessLevel Id:" + id);
            model.addAttribute("accessLevels", this.accessLevelService.findAll());
            return "accessLevels";
        }
        model.addAttribute("accessLevel", accessLevel);
        return "updateAccessLevel";
    }

    void setInstitution(Model institution) {
        institution.addAttribute("institution", this.mainService.institutionName());
        institution.addAttribute("title", this.mainService.institutionName());
        institution.addAttribute("motto", this.mainService.institutionMotto());
    }

    void setInstitution(ModelAndView institution) {
        institution.addObject("institution", this.mainService.institutionName());
        institution.addObject("motto", this.mainService.institutionMotto());
    }

}
