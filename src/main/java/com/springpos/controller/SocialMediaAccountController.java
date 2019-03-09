package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.SocialMediaAccount;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.SocialMediaAccountService;

@Controller
public class SocialMediaAccountController {

    private SocialMediaAccountService socialMediaAccountService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaAccountController.class);

    ArrayList<SocialMediaAccount> socialMediaAccountList = new ArrayList();

    @Autowired
    public void setSocialMediaAccountService(SocialMediaAccountService socialMediaAccountService) {
        this.socialMediaAccountService = socialMediaAccountService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("socialMediaAccount/new")
    public String socialMediaAccountPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("socialMediaAccount", new SocialMediaAccount());
        setInstitution(model);
        return "socialMediaAccount";
    }

    @GetMapping("/socialMediaAccounts")
    public ModelAndView showCategories(SocialMediaAccount socialMediaAccount) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("socialMediaAccounts");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("socialMediaAccount", new SocialMediaAccount());
            setInstitution(mv);
            socialMediaAccountList.clear();
            socialMediaAccountList = (ArrayList<SocialMediaAccount>) this.socialMediaAccountService.findAll();
            mv.addObject("socialMediaAccounts", this.socialMediaAccountService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "socialMediaAccount")
    public String save(@Valid SocialMediaAccount socialMediaAccount, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("socialMediaAccount", new SocialMediaAccount());
            return "socialMediaAccount";
        }
        socialMediaAccountService.save(socialMediaAccount);
        model.addAttribute("socialMediaAccount", new SocialMediaAccount());
        model.addAttribute("addMessage", " SocialMediaAccount Added Successfull ");
        return "socialMediaAccount";

    }

    @RequestMapping("/updateSocialMediaAccount/{id}")
    public String updateSocialMediaAccount(@PathVariable("id") int id, @Valid SocialMediaAccount socialMediaAccount,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("socialMediaAccount", socialMediaAccount);
        this.socialMediaAccountService.update(socialMediaAccount);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("socialMediaAccounts", this.socialMediaAccountService.findAll());
        return "socialMediaAccounts";
    }

    @GetMapping("/removeSocialMediaAccount/{id}")
    public String deleteSocialMediaAccount(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        SocialMediaAccount socialMediaAccount = this.socialMediaAccountService.find(id);
        if (socialMediaAccount == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccount Id:" + id);
            model.addAttribute("socialMediaAccounts", this.socialMediaAccountService.findAll());
            return "socialMediaAccounts";
        }
        this.socialMediaAccountService.delete(socialMediaAccount);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("socialMediaAccounts", this.socialMediaAccountService.findAll());
        return "socialMediaAccounts";
    }

    @GetMapping("/editSocialMediaAccount/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        SocialMediaAccount socialMediaAccount = this.socialMediaAccountService.find(id);
        if (socialMediaAccount == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccount Id:" + id);
            model.addAttribute("socialMediaAccounts", this.socialMediaAccountService.findAll());
            return "socialMediaAccounts";
        }
        model.addAttribute("socialMediaAccount", socialMediaAccount);
        return "updateSocialMediaAccount";
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