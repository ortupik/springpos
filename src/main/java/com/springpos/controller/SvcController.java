package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Svc;
import com.springpos.service.MainService;
import com.springpos.service.ServiceStatusService;
import com.springpos.service.ServiceTypeService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.SvcService;

@Controller
public class SvcController {

    private SvcService svcService;
    private ServiceStatusService statusService;
    private ServiceTypeService typeService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SvcController.class);

    ArrayList<Svc> svcList = new ArrayList();

    @Autowired
    public void setStatusService(ServiceStatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setTypeService(ServiceTypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setSvcService(SvcService svcService) {
        this.svcService = svcService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("svc/new")
    public String svcPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("svc", new Svc());
        mainService.setInstitution(model);
        return "svc";
    }

    @GetMapping("/svcs")
    public ModelAndView showCategories(Svc svc) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("svcs");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("svc", new Svc());
            mainService.setInstitution(mv);
            svcList.clear();
            svcList = (ArrayList<Svc>) this.svcService.findAll();
            mv.addObject("svcs", this.svcService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "svc")
    public String save(@Valid Svc svc, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("svc", new Svc());
            return "svc";
        }
        svcService.save(svc);
        model.addAttribute("serviceTypes", this.typeService.findAll());
        model.addAttribute("serviceStatuss", this.statusService.findAll());
        model.addAttribute("svc", new Svc());
        model.addAttribute("addMessage", " Svc Added Successfull ");
        return "svc";

    }

    @RequestMapping("/updateSvc/{id}")
    public String updateSvc(@PathVariable("id") int id, @Valid Svc svc,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("svc", svc);
        this.svcService.update(svc);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("svcs", this.svcService.findAll());
        return "svcs";
    }

    @GetMapping("/removeSvc/{id}")
    public String deleteSvc(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Svc svc = this.svcService.find(id);
        if (svc == null) {
            model.addAttribute("addMessage", "Invalid svc Id:" + id);
            model.addAttribute("svcs", this.svcService.findAll());
            return "svcs";
        }
        this.svcService.delete(svc);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("svcs", this.svcService.findAll());
        return "svcs";
    }

    @GetMapping("/editSvc/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Svc svc = this.svcService.find(id);
        if (svc == null) {
            model.addAttribute("addMessage", "Invalid svc Id:" + id);
            model.addAttribute("svcs", this.svcService.findAll());
            return "svcs";
        }
        model.addAttribute("svc", svc);
        return "updateSvc";
    }

}
