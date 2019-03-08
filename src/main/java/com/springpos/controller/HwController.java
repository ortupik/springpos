package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Hw;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwService;

@Controller
public class HwController {

    private HwService hwService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwController.class);

    ArrayList<Hw> hwList = new ArrayList();

    @Autowired
    public void setHwService(HwService hwService) {
        this.hwService = hwService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hw/new")
    public String hwPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hw", new Hw());
        setInstitution(model);
        return "hw";
    }

    @GetMapping("/hws")
    public ModelAndView showCategories(Hw hw) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hws");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hw", new Hw());
            setInstitution(mv);
            hwList.clear();
            hwList = (ArrayList<Hw>) this.hwService.findAll();
            mv.addObject("hws", this.hwService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "hw")
    public String save(@Valid Hw hw, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hw", new Hw());
            return "hw";
        }
        hwService.save(hw);
        model.addAttribute("hw", new Hw());
        model.addAttribute("addMessage", " Hw Added Successfull ");
        return "hw";

    }

    @RequestMapping("/updateHw/{id}")
    public String updateHw(@PathVariable("id") int id, @Valid Hw hw,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("hw", hw);
        this.hwService.update(hw);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hws", this.hwService.findAll());
        return "hws";
    }

    @GetMapping("/removeHw/{id}")
    public String deleteHw(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Hw hw = this.hwService.find(id);
        if (hw == null) {
            model.addAttribute("addMessage", "Invalid hw Id:" + id);
            model.addAttribute("hws", this.hwService.findAll());
            return "hws";
        }
        this.hwService.delete(hw);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hws", this.hwService.findAll());
        return "hws";
    }

    @GetMapping("/editHw/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Hw hw = this.hwService.find(id);
        if (hw == null) {
            model.addAttribute("addMessage", "Invalid hw Id:" + id);
            model.addAttribute("hws", this.hwService.findAll());
            return "hws";
        }
        model.addAttribute("hw", hw);
        return "updateHw";
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
