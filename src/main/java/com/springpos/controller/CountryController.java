package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Country;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.CountryService;

@Controller
public class CountryController {

    private CountryService countryService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    ArrayList<Country> countryList = new ArrayList();

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("country/new")
    public String countryPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("country", new Country());
        setInstitution(model);
        return "country";
    }

    @GetMapping("/countrys")
    public ModelAndView showCategories(Country country) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("countrys");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("country", new Country());
            setInstitution(mv);
            countryList.clear();
            countryList = (ArrayList<Country>) this.countryService.findAll();
            mv.addObject("countrys", this.countryService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "country")
    public String save(@Valid Country country, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("country", new Country());
            return "country";
        }
        countryService.save(country);
        model.addAttribute("country", new Country());
        model.addAttribute("addMessage", " Country Added Successfull ");
        return "country";

    }

    @RequestMapping("/updateCountry/{id}")
    public String updateCountry(@PathVariable("id") int id, @Valid Country country,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("country", country);
        this.countryService.update(country);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("countrys", this.countryService.findAll());
        return "countrys";
    }

    @GetMapping("/removeCountry/{id}")
    public String deleteCountry(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Country country = this.countryService.find(id);
        if (country == null) {
            model.addAttribute("addMessage", "Invalid country Id:" + id);
            model.addAttribute("countrys", this.countryService.findAll());
            return "countrys";
        }
        this.countryService.delete(country);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("countrys", this.countryService.findAll());
        return "countrys";
    }

    @GetMapping("/editCountry/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        Country country = this.countryService.find(id);
        if (country == null) {
            model.addAttribute("addMessage", "Invalid country Id:" + id);
            model.addAttribute("countrys", this.countryService.findAll());
            return "countrys";
        }
        model.addAttribute("country", country);
        return "updateCountry";
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
