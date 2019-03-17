package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Assignment;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.AssignmentService;

@Controller
public class AssignmentController {

    private AssignmentService assignmentService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentController.class);

    ArrayList<Assignment> assignmentList = new ArrayList();

    @Autowired
    public void setAssignmentService(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("assignment/new")
    public String assignmentPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("assignment", new Assignment());
        mainService.setInstitution(model);
        return "assignment";
    }

    @GetMapping("/assignments")
    public ModelAndView showCategories(Assignment assignment) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("assignments");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("assignment", new Assignment());
            mainService.setInstitution(mv);
            assignmentList.clear();
            assignmentList = (ArrayList<Assignment>) this.assignmentService.findAll();
            mv.addObject("assignments", this.assignmentService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "assignment")
    public String save(@Valid Assignment assignment, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("assignment", new Assignment());
            return "assignment";
        }
        assignmentService.save(assignment);
        model.addAttribute("assignment", new Assignment());
        model.addAttribute("addMessage", " Assignment Added Successfull ");
        return "assignment";

    }

    @RequestMapping("/updateAssignment/{id}")
    public String updateAssignment(@PathVariable("id") int id, @Valid Assignment assignment,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("assignment", assignment);
        this.assignmentService.update(assignment);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("assignments", this.assignmentService.findAll());
        return "assignments";
    }

    @GetMapping("/removeAssignment/{id}")
    public String deleteAssignment(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Assignment assignment = this.assignmentService.find(id);
        if (assignment == null) {
            model.addAttribute("addMessage", "Invalid assignment Id:" + id);
            model.addAttribute("assignments", this.assignmentService.findAll());
            return "assignments";
        }
        this.assignmentService.delete(assignment);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("assignments", this.assignmentService.findAll());
        return "assignments";
    }

    @GetMapping("/editAssignment/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Assignment assignment = this.assignmentService.find(id);
        if (assignment == null) {
            model.addAttribute("addMessage", "Invalid assignment Id:" + id);
            model.addAttribute("assignments", this.assignmentService.findAll());
            return "assignments";
        }
        model.addAttribute("assignment", assignment);
        return "updateAssignment";
    }

   

}
