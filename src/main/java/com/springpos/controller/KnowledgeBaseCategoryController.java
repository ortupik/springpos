package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.KnowledgeBaseCategory;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.KnowledgeBaseCategoryService;

@Controller
public class KnowledgeBaseCategoryController {

    private KnowledgeBaseCategoryService knowledgeBaseCategoryService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBaseCategoryController.class);

    ArrayList<KnowledgeBaseCategory> knowledgeBaseCategoryList = new ArrayList();

    @Autowired
    public void setKnowledgeBaseCategoryService(KnowledgeBaseCategoryService knowledgeBaseCategoryService) {
        this.knowledgeBaseCategoryService = knowledgeBaseCategoryService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("knowledgeBaseCategory/new")
    public String knowledgeBaseCategoryPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("knowledgeBaseCategory", new KnowledgeBaseCategory());
        setInstitution(model);
        return "knowledgeBaseCategory";
    }

    @GetMapping("/knowledgeBaseCategorys")
    public ModelAndView showCategories(KnowledgeBaseCategory knowledgeBaseCategory) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("knowledgeBaseCategorys");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("knowledgeBaseCategory", new KnowledgeBaseCategory());
            setInstitution(mv);
            knowledgeBaseCategoryList.clear();
            knowledgeBaseCategoryList = (ArrayList<KnowledgeBaseCategory>) this.knowledgeBaseCategoryService.findAll();
            mv.addObject("knowledgeBaseCategorys", this.knowledgeBaseCategoryService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "knowledgeBaseCategory")
    public String save(@Valid KnowledgeBaseCategory knowledgeBaseCategory, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("knowledgeBaseCategory", new KnowledgeBaseCategory());
            return "knowledgeBaseCategory";
        }
        knowledgeBaseCategoryService.save(knowledgeBaseCategory);
        model.addAttribute("knowledgeBaseCategory", new KnowledgeBaseCategory());
        model.addAttribute("addMessage", " KnowledgeBaseCategory Added Successfull ");
        return "knowledgeBaseCategory";

    }

    @RequestMapping("/updateKnowledgeBaseCategory/{id}")
    public String updateKnowledgeBaseCategory(@PathVariable("id") int id, @Valid KnowledgeBaseCategory knowledgeBaseCategory,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("knowledgeBaseCategory", knowledgeBaseCategory);
        this.knowledgeBaseCategoryService.update(knowledgeBaseCategory);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("knowledgeBaseCategorys", this.knowledgeBaseCategoryService.findAll());
        return "knowledgeBaseCategorys";
    }

    @GetMapping("/removeKnowledgeBaseCategory/{id}")
    public String deleteKnowledgeBaseCategory(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseCategory knowledgeBaseCategory = this.knowledgeBaseCategoryService.find(id);
        if (knowledgeBaseCategory == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseCategory Id:" + id);
            model.addAttribute("knowledgeBaseCategorys", this.knowledgeBaseCategoryService.findAll());
            return "knowledgeBaseCategorys";
        }
        this.knowledgeBaseCategoryService.delete(knowledgeBaseCategory);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("knowledgeBaseCategorys", this.knowledgeBaseCategoryService.findAll());
        return "knowledgeBaseCategorys";
    }

    @GetMapping("/editKnowledgeBaseCategory/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseCategory knowledgeBaseCategory = this.knowledgeBaseCategoryService.find(id);
        if (knowledgeBaseCategory == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseCategory Id:" + id);
            model.addAttribute("knowledgeBaseCategorys", this.knowledgeBaseCategoryService.findAll());
            return "knowledgeBaseCategorys";
        }
        model.addAttribute("knowledgeBaseCategory", knowledgeBaseCategory);
        return "updateKnowledgeBaseCategory";
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
