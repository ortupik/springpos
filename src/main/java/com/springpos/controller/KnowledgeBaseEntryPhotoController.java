package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.KnowledgeBaseEntryPhoto;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.KnowledgeBaseEntryPhotoService;

@Controller
public class KnowledgeBaseEntryPhotoController {

    private KnowledgeBaseEntryPhotoService knowledgeBaseEntryPhotoService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBaseEntryPhotoController.class);

    ArrayList<KnowledgeBaseEntryPhoto> knowledgeBaseEntryPhotoList = new ArrayList();

    @Autowired
    public void setKnowledgeBaseEntryPhotoService(KnowledgeBaseEntryPhotoService knowledgeBaseEntryPhotoService) {
        this.knowledgeBaseEntryPhotoService = knowledgeBaseEntryPhotoService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("knowledgeBaseEntryPhoto/new")
    public String knowledgeBaseEntryPhotoPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("knowledgeBaseEntryPhoto", new KnowledgeBaseEntryPhoto());
        setInstitution(model);
        return "knowledgeBaseEntryPhoto";
    }

    @GetMapping("/knowledgeBaseEntryPhotos")
    public ModelAndView showCategories(KnowledgeBaseEntryPhoto knowledgeBaseEntryPhoto) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("knowledgeBaseEntryPhotos");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("knowledgeBaseEntryPhoto", new KnowledgeBaseEntryPhoto());
            setInstitution(mv);
            knowledgeBaseEntryPhotoList.clear();
            knowledgeBaseEntryPhotoList = (ArrayList<KnowledgeBaseEntryPhoto>) this.knowledgeBaseEntryPhotoService.findAll();
            mv.addObject("knowledgeBaseEntryPhotos", this.knowledgeBaseEntryPhotoService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "knowledgeBaseEntryPhoto")
    public String save(@Valid KnowledgeBaseEntryPhoto knowledgeBaseEntryPhoto, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("knowledgeBaseEntryPhoto", new KnowledgeBaseEntryPhoto());
            return "knowledgeBaseEntryPhoto";
        }
        knowledgeBaseEntryPhotoService.save(knowledgeBaseEntryPhoto);
        model.addAttribute("knowledgeBaseEntryPhoto", new KnowledgeBaseEntryPhoto());
        model.addAttribute("addMessage", " KnowledgeBaseEntryPhoto Added Successfull ");
        return "knowledgeBaseEntryPhoto";

    }

    @RequestMapping("/updateKnowledgeBaseEntryPhoto/{id}")
    public String updateKnowledgeBaseEntryPhoto(@PathVariable("id") int id, @Valid KnowledgeBaseEntryPhoto knowledgeBaseEntryPhoto,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("knowledgeBaseEntryPhoto", knowledgeBaseEntryPhoto);
        this.knowledgeBaseEntryPhotoService.update(knowledgeBaseEntryPhoto);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("knowledgeBaseEntryPhotos", this.knowledgeBaseEntryPhotoService.findAll());
        return "knowledgeBaseEntryPhotos";
    }

    @GetMapping("/removeKnowledgeBaseEntryPhoto/{id}")
    public String deleteKnowledgeBaseEntryPhoto(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseEntryPhoto knowledgeBaseEntryPhoto = this.knowledgeBaseEntryPhotoService.find(id);
        if (knowledgeBaseEntryPhoto == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseEntryPhoto Id:" + id);
            model.addAttribute("knowledgeBaseEntryPhotos", this.knowledgeBaseEntryPhotoService.findAll());
            return "knowledgeBaseEntryPhotos";
        }
        this.knowledgeBaseEntryPhotoService.delete(knowledgeBaseEntryPhoto);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("knowledgeBaseEntryPhotos", this.knowledgeBaseEntryPhotoService.findAll());
        return "knowledgeBaseEntryPhotos";
    }

    @GetMapping("/editKnowledgeBaseEntryPhoto/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        KnowledgeBaseEntryPhoto knowledgeBaseEntryPhoto = this.knowledgeBaseEntryPhotoService.find(id);
        if (knowledgeBaseEntryPhoto == null) {
            model.addAttribute("addMessage", "Invalid knowledgeBaseEntryPhoto Id:" + id);
            model.addAttribute("knowledgeBaseEntryPhotos", this.knowledgeBaseEntryPhotoService.findAll());
            return "knowledgeBaseEntryPhotos";
        }
        model.addAttribute("knowledgeBaseEntryPhoto", knowledgeBaseEntryPhoto);
        return "updateKnowledgeBaseEntryPhoto";
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
