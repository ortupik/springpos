package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwManufacturerStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwManufacturerStatusService;

@Controller
public class HwManufacturerStatusController {

    private HwManufacturerStatusService hwManufacturerStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwManufacturerStatusController.class);

    ArrayList<HwManufacturerStatus> hwManufacturerStatusList = new ArrayList();

    @Autowired
    public void setHwManufacturerStatusService(HwManufacturerStatusService hwManufacturerStatusService) {
        this.hwManufacturerStatusService = hwManufacturerStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwManufacturerStatus/new")
    public String hwManufacturerStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwManufacturerStatus", new HwManufacturerStatus());
        setInstitution(model);
        return "hwManufacturerStatus";
    }

    @GetMapping("/hwManufacturerStatuss")
    public ModelAndView showCategories(HwManufacturerStatus hwManufacturerStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwManufacturerStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwManufacturerStatus", new HwManufacturerStatus());
            setInstitution(mv);
            hwManufacturerStatusList.clear();
            hwManufacturerStatusList = (ArrayList<HwManufacturerStatus>) this.hwManufacturerStatusService.findAll();
            mv.addObject("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "hwManufacturerStatus")
    public String save(@Valid HwManufacturerStatus hwManufacturerStatus, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwManufacturerStatus", new HwManufacturerStatus());
            return "hwManufacturerStatus";
        }
        hwManufacturerStatusService.save(hwManufacturerStatus);
        model.addAttribute("hwManufacturerStatus", new HwManufacturerStatus());
        model.addAttribute("addMessage", " HwManufacturerStatus Added Successfull ");
        return "hwManufacturerStatus";

    }

    @RequestMapping("/updateHwManufacturerStatus/{id}")
    public String updateHwManufacturerStatus(@PathVariable("id") int id, @Valid HwManufacturerStatus hwManufacturerStatus,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("hwManufacturerStatus", hwManufacturerStatus);
        this.hwManufacturerStatusService.update(hwManufacturerStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
        return "hwManufacturerStatuss";
    }

    @GetMapping("/removeHwManufacturerStatus/{id}")
    public String deleteHwManufacturerStatus(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        HwManufacturerStatus hwManufacturerStatus = this.hwManufacturerStatusService.find(id);
        if (hwManufacturerStatus == null) {
            model.addAttribute("addMessage", "Invalid hwManufacturerStatus Id:" + id);
            model.addAttribute("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
            return "hwManufacturerStatuss";
        }
        this.hwManufacturerStatusService.delete(hwManufacturerStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
        return "hwManufacturerStatuss";
    }

    @GetMapping("/editHwManufacturerStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        HwManufacturerStatus hwManufacturerStatus = this.hwManufacturerStatusService.find(id);
        if (hwManufacturerStatus == null) {
            model.addAttribute("addMessage", "Invalid hwManufacturerStatus Id:" + id);
            model.addAttribute("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
            return "hwManufacturerStatuss";
        }
        model.addAttribute("hwManufacturerStatus", hwManufacturerStatus);
        return "updateHwManufacturerStatus";
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
