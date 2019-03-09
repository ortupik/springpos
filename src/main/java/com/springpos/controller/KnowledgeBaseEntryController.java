package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.KnowledgeBaseEntry;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.KnowledgeBaseEntryService;

@Controller
public class KnowledgeBaseEntryController {

    private KnowledgeBaseEntryService knowledgeBaseEntryService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBaseEntryController.class);

    ArrayList<KnowledgeBaseEntry> knowledgeBaseEntryList = new ArrayList();

    @Autowired
    public void setKnowledgeBaseEntryService(KnowledgeBaseEntryService knowledgeBaseEntryService) {
        this.knowledgeBaseEntryService = knowledgeBaseEntryService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("knowledgeBaseEntry/new")
    public String knowledgeBaseEntryPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("knowledgeBaseEntry", new KnowledgeBaseEntry());
        setInstitution(model);
        return "knowledgeBaseEntry";
    }

    @GetMapping("/knowledgeBaseEntrys")
    public ModelAndView showCategories(KnowledgeBaseEntry knowledgeBaseEntry) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("knowledgeBaseEntrys");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("knowledgeBaseEntry", new KnowledgeBaseEntry());
            setInstitution(mv);
            knowledgeBaseEntryList.clear();
            knowledgeBaseEntryList = (ArrayList<KnowledgeBaseEntry>) this.knowledgeBaseEntryService.findAll();
            mv.addObject("knowledgeBaseEntrys", this.knowledgeBaseEntryService.findAll());
        }
        return mv;
    }



    @PostMapping(value = "knowledgeBaseEntry")
    public String save(@Valid KnowledgeBaseEntry knowledgeBaseEntry, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("knowledgeBaseEntry", new KnowledgeBaseEntry());
            return "knowledgeBaseEntry";
        }
        knowledgeBaseEntryService.save(knowledgeBaseEntry);
        model.addAttribute("knowledgeBaseEntry", new KnowledgeBaseEntry());
        model.addAttribute("addMessage", " KnowledgeBaseEntry Added Successfull ");
        return "knowledgeBaseEntry";

    }

    @RequestMapping("/updateKnowledgeBaseEntry/{id}")
    public String updateKnowledgeBaseEntry(@PathVariable("id") int id, @Valid KnowledgeBaseEntry knowledgeBaseEntry,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("knowledgeBaseEntry", knowledgeBaseEntry);
        this.knowledgeBaseEntryService.update(knowledgeBaseEntry);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("knowledgeBaseEntrys", this.knowledgeBaseEntryService.findAll());
        return "knowledgeBaseEntrys";
    }

    @GetMapping("/removeKnowledgeBaseEntry/{id}")
    public String deleteKnowledgeBaseEntry(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseEntry knowledgeBaseEntry = this.knowledgeBaseEntryService.find(id);
        if (knowledgeBaseEntry == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseEntry Id:" + id);
            model.addAttribute("knowledgeBaseEntrys", this.knowledgeBaseEntryService.findAll());
            return "knowledgeBaseEntrys";
        }
        this.knowledgeBaseEntryService.delete(knowledgeBaseEntry);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("knowledgeBaseEntrys", this.knowledgeBaseEntryService.findAll());
        return "knowledgeBaseEntrys";
    }

    @GetMapping("/editKnowledgeBaseEntry/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseEntry knowledgeBaseEntry = this.knowledgeBaseEntryService.find(id);
        if (knowledgeBaseEntry == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseEntry Id:" + id);
            model.addAttribute("knowledgeBaseEntrys", this.knowledgeBaseEntryService.findAll());
            return "knowledgeBaseEntrys";
        }
        model.addAttribute("knowledgeBaseEntry", knowledgeBaseEntry);
        return "updateKnowledgeBaseEntry";
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
