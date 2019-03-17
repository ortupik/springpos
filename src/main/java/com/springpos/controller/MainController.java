package com.springpos.controller;

import com.springpos.bean.User;
import com.springpos.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private MainService mainService;


    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", new User());
        mainService.setInstitution(mv);
        return mv;
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return mainService.getLoggedOut();
    }

}
