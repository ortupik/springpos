package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.State;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.StateService;

@Controller
public class StateController {

    private StateService stateService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class);

    ArrayList<State> stateList = new ArrayList();

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("state/new")
    public String statePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("state", new State());
        mainService.setInstitution(model);
        return "state";
    }

    @GetMapping("/states")
    public ModelAndView showCategories(State state) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("states");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("state", new State());
            mainService.setInstitution(mv);
            stateList.clear();
            stateList = (ArrayList<State>) this.stateService.findAll();
            mv.addObject("states", this.stateService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "state")
    public String save(@Valid State state, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("state", new State());
            return "state";
        }
        stateService.save(state);
        model.addAttribute("state", new State());
        model.addAttribute("addMessage", " State Added Successfull ");
        return "state";

    }

    @RequestMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") int id, @Valid State state,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("state", state);
        this.stateService.update(state);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("states", this.stateService.findAll());
        return "states";
    }

    @GetMapping("/removeState/{id}")
    public String deleteState(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        State state = this.stateService.find(id);
        if (state == null) {
            model.addAttribute("addMessage", "Invalid state Id:" + id);
            model.addAttribute("states", this.stateService.findAll());
            return "states";
        }
        this.stateService.delete(state);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("states", this.stateService.findAll());
        return "states";
    }

    @GetMapping("/editState/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        State state = this.stateService.find(id);
        if (state == null) {
            model.addAttribute("addMessage", "Invalid state Id:" + id);
            model.addAttribute("states", this.stateService.findAll());
            return "states";
        }
        model.addAttribute("state", state);
        return "updateState";
    }


}
