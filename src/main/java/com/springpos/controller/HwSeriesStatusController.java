package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwSeriesStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwSeriesStatusService;

@Controller
public class HwSeriesStatusController {

    private HwSeriesStatusService hwSeriesStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwSeriesStatusController.class);

    ArrayList<HwSeriesStatus> hwSeriesStatusList = new ArrayList();

    @Autowired
    public void setHwSeriesStatusService(HwSeriesStatusService hwSeriesStatusService) {
        this.hwSeriesStatusService = hwSeriesStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwSeriesStatus/new")
    public String hwSeriesStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwSeriesStatus", new HwSeriesStatus());
        mainService.setInstitution(model);
        return "hwSeriesStatus";
    }

    @GetMapping("/hwSeriesStatuss")
    public ModelAndView showCategories(HwSeriesStatus hwSeriesStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwSeriesStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwSeriesStatus", new HwSeriesStatus());
            mainService.setInstitution(mv);
            hwSeriesStatusList.clear();
            hwSeriesStatusList = (ArrayList<HwSeriesStatus>) this.hwSeriesStatusService.findAll();
            mv.addObject("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwSeriesStatus")
    public String save(@Valid HwSeriesStatus hwSeriesStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwSeriesStatus", new HwSeriesStatus());
            return "hwSeriesStatus";
        }
        hwSeriesStatusService.save(hwSeriesStatus);
        model.addAttribute("hwSeriesStatus", new HwSeriesStatus());
        model.addAttribute("addMessage", " HwSeriesStatus Added Successfull ");
        return "hwSeriesStatus";

    }

    @RequestMapping("/updateHwSeriesStatus/{id}")
    public String updateHwSeriesStatus(@PathVariable("id") int id, @Valid HwSeriesStatus hwSeriesStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwSeriesStatus", hwSeriesStatus);
        this.hwSeriesStatusService.update(hwSeriesStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
        return "hwSeriesStatuss";
    }

    @GetMapping("/removeHwSeriesStatus/{id}")
    public String deleteHwSeriesStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSeriesStatus hwSeriesStatus = this.hwSeriesStatusService.find(id);
        if (hwSeriesStatus == null) {
            model.addAttribute("addMessage", "Invalid hwSeriesStatus Id:" + id);
            model.addAttribute("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
            return "hwSeriesStatuss";
        }
        this.hwSeriesStatusService.delete(hwSeriesStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
        return "hwSeriesStatuss";
    }

    @GetMapping("/editHwSeriesStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSeriesStatus hwSeriesStatus = this.hwSeriesStatusService.find(id);
        if (hwSeriesStatus == null) {
            model.addAttribute("addMessage", "Invalid hwSeriesStatus Id:" + id);
            model.addAttribute("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
            return "hwSeriesStatuss";
        }
        model.addAttribute("hwSeriesStatus", hwSeriesStatus);
        return "updateHwSeriesStatus";
    }


}
