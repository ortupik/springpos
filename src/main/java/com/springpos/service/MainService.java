package com.springpos.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface MainService {

    public String institutionName();

    public String institutionMotto();

    public ModelAndView setLoggedIn(String loggedIn);

    public String getLoggedIn();

    public String getLoggedOut();

    public int getAccessPermition();

    public void setAccessPermition();

    public Model sessionLoggedIn(Model model);

    public String encryptStuff(String crap);

}
