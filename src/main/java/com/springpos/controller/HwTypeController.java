package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwTypeService;

@Controller
public class HwTypeController {

    private HwTypeService hwTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwTypeController.class);

    ArrayList<HwType> hwTypeList = new ArrayList();

    @Autowired
    public void setHwTypeService(HwTypeService hwTypeService) {
        this.hwTypeService = hwTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwType/new")
    public String hwTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwType", new HwType());
        mainService.setInstitution(model);
        return "hwType";
    }

    @GetMapping("/hwTypes")
    public ModelAndView showCategories(HwType hwType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwType", new HwType());
            mainService.setInstitution(mv);
            hwTypeList.clear();
            hwTypeList = (ArrayList<HwType>) this.hwTypeService.findAll();
            mv.addObject("hwTypes", this.hwTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwType")
    public String save(@Valid HwType hwType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwType", new HwType());
            return "hwType";
        }
        hwTypeService.save(hwType);
        model.addAttribute("hwType", new HwType());
        model.addAttribute("addMessage", " HwType Added Successfull ");
        return "hwType";

    }

    @RequestMapping("/updateHwType/{id}")
    public String updateHwType(@PathVariable("id") int id, @Valid HwType hwType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwType", hwType);
        this.hwTypeService.update(hwType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwTypes", this.hwTypeService.findAll());
        return "hwTypes";
    }

    @GetMapping("/removeHwType/{id}")
    public String deleteHwType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwType hwType = this.hwTypeService.find(id);
        if (hwType == null) {
            model.addAttribute("addMessage", "Invalid hwType Id:" + id);
            model.addAttribute("hwTypes", this.hwTypeService.findAll());
            return "hwTypes";
        }
        this.hwTypeService.delete(hwType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwTypes", this.hwTypeService.findAll());
        return "hwTypes";
    }

    @GetMapping("/editHwType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwType hwType = this.hwTypeService.find(id);
        if (hwType == null) {
            model.addAttribute("addMessage", "Invalid hwType Id:" + id);
            model.addAttribute("hwTypes", this.hwTypeService.findAll());
            return "hwTypes";
        }
        model.addAttribute("hwType", hwType);
        return "updateHwType";
    }

 
}
