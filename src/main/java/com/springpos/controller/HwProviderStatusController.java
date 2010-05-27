package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwProviderStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwProviderStatusService;

@Controller
public class HwProviderStatusController {

    private HwProviderStatusService hwProviderStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwProviderStatusController.class);

    ArrayList<HwProviderStatus> hwProviderStatusList = new ArrayList();

    @Autowired
    public void setHwProviderStatusService(HwProviderStatusService hwProviderStatusService) {
        this.hwProviderStatusService = hwProviderStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwProviderStatus/new")
    public String hwProviderStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwProviderStatus", new HwProviderStatus());
        mainService.setInstitution(model);
        return "hwProviderStatus";
    }

    @GetMapping("/hwProviderStatuss")
    public ModelAndView showCategories(HwProviderStatus hwProviderStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwProviderStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwProviderStatus", new HwProviderStatus());
            mainService.setInstitution(mv);
            hwProviderStatusList.clear();
            hwProviderStatusList = (ArrayList<HwProviderStatus>) this.hwProviderStatusService.findAll();
            mv.addObject("hwProviderStatuss", this.hwProviderStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwProviderStatus")
    public String save(@Valid HwProviderStatus hwProviderStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwProviderStatus", new HwProviderStatus());
            return "hwProviderStatus";
        }
        hwProviderStatusService.save(hwProviderStatus);
        model.addAttribute("hwProviderStatus", new HwProviderStatus());
        model.addAttribute("addMessage", " HwProviderStatus Added Successfull ");
        return "hwProviderStatus";

    }

    @RequestMapping("/updateHwProviderStatus/{id}")
    public String updateHwProviderStatus(@PathVariable("id") int id, @Valid HwProviderStatus hwProviderStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwProviderStatus", hwProviderStatus);
        this.hwProviderStatusService.update(hwProviderStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwProviderStatuss", this.hwProviderStatusService.findAll());
        return "hwProviderStatuss";
    }

    @GetMapping("/removeHwProviderStatus/{id}")
    public String deleteHwProviderStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwProviderStatus hwProviderStatus = this.hwProviderStatusService.find(id);
        if (hwProviderStatus == null) {
            model.addAttribute("addMessage", "Invalid hwProviderStatus Id:" + id);
            model.addAttribute("hwProviderStatuss", this.hwProviderStatusService.findAll());
            return "hwProviderStatuss";
        }
        this.hwProviderStatusService.delete(hwProviderStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwProviderStatuss", this.hwProviderStatusService.findAll());
        return "hwProviderStatuss";
    }

    @GetMapping("/editHwProviderStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwProviderStatus hwProviderStatus = this.hwProviderStatusService.find(id);
        if (hwProviderStatus == null) {
            model.addAttribute("addMessage", "Invalid hwProviderStatus Id:" + id);
            model.addAttribute("hwProviderStatuss", this.hwProviderStatusService.findAll());
            return "hwProviderStatuss";
        }
        model.addAttribute("hwProviderStatus", hwProviderStatus);
        return "updateHwProviderStatus";
    }


}
