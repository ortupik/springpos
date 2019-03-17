package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContactType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContactTypeService;

@Controller
public class ContactTypeController {

    private ContactTypeService contactTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactTypeController.class);

    ArrayList<ContactType> contactTypeList = new ArrayList();

    @Autowired
    public void setContactTypeService(ContactTypeService contactTypeService) {
        this.contactTypeService = contactTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("contactType/new")
    public String contactTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("contactType", new ContactType());
        mainService.setInstitution(model);
        return "contactType";
    }

    @GetMapping("/contactTypes")
    public ModelAndView showCategories(ContactType contactType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contactTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("contactType", new ContactType());
            mainService.setInstitution(mv);
            contactTypeList.clear();
            contactTypeList = (ArrayList<ContactType>) this.contactTypeService.findAll();
            mv.addObject("contactTypes", this.contactTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contactType")
    public String save(@Valid ContactType contactType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("contactType", new ContactType());
            return "contactType";
        }
        contactTypeService.save(contactType);
        model.addAttribute("contactType", new ContactType());
        model.addAttribute("addMessage", " ContactType Added Successfull ");
        return "contactType";

    }

    @RequestMapping("/updateContactType/{id}")
    public String updateContactType(@PathVariable("id") int id, @Valid ContactType contactType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("contactType", contactType);
        this.contactTypeService.update(contactType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contactTypes", this.contactTypeService.findAll());
        return "contactTypes";
    }

    @GetMapping("/removeContactType/{id}")
    public String deleteContactType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContactType contactType = this.contactTypeService.find(id);
        if (contactType == null) {
            model.addAttribute("addMessage", "Invalid contactType Id:" + id);
            model.addAttribute("contactTypes", this.contactTypeService.findAll());
            return "contactTypes";
        }
        this.contactTypeService.delete(contactType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contactTypes", this.contactTypeService.findAll());
        return "contactTypes";
    }

    @GetMapping("/editContactType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContactType contactType = this.contactTypeService.find(id);
        if (contactType == null) {
            model.addAttribute("addMessage", "Invalid contactType Id:" + id);
            model.addAttribute("contactTypes", this.contactTypeService.findAll());
            return "contactTypes";
        }
        model.addAttribute("contactType", contactType);
        return "updateContactType";
    }


}
