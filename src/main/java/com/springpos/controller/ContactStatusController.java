package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContactStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContactStatusService;

@Controller
public class ContactStatusController {

    private ContactStatusService contactStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactStatusController.class);

    ArrayList<ContactStatus> contactStatusList = new ArrayList();

    @Autowired
    public void setContactStatusService(ContactStatusService contactStatusService) {
        this.contactStatusService = contactStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("contactStatus/new")
    public String contactStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("contactStatus", new ContactStatus());
        mainService.setInstitution(model);
        return "contactStatus";
    }

    @GetMapping("/contactStatuss")
    public ModelAndView showCategories(ContactStatus contactStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contactStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("contactStatus", new ContactStatus());
            mainService.setInstitution(mv);
            contactStatusList.clear();
            contactStatusList = (ArrayList<ContactStatus>) this.contactStatusService.findAll();
            mv.addObject("contactStatuss", this.contactStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contactStatus")
    public String save(@Valid ContactStatus contactStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            model.addAttribute("contactStatus", new ContactStatus());
            return "contactStatus";
        }
        contactStatusService.save(contactStatus);
        model.addAttribute("contactStatus", new ContactStatus());
        model.addAttribute("addMessage", " Contact Status Added Successfull ");
        return "contactStatus";

    }

    @RequestMapping("/updateContactStatus/{id}")
    public String updateContactStatus(@PathVariable("id") int id, @Valid ContactStatus contactStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("contactStatus", contactStatus);
        this.contactStatusService.update(contactStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contactStatuss", this.contactStatusService.findAll());
        return "contactStatuss";
    }

    @GetMapping("/removeContactStatus/{id}")
    public String deleteContactStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContactStatus contactStatus = this.contactStatusService.find(id);
        if (contactStatus == null) {
            model.addAttribute("addMessage", "Invalid contactStatus Id:" + id);
            model.addAttribute("contactStatuss", this.contactStatusService.findAll());
            return "contactStatuss";
        }
        this.contactStatusService.delete(contactStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contactStatuss", this.contactStatusService.findAll());
        return "contactStatuss";
    }

    @GetMapping("/editContactStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContactStatus contactStatus = this.contactStatusService.find(id);
        if (contactStatus == null) {
            model.addAttribute("addMessage", "Invalid contactStatus Id:" + id);
            model.addAttribute("contactStatuss", this.contactStatusService.findAll());
            return "contactStatuss";
        }
        model.addAttribute("contactStatus", contactStatus);
        return "updateContactStatus";
    }



}
