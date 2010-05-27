package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwModelStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwModelStatusService;

@Controller
public class HwModelStatusController {

    private HwModelStatusService hwModelStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwModelStatusController.class);

    ArrayList<HwModelStatus> hwModelStatusList = new ArrayList();

    @Autowired
    public void setHwModelStatusService(HwModelStatusService hwModelStatusService) {
        this.hwModelStatusService = hwModelStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwModelStatus/new")
    public String hwModelStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwModelStatus", new HwModelStatus());
        mainService.setInstitution(model);
        return "hwModelStatus";
    }

    @GetMapping("/hwModelStatuss")
    public ModelAndView showCategories(HwModelStatus hwModelStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwModelStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwModelStatus", new HwModelStatus());
            mainService.setInstitution(mv);
            hwModelStatusList.clear();
            hwModelStatusList = (ArrayList<HwModelStatus>) this.hwModelStatusService.findAll();
            mv.addObject("hwModelStatuss", this.hwModelStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwModelStatus")
    public String save(@Valid HwModelStatus hwModelStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwModelStatus", new HwModelStatus());
            return "hwModelStatus";
        }
        hwModelStatusService.save(hwModelStatus);
        model.addAttribute("hwModelStatus", new HwModelStatus());
        model.addAttribute("addMessage", " HwModelStatus Added Successfull ");
        return "hwModelStatus";

    }

    @RequestMapping("/updateHwModelStatus/{id}")
    public String updateHwModelStatus(@PathVariable("id") int id, @Valid HwModelStatus hwModelStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwModelStatus", hwModelStatus);
        this.hwModelStatusService.update(hwModelStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwModelStatuss", this.hwModelStatusService.findAll());
        return "hwModelStatuss";
    }

    @GetMapping("/removeHwModelStatus/{id}")
    public String deleteHwModelStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwModelStatus hwModelStatus = this.hwModelStatusService.find(id);
        if (hwModelStatus == null) {
            model.addAttribute("addMessage", "Invalid hwModelStatus Id:" + id);
            model.addAttribute("hwModelStatuss", this.hwModelStatusService.findAll());
            return "hwModelStatuss";
        }
        this.hwModelStatusService.delete(hwModelStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwModelStatuss", this.hwModelStatusService.findAll());
        return "hwModelStatuss";
    }

    @GetMapping("/editHwModelStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwModelStatus hwModelStatus = this.hwModelStatusService.find(id);
        if (hwModelStatus == null) {
            model.addAttribute("addMessage", "Invalid hwModelStatus Id:" + id);
            model.addAttribute("hwModelStatuss", this.hwModelStatusService.findAll());
            return "hwModelStatuss";
        }
        model.addAttribute("hwModelStatus", hwModelStatus);
        return "updateHwModelStatus";
    }


}
