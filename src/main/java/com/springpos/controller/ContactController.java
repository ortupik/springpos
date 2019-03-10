package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Contact;
import com.springpos.bean.ContactStatus;
import com.springpos.bean.ContactType;
import com.springpos.bean.CustomerSite;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContactService;
import com.springpos.service.ContactStatusService;
import com.springpos.service.ContactTypeService;
import com.springpos.service.CountryService;
import com.springpos.service.CustomerSiteService;
import com.springpos.service.StateService;

@Controller
public class ContactController {

    private ContactService contactService;
    private ContactTypeService typeService;
    private ContactStatusService statusService;
    private CountryService countryService;
    private StateService stateService;
    private MainService mainService;
    private CustomerSiteService customerSiteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    ArrayList<Contact> contactList = new ArrayList();

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setTypeService(ContactTypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setStatusService(ContactStatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setCustomerSiteService(CustomerSiteService customerSiteService) {
        this.customerSiteService = customerSiteService;
    }

    @RequestMapping("contact/new")
    public String contactPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("contactTypes", this.typeService.findAll());
        model.addAttribute("contactStatuss", this.statusService.findAll());
        model.addAttribute("countries", this.countryService.findAll());
        model.addAttribute("states", this.stateService.findAll());
        model.addAttribute("contact", new Contact());
        setInstitution(model);
        return "contact";
    }

    @GetMapping("/contacts")
    public ModelAndView showCategories(Contact contact) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contacts");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("contact", new Contact());
            setInstitution(mv);
            contactList.clear();
            for (Contact con : this.contactService.findAll()) {
                ContactType type = typeService.find(con.getContact_type_id());
                con.setType(type.getContact_type());
                ContactStatus status = statusService.find(con.getContact_status_id());
                con.setStatus(status.getContact_status());
                contactList.add(con);
            }
            mv.addObject("contacts", contactList);
        }
        return mv;
    }

    @PostMapping(value = "contact")
    public String save(@Valid Contact contact, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            model.addAttribute("contact", new Contact());
            return "contact";
        }
        CustomerSite cust = new CustomerSite(contact);
        if (cust == null) {
            model.addAttribute("addMessage", contact.toString());
            model.addAttribute("contact", new Contact());
            return "contact";
        }
        CustomerSite newCust = customerSiteService.save(cust);
        contact.setCust_site_id(newCust.getCust_site_status_id());
        Contact contac = contactService.save(contact);
        model.addAttribute("contact", new Contact());
        model.addAttribute("addMessage", " Contact Added Successfull ");
        return "contact";

    }

    @RequestMapping("/updateContact/{id}")
    public String updateContact(@PathVariable("id") int id, @Valid Contact contact,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("contact", contact);
        this.contactService.update(contact);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contacts", this.contactService.findAll());
        return "contacts";
    }

    @GetMapping("/removeContact/{id}")
    public String deleteContact(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Contact contact = this.contactService.find(id);
        if (contact == null) {
            model.addAttribute("addMessage", "Invalid contact Id:" + id);
            model.addAttribute("contacts", this.contactService.findAll());
            return "contacts";
        }
        this.contactService.delete(contact);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contacts", this.contactService.findAll());
        return "contacts";
    }

    @GetMapping("/editContact/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Contact contact = this.contactService.find(id);
        if (contact == null) {
            model.addAttribute("addMessage", "Invalid contact Id:" + id);
            model.addAttribute("contacts", this.contactService.findAll());
            return "contacts";
        }
        model.addAttribute("contact", contact);
        return "updateContact";
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
