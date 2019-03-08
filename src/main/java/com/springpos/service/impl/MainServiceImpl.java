package com.springpos.service.impl;

import com.springpos.bean.User;
import com.springpos.bean.Sessions;
import org.springframework.stereotype.Service;
import com.springpos.service.MainService;
import com.springpos.service.SessionService;
import com.springpos.service.UserService;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MainServiceImpl implements MainService {

    private String session;

    ModelAndView mv;

    public String loggedIn = "nospaniol@gmail.com";

    public String loggedOut = "";

    private UserService userService;

    private SessionService sessionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String institutionName() {
        return "HW";
    }

    @Override
    public String institutionMotto() {
        return "";
    }

     String getUser() {
        return loggedIn;
    }

    @Override
    public ModelAndView setLoggedIn(String loggedIn) {
        loggedOut = "";
        Random rand = new Random();
        int rand_sess = rand.nextInt(1000);
        User user = userService.findByEmail(loggedIn);
        Sessions sess = new Sessions();
        sess.setSessionName(user.getEmail() + " | " + String.valueOf(rand_sess));
        sess.setUserName(loggedIn);
        Sessions newSess = sessionService.save(sess);
        session = newSess.getSessionName();
        session = newSess.getUserName();
        String sessName = newSess.getSessionName();
        mv = new ModelAndView();
        if (user.getRoles().equals("Admin")) {
            mv.setViewName("adminPage");
        }
        if (user.getRoles().equals("Admin")) {
            mv.setViewName("userPage");
        }
        this.loggedIn = loggedIn;
        mv.addObject("sessionLoggedIn", sessName);
        mv.addObject("loggedIn", this.loggedIn);
        return mv;
    }

    @Override
    public String getLoggedIn() {
        return loggedIn;
    }

    @Override
    public Model sessionLoggedIn(Model model) {
        Sessions newSess = sessionService.findBySessionLatest();
        model.addAttribute("loggedIn", this.loggedIn);
        model.addAttribute("sessionLoggedIn", newSess.getSessionName());
        return model;
    }

    @Override
    public String getLoggedOut() {
        if (getLoggedIn() == null) {
            return "redirect:/";
        }  
        session = null;
        this.loggedIn = null;
        loggedOut="Logged Out";
        return "redirect:/";
    }

    @Override
    public String getAccessPermition() {
        return loggedOut;
    }

}
