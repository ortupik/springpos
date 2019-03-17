package com.springpos.controller;

import com.springpos.bean.User;
import com.springpos.service.MainService;
import com.springpos.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nospaniol
 */
@Controller
public class UserController {

    private UserService userService;
    private MainService mainService;

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setUserService(UserService ps) {
        this.userService = ps;
    }

    @RequestMapping("loginPage")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        mainService.setInstitution(model);
        return "loginPage";
    }

    @RequestMapping("register")
    public String registerPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("user", new User());
        mainService.setInstitution(model);
        return "user";
    }

    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public String adminSite() {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        return "adminPage";
    }

    @RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
    public String userPage() {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        return "userPage";
    }

    @PostMapping(value = "login")
    public ModelAndView login(@Valid User p, BindingResult result, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");
        mv.addObject("user",new User());  
        mainService.checkStaticOrder();
        if (userService.authenticate(p.getEmail(), p.getPassword())) {
            mv = mainService.setLoggedIn(p.getEmail());
            return mv;
        }
        return mv;
    }

    @PostMapping(value = "user")
    public String save(@Valid User user, BindingResult result, Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());
            model.addAttribute("user", new User());
            return "user";
        }
        User users = userService.findByEmail(user.getEmail());
        if (users == null) {
            userService.save(user);
            model.addAttribute("addMessage", " User Registered Successfull !");
        } else {
            model.addAttribute("addMessage", " User is already registered !");
        }
        model.addAttribute("user", new User());
        return "user";

    }

    @GetMapping("/users")
    public ModelAndView showCategories(User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("users");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        }
        mv.addObject("user", new User());
        mainService.setInstitution(mv);
        mv.addObject("users", this.userService.findAll());
        return mv;
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user,
            BindingResult result, Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        mainService.setInstitution(model);
        model.addAttribute("user", user);
        this.userService.update(user);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @GetMapping("/removeUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        User user = this.userService.find(id);
        if (user == null) {
            model.addAttribute("addMessage", "Invalid user Id:" + id);
            model.addAttribute("users", this.userService.findAll());
            return "users";
        }
        this.userService.delete(user);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        User user = this.userService.find(id);
        if (user == null) {
            model.addAttribute("addMessage", "Invalid user Id:" + id);
            model.addAttribute("users", this.userService.findAll());
            return "users";
        }
        model.addAttribute("user", user);
        return "updateUser";
    }

}
