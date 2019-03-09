package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwInventoryStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwInventoryStatusService;

@Controller
public class HwInventoryStatusController {

    private HwInventoryStatusService hwInventoryStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwInventoryStatusController.class);

    ArrayList<HwInventoryStatus> hwInventoryStatusList = new ArrayList();

    @Autowired
    public void setHwInventoryStatusService(HwInventoryStatusService hwInventoryStatusService) {
        this.hwInventoryStatusService = hwInventoryStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwInventoryStatus/new")
    public String hwInventoryStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwInventoryStatus", new HwInventoryStatus());
        setInstitution(model);
        return "hwInventoryStatus";
    }

    @GetMapping("/hwInventoryStatuss")
    public ModelAndView showCategories(HwInventoryStatus hwInventoryStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwInventoryStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwInventoryStatus", new HwInventoryStatus());
            setInstitution(mv);
            hwInventoryStatusList.clear();
            hwInventoryStatusList = (ArrayList<HwInventoryStatus>) this.hwInventoryStatusService.findAll();
            mv.addObject("hwInventoryStatuss", this.hwInventoryStatusService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "hwInventoryStatus")
    public String save(@Valid HwInventoryStatus hwInventoryStatus, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwInventoryStatus", new HwInventoryStatus());
            return "hwInventoryStatus";
        }
        hwInventoryStatusService.save(hwInventoryStatus);
        model.addAttribute("hwInventoryStatus", new HwInventoryStatus());
        model.addAttribute("addMessage", " HwInventoryStatus Added Successfull ");
        return "hwInventoryStatus";

    }

    @RequestMapping("/updateHwInventoryStatus/{id}")
    public String updateHwInventoryStatus(@PathVariable("id") int id, @Valid HwInventoryStatus hwInventoryStatus,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("hwInventoryStatus", hwInventoryStatus);
        this.hwInventoryStatusService.update(hwInventoryStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwInventoryStatuss", this.hwInventoryStatusService.findAll());
        return "hwInventoryStatuss";
    }

    @GetMapping("/removeHwInventoryStatus/{id}")
    public String deleteHwInventoryStatus(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        HwInventoryStatus hwInventoryStatus = this.hwInventoryStatusService.find(id);
        if (hwInventoryStatus == null) {
            model.addAttribute("addMessage", "Invalid hwInventoryStatus Id:" + id);
            model.addAttribute("hwInventoryStatuss", this.hwInventoryStatusService.findAll());
            return "hwInventoryStatuss";
        }
        this.hwInventoryStatusService.delete(hwInventoryStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwInventoryStatuss", this.hwInventoryStatusService.findAll());
        return "hwInventoryStatuss";
    }

    @GetMapping("/editHwInventoryStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        HwInventoryStatus hwInventoryStatus = this.hwInventoryStatusService.find(id);
        if (hwInventoryStatus == null) {
            model.addAttribute("addMessage", "Invalid hwInventoryStatus Id:" + id);
            model.addAttribute("hwInventoryStatuss", this.hwInventoryStatusService.findAll());
            return "hwInventoryStatuss";
        }
        model.addAttribute("hwInventoryStatus", hwInventoryStatus);
        return "updateHwInventoryStatus";
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
