package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.SocialMediaAccountType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.SocialMediaAccountTypeService;

@Controller
public class SocialMediaAccountTypeController {

    private SocialMediaAccountTypeService socialMediaAccountTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaAccountTypeController.class);

    ArrayList<SocialMediaAccountType> socialMediaAccountTypeList = new ArrayList();

    @Autowired
    public void setSocialMediaAccountTypeService(SocialMediaAccountTypeService socialMediaAccountTypeService) {
        this.socialMediaAccountTypeService = socialMediaAccountTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("socialMediaAccountType/new")
    public String socialMediaAccountTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("socialMediaAccountType", new SocialMediaAccountType());
        mainService.setInstitution(model);
        return "socialMediaAccountType";
    }

    @GetMapping("/socialMediaAccountTypes")
    public ModelAndView showCategories(SocialMediaAccountType socialMediaAccountType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("socialMediaAccountTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("socialMediaAccountType", new SocialMediaAccountType());
            mainService.setInstitution(mv);
            socialMediaAccountTypeList.clear();
            socialMediaAccountTypeList = (ArrayList<SocialMediaAccountType>) this.socialMediaAccountTypeService.findAll();
            mv.addObject("socialMediaAccountTypes", this.socialMediaAccountTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "socialMediaAccountType")
    public String save(@Valid SocialMediaAccountType socialMediaAccountType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("socialMediaAccountType", new SocialMediaAccountType());
            return "socialMediaAccountType";
        }
        socialMediaAccountTypeService.save(socialMediaAccountType);
        model.addAttribute("socialMediaAccountType", new SocialMediaAccountType());
        model.addAttribute("addMessage", " SocialMediaAccountType Added Successfull ");
        return "socialMediaAccountType";

    }

    @RequestMapping("/updateSocialMediaAccountType/{id}")
    public String updateSocialMediaAccountType(@PathVariable("id") int id, @Valid SocialMediaAccountType socialMediaAccountType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("socialMediaAccountType", socialMediaAccountType);
        this.socialMediaAccountTypeService.update(socialMediaAccountType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("socialMediaAccountTypes", this.socialMediaAccountTypeService.findAll());
        return "socialMediaAccountTypes";
    }

    @GetMapping("/removeSocialMediaAccountType/{id}")
    public String deleteSocialMediaAccountType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        SocialMediaAccountType socialMediaAccountType = this.socialMediaAccountTypeService.find(id);
        if (socialMediaAccountType == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccountType Id:" + id);
            model.addAttribute("socialMediaAccountTypes", this.socialMediaAccountTypeService.findAll());
            return "socialMediaAccountTypes";
        }
        this.socialMediaAccountTypeService.delete(socialMediaAccountType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("socialMediaAccountTypes", this.socialMediaAccountTypeService.findAll());
        return "socialMediaAccountTypes";
    }

    @GetMapping("/editSocialMediaAccountType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        SocialMediaAccountType socialMediaAccountType = this.socialMediaAccountTypeService.find(id);
        if (socialMediaAccountType == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccountType Id:" + id);
            model.addAttribute("socialMediaAccountTypes", this.socialMediaAccountTypeService.findAll());
            return "socialMediaAccountTypes";
        }
        model.addAttribute("socialMediaAccountType", socialMediaAccountType);
        return "updateSocialMediaAccountType";
    }


}
