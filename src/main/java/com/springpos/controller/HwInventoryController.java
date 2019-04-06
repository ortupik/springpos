package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwInventory;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwInventoryService;
import com.springpos.service.HwInventoryStatusService;
import com.springpos.service.HwModelService;
import com.springpos.service.HwProviderService;
import com.springpos.service.HwProviderService;

@Controller
public class HwInventoryController {

    private HwInventoryService hwInventoryService;
    private HwProviderService hwProviderService;
    private HwInventoryStatusService hwInventoryStatusService;
    private HwModelService hwModelService;
    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwInventoryController.class);

    ArrayList<HwInventory> hwInventoryList = new ArrayList();

    @Autowired
    public void setHwProviderService(HwProviderService hwProviderService) {
        this.hwProviderService = hwProviderService;
    }

    @Autowired
    public void setHwInventoryStatusService(HwInventoryStatusService hwInventoryStatusService) {
        this.hwInventoryStatusService = hwInventoryStatusService;
    }

    @Autowired
    public void setHwModelService(HwModelService hwModelService) {
        this.hwModelService = hwModelService;
    }

    @Autowired
    public void setHwInventoryService(HwInventoryService hwInventoryService) {
        this.hwInventoryService = hwInventoryService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

 
    @RequestMapping("hwInventory/new")
    public String hwInventoryPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("hwProviders",this.hwProviderService.findAll());
        model.addAttribute("hwModels",this.hwModelService.findAll());
         model.addAttribute("hwInventoryStatuss",this.hwInventoryStatusService.findAll());
        model.addAttribute("hwInventory", new HwInventory());
        mainService.setInstitution(model);
        return "hwInventory";
    }

    @GetMapping("/hwInventorys")
    public ModelAndView showCategories(HwInventory hwInventory) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwInventorys");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("hwInventory", new HwInventory());
            mainService.setInstitution(mv);
            hwInventoryList.clear();
            hwInventoryList = (ArrayList<HwInventory>) this.hwInventoryService.findAll();
            mv.addObject("hwInventorys", this.hwInventoryService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwInventory")
    public String save(@Valid HwInventory hwInventory, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwInventory", new HwInventory());
            return "hwInventory";
        }
        hwInventoryService.save(hwInventory);
        model.addAttribute("hwInventory", new HwInventory());
        model.addAttribute("addMessage", " HwInventory Added Successfull ");
        return "hwInventory";

    }

    @RequestMapping("/updateHwInventory/{id}")
    public String updateHwInventory(@PathVariable("id") int id, @Valid HwInventory hwInventory,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwInventory", hwInventory);
        this.hwInventoryService.update(hwInventory);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwInventorys", this.hwInventoryService.findAll());
        return "hwInventorys";
    }

    @GetMapping("/removeHwInventory/{id}")
    public String deleteHwInventory(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwInventory hwInventory = this.hwInventoryService.find(id);
        if (hwInventory == null) {
            model.addAttribute("addMessage", "Invalid hwInventory Id:" + id);
            model.addAttribute("hwInventorys", this.hwInventoryService.findAll());
            return "hwInventorys";
        }
        this.hwInventoryService.delete(hwInventory);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwInventorys", this.hwInventoryService.findAll());
        return "hwInventorys";
    }

    @GetMapping("/editHwInventory/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwInventory hwInventory = this.hwInventoryService.find(id);
        if (hwInventory == null) {
            model.addAttribute("addMessage", "Invalid hwInventory Id:" + id);
            model.addAttribute("hwInventorys", this.hwInventoryService.findAll());
            return "hwInventorys";
        }
        model.addAttribute("hwInventory", hwInventory);
        return "updateHwInventory";
    }

}
