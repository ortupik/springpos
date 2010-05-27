package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.HwSvoLine;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.HwSvoLineService;

@Controller
public class HwSvoLineController {

    private HwSvoLineService hwSvoLineService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HwSvoLineController.class);

    ArrayList<HwSvoLine> hwSvoLineList = new ArrayList();

    @Autowired
    public void setHwSvoLineService(HwSvoLineService hwSvoLineService) {
        this.hwSvoLineService = hwSvoLineService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("hwSvoLine/new")
    public String hwSvoLinePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("hwSvoLine", new HwSvoLine());
        mainService.setInstitution(model);
        return "hwSvoLine";
    }

    @GetMapping("/hwSvoLines")
    public ModelAndView showCategories(HwSvoLine hwSvoLine) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hwSvoLines");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("hwSvoLine", new HwSvoLine());
            mainService.setInstitution(mv);
            hwSvoLineList.clear();
            hwSvoLineList = (ArrayList<HwSvoLine>) this.hwSvoLineService.findAll();
            mv.addObject("hwSvoLines", this.hwSvoLineService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "hwSvoLine")
    public String save(@Valid HwSvoLine hwSvoLine, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("hwSvoLine", new HwSvoLine());
            return "hwSvoLine";
        }
        hwSvoLineService.save(hwSvoLine);
        model.addAttribute("hwSvoLine", new HwSvoLine());
        model.addAttribute("addMessage", " HwSvoLine Added Successfull ");
        return "hwSvoLine";

    }

    @RequestMapping("/updateHwSvoLine/{id}")
    public String updateHwSvoLine(@PathVariable("id") int id, @Valid HwSvoLine hwSvoLine,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("hwSvoLine", hwSvoLine);
        this.hwSvoLineService.update(hwSvoLine);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("hwSvoLines", this.hwSvoLineService.findAll());
        return "hwSvoLines";
    }

    @GetMapping("/removeHwSvoLine/{id}")
    public String deleteHwSvoLine(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSvoLine hwSvoLine = this.hwSvoLineService.find(id);
        if (hwSvoLine == null) {
            model.addAttribute("addMessage", "Invalid hwSvoLine Id:" + id);
            model.addAttribute("hwSvoLines", this.hwSvoLineService.findAll());
            return "hwSvoLines";
        }
        this.hwSvoLineService.delete(hwSvoLine);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("hwSvoLines", this.hwSvoLineService.findAll());
        return "hwSvoLines";
    }

    @GetMapping("/editHwSvoLine/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        HwSvoLine hwSvoLine = this.hwSvoLineService.find(id);
        if (hwSvoLine == null) {
            model.addAttribute("addMessage", "Invalid hwSvoLine Id:" + id);
            model.addAttribute("hwSvoLines", this.hwSvoLineService.findAll());
            return "hwSvoLines";
        }
        model.addAttribute("hwSvoLine", hwSvoLine);
        return "updateHwSvoLine";
    }


}
