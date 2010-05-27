package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.PhotoType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.PhotoTypeService;

@Controller
public class PhotoTypeController {

    private PhotoTypeService photoTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoTypeController.class);

    ArrayList<PhotoType> photoTypeList = new ArrayList();

    @Autowired
    public void setPhotoTypeService(PhotoTypeService photoTypeService) {
        this.photoTypeService = photoTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("photoType/new")
    public String photoTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("photoType", new PhotoType());
        mainService.setInstitution(model);
        return "photoType";
    }

    @GetMapping("/photoTypes")
    public ModelAndView showCategories(PhotoType photoType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("photoTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("photoType", new PhotoType());
            mainService.setInstitution(mv);
            photoTypeList.clear();
            photoTypeList = (ArrayList<PhotoType>) this.photoTypeService.findAll();
            mv.addObject("photoTypes", this.photoTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "photoType")
    public String save(@Valid PhotoType photoType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("photoType", new PhotoType());
            return "photoType";
        }
        photoTypeService.save(photoType);
        model.addAttribute("photoType", new PhotoType());
        model.addAttribute("addMessage", " PhotoType Added Successfull ");
        return "photoType";

    }

    @RequestMapping("/updatePhotoType/{id}")
    public String updatePhotoType(@PathVariable("id") int id, @Valid PhotoType photoType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("photoType", photoType);
        this.photoTypeService.update(photoType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("photoTypes", this.photoTypeService.findAll());
        return "photoTypes";
    }

    @GetMapping("/removePhotoType/{id}")
    public String deletePhotoType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        PhotoType photoType = this.photoTypeService.find(id);
        if (photoType == null) {
            model.addAttribute("addMessage", "Invalid photoType Id:" + id);
            model.addAttribute("photoTypes", this.photoTypeService.findAll());
            return "photoTypes";
        }
        this.photoTypeService.delete(photoType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("photoTypes", this.photoTypeService.findAll());
        return "photoTypes";
    }

    @GetMapping("/editPhotoType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        PhotoType photoType = this.photoTypeService.find(id);
        if (photoType == null) {
            model.addAttribute("addMessage", "Invalid photoType Id:" + id);
            model.addAttribute("photoTypes", this.photoTypeService.findAll());
            return "photoTypes";
        }
        model.addAttribute("photoType", photoType);
        return "updatePhotoType";
    }


}
