package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwModel;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwModelService;
import com.springpos.service.HwModelStatusService;
import com.springpos.service.HwSeriesService;

@Controller
public class HwModelController {

    private HwModelService hwModelService;
    private HwModelStatusService hwModelStatusService;
    private HwSeriesService hwSeriesService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwModelController.class);

    ArrayList<HwModel> hwModelList = new ArrayList();

    @Autowired
    public void setHwModelStatusService(HwModelStatusService hwModelStatusService) {
        this.hwModelStatusService = hwModelStatusService;
    }

    @Autowired
    public void setHwSeriesService(HwSeriesService hwSeriesService) {
        this.hwSeriesService = hwSeriesService;
    }

    @Autowired
    public void setHwModelService(HwModelService hwModelService) {
        this.hwModelService = hwModelService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwModel/new")
    public String hwModelPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwSeriess", this.hwSeriesService.findAll());
        model.addAttribute("hwModelStatuss", this.hwModelStatusService.findAll());
        model.addAttribute("hwModel", new HwModel());
        mainService.setInstitution(model);
        return "hwModel";
    }

    @GetMapping("/hwModels")
    public ModelAndView showCategories(HwModel hwModel) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwModels");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwModel", new HwModel());
            mainService.setInstitution(mv);
            hwModelList.clear();
            hwModelList = (ArrayList<HwModel>) this.hwModelService.findAll();
            mv.addObject("hwModels", this.hwModelService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwModel")
    public String save(@Valid HwModel hwModel, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwModel", new HwModel());
            return "hwModel";
        }
        hwModelService.save(hwModel);
        model.addAttribute("hwModel", new HwModel());
        model.addAttribute("addMessage", " HwModel Added Successfull ");
        return "hwModel";

    }

    @RequestMapping("/updateHwModel/{id}")
    public String updateHwModel(@PathVariable("id") int id, @Valid HwModel hwModel,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwModel", hwModel);
        this.hwModelService.update(hwModel);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwModels", this.hwModelService.findAll());
        return "hwModels";
    }

    @GetMapping("/removeHwModel/{id}")
    public String deleteHwModel(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwModel hwModel = this.hwModelService.find(id);
        if (hwModel == null) {
            model.addAttribute("addMessage", "Invalid hwModel Id:" + id);
            model.addAttribute("hwModels", this.hwModelService.findAll());
            return "hwModels";
        }
        this.hwModelService.delete(hwModel);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwModels", this.hwModelService.findAll());
        return "hwModels";
    }

    @GetMapping("/editHwModel/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwModel hwModel = this.hwModelService.find(id);
        if (hwModel == null) {
            model.addAttribute("addMessage", "Invalid hwModel Id:" + id);
            model.addAttribute("hwModels", this.hwModelService.findAll());
            return "hwModels";
        }
        model.addAttribute("hwModel", hwModel);
        return "updateHwModel";
    }

}
