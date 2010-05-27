package com.springpos.controller;

import com.springpos.bean.Contractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.CustomerSite;
import com.springpos.bean.ServiceOrder;
import com.springpos.bean.Summary;
import com.springpos.service.ContractorService;
import com.springpos.service.CountryService;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CustomerSiteService;
import com.springpos.service.ServiceOrderService;
import com.springpos.service.ServiceTypeService;
import com.springpos.service.StateService;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
public class SequenceController {

    private CustomerSiteService customerSiteService;
    private CountryService countryService;
    private StateService stateService;
    private MainService mainService;
    private ServiceOrderService serviceOrderService;
    private ContractorService contractorService;
    private ServiceTypeService serviceTypeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceController.class);

    ArrayList<CustomerSite> customerSiteList = new ArrayList();

    @Autowired
    public void setServiceTypeService(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @Autowired
    public void setServiceOrderService(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @Autowired
    public void setContractorService(ContractorService contractorService) {
        this.contractorService = contractorService;
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

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/createOrder")
    public ModelAndView createOrder() {
        ModelAndView mv = new ModelAndView("createOrder");
        mainService.setInstitution(mv);
        return mv;
    }

    @RequestMapping("/newCustomer")
    public ModelAndView newOrder() {
        ModelAndView mv = new ModelAndView("newOrder");
        mv.addObject("countries", this.countryService.findAll());
        mv.addObject("states", this.stateService.findAll());
        mv.addObject("customerSite", new CustomerSite());
        mainService.setInstitution(mv);
        return mv;
    }

    @RequestMapping("/regularCustomer")
    public ModelAndView regularCustomer() {
        ModelAndView mv = new ModelAndView("selectCustomer");
        mv.addObject("customerSite", new CustomerSite());
        mv.addObject("customerSites", this.customerSiteService.findAll());
        mainService.setInstitution(mv);
        return mv;
    }

    @RequestMapping(value = "page2")
    public String page2(@Valid CustomerSite customerSite, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            model.addAttribute("customerSite", new CustomerSite());
            return "newOrder";
        }
        this.mainService.pageTwo(customerSite);
        model.addAttribute("serviceOrder", new ServiceOrder());
        model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
        return "serviceOrder";
    }

    @RequestMapping("/selectCustomer/{id}")
    public String selectCustomer(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        CustomerSite customerSite = this.customerSiteService.find(id);
        if (customerSite == null) {
            model.addAttribute("customerSites", this.customerSiteService.findAll());
            return "selectCustomer";
        }
        this.mainService.pageTwo(customerSite);
        model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
        model.addAttribute("serviceOrder", new ServiceOrder());
        return "serviceOrder";
    }

    @RequestMapping(value = "page3")
    public String page3(@Valid ServiceOrder serviceOrder, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            model.addAttribute("serviceOrder", new ServiceOrder());
            model.addAttribute("serviceTypes", this.serviceTypeService.findAll());
            return "serviceOrder";
        }
        if (mainService.getSequence() == null) {
            return "redirect:/createOrder";
        }
        this.mainService.pageThree(serviceOrder);
        model.addAttribute("contractors", this.contractorService.findAll());
        return "selectContract";
    }

    @RequestMapping("/selectContractor/{id}")
    public String page4(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        if (mainService.getSequence() == null) {
            return "redirect:/createOrder";
        }
        Contractor contractor = this.contractorService.find(id);
        if (contractor == null) {
            model.addAttribute("contractors", this.contractorService.findAll());
            return "selectContract";
        }
        this.mainService.pageFour(contractor);
        Summary summary = new Summary(mainService.getSequence().getCustomer(), mainService.getSequence().getServiceOrder(), mainService.getSequence().getContractor());
        model.addAttribute("summary", summary);
        return "summaryPage";
    }

    @RequestMapping("/summaryPage")
    public String summaryPage(@Valid Summary summary, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            summary = new Summary(mainService.getSequence().getCustomer(), mainService.getSequence().getServiceOrder(), mainService.getSequence().getContractor());
            model.addAttribute("summary", summary);
            return "summaryPage";
        }
        if (mainService.getSequence() == null) {
            return "redirect:/createOrder";
        }
        LocalDateTime start = summary.getDateRecieved().toLocalDate().atTime(LocalTime.now());
        LocalDateTime end = summary.getDateScheduled().toLocalDate().atTime(LocalTime.now());
        mainService.getSequence().getServiceOrder().setDate_started(start);
        mainService.getSequence().getServiceOrder().setDate_finished(end);
        mainService.executeSequence();
        mainService.setInstitution(model);
        model.addAttribute("addMessage", "Your order has been successful!");
        return "redirect:/createOrder";
    }

}
