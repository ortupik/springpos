package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwProvider;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwProviderService;
import com.springpos.service.HwProviderStatusService;

@Controller
public class HwProviderController {

    private HwProviderService hwProviderService;
private HwProviderStatusService hwProviderStatusService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwProviderController.class);

    ArrayList<HwProvider> hwProviderList = new ArrayList();

    @Autowired
    public void setHwProviderService(HwProviderService hwProviderService) {
        this.hwProviderService = hwProviderService;
    }
    @Autowired
    public void setHwProviderStatusService(HwProviderStatusService hwProviderStatusService) {
        this.hwProviderStatusService = hwProviderStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwProvider/new")
    public String hwProviderPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwProvider", new HwProvider());
        model.addAttribute("hwProviderStatuss",this.hwProviderStatusService.findAll());
        mainService.setInstitution(model);
        return "hwProvider";
    }

    @GetMapping("/hwProviders")
    public ModelAndView showCategories(HwProvider hwProvider) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwProviders");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwProvider", new HwProvider());
            mainService.setInstitution(mv);
            hwProviderList.clear();
            hwProviderList = (ArrayList<HwProvider>) this.hwProviderService.findAll();
            mv.addObject("hwProviders", this.hwProviderService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwProvider")
    public String save(@Valid HwProvider hwProvider, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwProvider", new HwProvider());
            return "hwProvider";
        }
        hwProviderService.save(hwProvider);
        model.addAttribute("hwProvider", new HwProvider());
        model.addAttribute("addMessage", " HwProvider Added Successfull ");
        return "hwProvider";

    }

    @RequestMapping("/updateHwProvider/{id}")
    public String updateHwProvider(@PathVariable("id") int id, @Valid HwProvider hwProvider,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwProvider", hwProvider);
        this.hwProviderService.update(hwProvider);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwProviders", this.hwProviderService.findAll());
        return "hwProviders";
    }

    @GetMapping("/removeHwProvider/{id}")
    public String deleteHwProvider(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwProvider hwProvider = this.hwProviderService.find(id);
        if (hwProvider == null) {
            model.addAttribute("addMessage", "Invalid hwProvider Id:" + id);
            model.addAttribute("hwProviders", this.hwProviderService.findAll());
            return "hwProviders";
        }
        this.hwProviderService.delete(hwProvider);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwProviders", this.hwProviderService.findAll());
        return "hwProviders";
    }

    @GetMapping("/editHwProvider/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwProvider hwProvider = this.hwProviderService.find(id);
        if (hwProvider == null) {
            model.addAttribute("addMessage", "Invalid hwProvider Id:" + id);
            model.addAttribute("hwProviders", this.hwProviderService.findAll());
            return "hwProviders";
        }
        model.addAttribute("hwProvider", hwProvider);
        return "updateHwProvider";
    }


}
