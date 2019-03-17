package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.AssignmentStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.AssignmentStatusService;

@Controller
public class AssignmentStatusController {

    private AssignmentStatusService assignmentStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentStatusController.class);

    ArrayList<AssignmentStatus> assignmentStatusList = new ArrayList();

    @Autowired
    public void setAssignmentStatusService(AssignmentStatusService assignmentStatusService) {
        this.assignmentStatusService = assignmentStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("assignmentStatus/new")
    public String assignmentStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("assignmentStatus", new AssignmentStatus());
        mainService.setInstitution(model);
        return "assignmentStatus";
    }

    @GetMapping("/assignmentStatuss")
    public ModelAndView showCategories(AssignmentStatus assignmentStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("assignmentStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("assignmentStatus", new AssignmentStatus());
            mainService.setInstitution(mv);
            assignmentStatusList.clear();
            assignmentStatusList = (ArrayList<AssignmentStatus>) this.assignmentStatusService.findAll();
            mv.addObject("assignmentStatuss", this.assignmentStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "assignmentStatus")
    public String save(@Valid AssignmentStatus assignmentStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("assignmentStatus", new AssignmentStatus());
            return "assignmentStatus";
        }
        assignmentStatusService.save(assignmentStatus);
        model.addAttribute("assignmentStatus", new AssignmentStatus());
        model.addAttribute("addMessage", " AssignmentStatus Added Successfull ");
        return "assignmentStatus";

    }

    @RequestMapping("/updateAssignmentStatus/{id}")
    public String updateAssignmentStatus(@PathVariable("id") int id, @Valid AssignmentStatus assignmentStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("assignmentStatus", assignmentStatus);
        this.assignmentStatusService.update(assignmentStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("assignmentStatuss", this.assignmentStatusService.findAll());
        return "assignmentStatuss";
    }

    @GetMapping("/removeAssignmentStatus/{id}")
    public String deleteAssignmentStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        AssignmentStatus assignmentStatus = this.assignmentStatusService.find(id);
        if (assignmentStatus == null) {
            model.addAttribute("addMessage", "Invalid assignmentStatus Id:" + id);
            model.addAttribute("assignmentStatuss", this.assignmentStatusService.findAll());
            return "assignmentStatuss";
        }
        this.assignmentStatusService.delete(assignmentStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("assignmentStatuss", this.assignmentStatusService.findAll());
        return "assignmentStatuss";
    }

    @GetMapping("/editAssignmentStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        AssignmentStatus assignmentStatus = this.assignmentStatusService.find(id);
        if (assignmentStatus == null) {
            model.addAttribute("addMessage", "Invalid assignmentStatus Id:" + id);
            model.addAttribute("assignmentStatuss", this.assignmentStatusService.findAll());
            return "assignmentStatuss";
        }
        model.addAttribute("assignmentStatus", assignmentStatus);
        return "updateAssignmentStatus";
    }

  

}
