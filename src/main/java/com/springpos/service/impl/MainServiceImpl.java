package com.springpos.service.impl;

import com.springpos.bean.User;
import com.springpos.bean.Sessions;
import org.springframework.stereotype.Service;
import com.springpos.service.MainService;
import com.springpos.service.SessionService;
import com.springpos.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MainServiceImpl implements MainService {

    private String session;

    ModelAndView mv;

    String staticUser = "admin@gmail.com";

    String staticPass = "1234";

    private String loggedIn = "s";

    private int loggedOut = 0;

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
        return "Computer Xperts";
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
        setLogout();
        Random rand = new Random();
        int rand_sess = rand.nextInt(1000);
        User usr = userService.findByEmail(staticUser);
        if (usr == null) {
            usr = new User();
            usr.setEmail(session);
            usr.setPassword(encryptStuff(staticPass));
            userService.save(usr);
        }
        User user = userService.findByEmail(loggedIn);
        Sessions sess = new Sessions();
        sess.setSessionName(user.getEmail() + " | " + String.valueOf(rand_sess));
        sess.setUserName(loggedIn);
        Sessions newSess = sessionService.save(sess);
        session = newSess.getSessionName() + newSess.getUserName();
        String sessName = newSess.getSessionName();
        mv = new ModelAndView();
        setLogin(session, loggedIn, rand_sess);
        mv.setViewName("adminPage");
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

        return "redirect:/";
    }

    @Override
    public int getAccessPermition() {
        return loggedOut;
    }

    @Override
    public void setAccessPermition() {
        Random rand = new Random();
        loggedOut = rand.nextInt(1000);
    }

    @Override
    public String encryptStuff(String crap) {
        try {
            byte[] input = crap.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            byte[] cipherText = cipher.doFinal(input);
            String encodedString = Base64.getEncoder().encodeToString(cipherText);
            return encodedString;
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(MainServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    void setLogout() {
        session = null;
        this.loggedIn = null;
        loggedOut = 0;
        mv = null;
    }

    void setLogin(String sess, String loggedIn, int loggedOut) {
        this.session = sess;
        this.loggedIn = loggedIn;
        this.loggedOut = loggedOut;
    }
}
