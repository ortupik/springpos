package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwManufacturer;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwManufacturerService;
import com.springpos.service.HwManufacturerStatusService;

@Controller
public class HwManufacturerController {

    private HwManufacturerService hwManufacturerService;
    private HwManufacturerStatusService hwManufacturerStatusService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwManufacturerController.class);

    ArrayList<HwManufacturer> hwManufacturerList = new ArrayList();

    @Autowired
    public void setHwManufacturerService(HwManufacturerService hwManufacturerService) {
        this.hwManufacturerService = hwManufacturerService;
    }

    @Autowired
    public void setHwManufacturerStatusService(HwManufacturerStatusService hwManufacturerStatusService) {
        this.hwManufacturerStatusService = hwManufacturerStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwManufacturer/new")
    public String hwManufacturerPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwManufacturerStatuss", this.hwManufacturerStatusService.findAll());
        model.addAttribute("hwManufacturer", new HwManufacturer());
        mainService.setInstitution(model);
        return "hwManufacturer";
    }

    @GetMapping("/hwManufacturers")
    public ModelAndView showCategories(HwManufacturer hwManufacturer) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwManufacturers");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwManufacturer", new HwManufacturer());
            mainService.setInstitution(mv);
            hwManufacturerList.clear();
            hwManufacturerList = (ArrayList<HwManufacturer>) this.hwManufacturerService.findAll();
            mv.addObject("hwManufacturers", this.hwManufacturerService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwManufacturer")
    public String save(@Valid HwManufacturer hwManufacturer, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwManufacturer", new HwManufacturer());
            return "hwManufacturer";
        }
        hwManufacturerService.save(hwManufacturer);
        model.addAttribute("hwManufacturer", new HwManufacturer());
        model.addAttribute("addMessage", " HwManufacturer Added Successfull ");
        return "hwManufacturer";

    }

    @RequestMapping("/updateHwManufacturer/{id}")
    public String updateHwManufacturer(@PathVariable("id") int id, @Valid HwManufacturer hwManufacturer,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwManufacturer", hwManufacturer);
        this.hwManufacturerService.update(hwManufacturer);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwManufacturers", this.hwManufacturerService.findAll());
        return "hwManufacturers";
    }

    @GetMapping("/removeHwManufacturer/{id}")
    public String deleteHwManufacturer(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwManufacturer hwManufacturer = this.hwManufacturerService.find(id);
        if (hwManufacturer == null) {
            model.addAttribute("addMessage", "Invalid hwManufacturer Id:" + id);
            model.addAttribute("hwManufacturers", this.hwManufacturerService.findAll());
            return "hwManufacturers";
        }
        this.hwManufacturerService.delete(hwManufacturer);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwManufacturers", this.hwManufacturerService.findAll());
        return "hwManufacturers";
    }

    @GetMapping("/editHwManufacturer/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwManufacturer hwManufacturer = this.hwManufacturerService.find(id);
        if (hwManufacturer == null) {
            model.addAttribute("addMessage", "Invalid hwManufacturer Id:" + id);
            model.addAttribute("hwManufacturers", this.hwManufacturerService.findAll());
            return "hwManufacturers";
        }
        model.addAttribute("hwManufacturer", hwManufacturer);
        return "updateHwManufacturer";
    }

}
