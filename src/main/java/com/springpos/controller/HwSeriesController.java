package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwSeries;
import com.springpos.service.HwManufacturerService;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwSeriesService;
import com.springpos.service.HwSeriesStatusService;
import com.springpos.service.HwTypeService;

@Controller
public class HwSeriesController {

    private HwSeriesService hwSeriesService;
    private HwTypeService hwTypeService;
    private MainService mainService;
    private HwSeriesStatusService hwSeriesStatusService;
    private HwManufacturerService hwManufacturerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwSeriesController.class);

    ArrayList<HwSeries> hwSeriesList = new ArrayList();

    @Autowired
    public void setHwTypeService(HwTypeService hwTypeService) {
        this.hwTypeService = hwTypeService;
    }

    @Autowired
    public void setHwSeriesStatusService(HwSeriesStatusService hwSeriesStatusService) {
        this.hwSeriesStatusService = hwSeriesStatusService;
    }

    @Autowired
    public void setHwManufacturerService(HwManufacturerService hwManufacturerService) {
        this.hwManufacturerService = hwManufacturerService;
    }
  

    @Autowired
    public void setHwSeriesService(HwSeriesService hwSeriesService) {
        this.hwSeriesService = hwSeriesService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwSeries/new")
    public String hwSeriesPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwTypess", this.hwTypeService.findAll());
        model.addAttribute("hwSeriesStatuss", this.hwSeriesStatusService.findAll());
        model.addAttribute("hwManufacturers", this.hwManufacturerService.findAll());
        model.addAttribute("hwSeries", new HwSeries());
        mainService.setInstitution(model);
        return "hwSeries";
    }

    @GetMapping("/hwSeriess")
    public ModelAndView showCategories(HwSeries hwSeries) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwSeriess");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwSeries", new HwSeries());
            mainService.setInstitution(mv);
            hwSeriesList.clear();
            hwSeriesList = (ArrayList<HwSeries>) this.hwSeriesService.findAll();
            mv.addObject("hwSeriess", this.hwSeriesService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwSeries")
    public String save(@Valid HwSeries hwSeries, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwSeries", new HwSeries());
            return "hwSeries";
        }
        hwSeriesService.save(hwSeries);
        model.addAttribute("hwSeries", new HwSeries());
        model.addAttribute("addMessage", " HwSeries Added Successfull ");
        return "hwSeries";

    }

    @RequestMapping("/updateHwSeries/{id}")
    public String updateHwSeries(@PathVariable("id") int id, @Valid HwSeries hwSeries,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwSeries", hwSeries);
        this.hwSeriesService.update(hwSeries);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwSeriess", this.hwSeriesService.findAll());
        return "hwSeriess";
    }

    @GetMapping("/removeHwSeries/{id}")
    public String deleteHwSeries(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSeries hwSeries = this.hwSeriesService.find(id);
        if (hwSeries == null) {
            model.addAttribute("addMessage", "Invalid hwSeries Id:" + id);
            model.addAttribute("hwSeriess", this.hwSeriesService.findAll());
            return "hwSeriess";
        }
        this.hwSeriesService.delete(hwSeries);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwSeriess", this.hwSeriesService.findAll());
        return "hwSeriess";
    }

    @GetMapping("/editHwSeries/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSeries hwSeries = this.hwSeriesService.find(id);
        if (hwSeries == null) {
            model.addAttribute("addMessage", "Invalid hwSeries Id:" + id);
            model.addAttribute("hwSeriess", this.hwSeriesService.findAll());
            return "hwSeriess";
        }
        model.addAttribute("hwSeries", hwSeries);
        return "updateHwSeries";
    }

}
