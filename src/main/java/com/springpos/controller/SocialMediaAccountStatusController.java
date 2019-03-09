package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.SocialMediaAccountStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.SocialMediaAccountStatusService;

@Controller
public class SocialMediaAccountStatusController {

    private SocialMediaAccountStatusService socialMediaAccountStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialMediaAccountStatusController.class);

    ArrayList<SocialMediaAccountStatus> socialMediaAccountStatusList = new ArrayList();

    @Autowired
    public void setSocialMediaAccountStatusService(SocialMediaAccountStatusService socialMediaAccountStatusService) {
        this.socialMediaAccountStatusService = socialMediaAccountStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("socialMediaAccountStatus/new")
    public String socialMediaAccountStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("socialMediaAccountStatus", new SocialMediaAccountStatus());
        setInstitution(model);
        return "socialMediaAccountStatus";
    }

    @GetMapping("/socialMediaAccountStatuss")
    public ModelAndView showCategories(SocialMediaAccountStatus socialMediaAccountStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("socialMediaAccountStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("socialMediaAccountStatus", new SocialMediaAccountStatus());
            setInstitution(mv);
            socialMediaAccountStatusList.clear();
            socialMediaAccountStatusList = (ArrayList<SocialMediaAccountStatus>) this.socialMediaAccountStatusService.findAll();
            mv.addObject("socialMediaAccountStatuss", this.socialMediaAccountStatusService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "socialMediaAccountStatus")
    public String save(@Valid SocialMediaAccountStatus socialMediaAccountStatus, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("socialMediaAccountStatus", new SocialMediaAccountStatus());
            return "socialMediaAccountStatus";
        }
        socialMediaAccountStatusService.save(socialMediaAccountStatus);
        model.addAttribute("socialMediaAccountStatus", new SocialMediaAccountStatus());
        model.addAttribute("addMessage", " SocialMediaAccountStatus Added Successfull ");
        return "socialMediaAccountStatus";

    }

    @RequestMapping("/updateSocialMediaAccountStatus/{id}")
    public String updateSocialMediaAccountStatus(@PathVariable("id") int id, @Valid SocialMediaAccountStatus socialMediaAccountStatus,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("socialMediaAccountStatus", socialMediaAccountStatus);
        this.socialMediaAccountStatusService.update(socialMediaAccountStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("socialMediaAccountStatuss", this.socialMediaAccountStatusService.findAll());
        return "socialMediaAccountStatuss";
    }

    @GetMapping("/removeSocialMediaAccountStatus/{id}")
    public String deleteSocialMediaAccountStatus(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        SocialMediaAccountStatus socialMediaAccountStatus = this.socialMediaAccountStatusService.find(id);
        if (socialMediaAccountStatus == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccountStatus Id:" + id);
            model.addAttribute("socialMediaAccountStatuss", this.socialMediaAccountStatusService.findAll());
            return "socialMediaAccountStatuss";
        }
        this.socialMediaAccountStatusService.delete(socialMediaAccountStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("socialMediaAccountStatuss", this.socialMediaAccountStatusService.findAll());
        return "socialMediaAccountStatuss";
    }

    @GetMapping("/editSocialMediaAccountStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        SocialMediaAccountStatus socialMediaAccountStatus = this.socialMediaAccountStatusService.find(id);
        if (socialMediaAccountStatus == null) {
            model.addAttribute("addMessage", "Invalid socialMediaAccountStatus Id:" + id);
            model.addAttribute("socialMediaAccountStatuss", this.socialMediaAccountStatusService.findAll());
            return "socialMediaAccountStatuss";
        }
        model.addAttribute("socialMediaAccountStatus", socialMediaAccountStatus);
        return "updateSocialMediaAccountStatus";
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
