package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceOrderPhoto;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceOrderPhotoService;

@Controller
public class ServiceOrderPhotoController {

    private ServiceOrderPhotoService serviceOrderPhotoService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOrderPhotoController.class);

    ArrayList<ServiceOrderPhoto> serviceOrderPhotoList = new ArrayList();

    @Autowired
    public void setServiceOrderPhotoService(ServiceOrderPhotoService serviceOrderPhotoService) {
        this.serviceOrderPhotoService = serviceOrderPhotoService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceOrderPhoto/new")
    public String serviceOrderPhotoPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("serviceOrderPhoto", new ServiceOrderPhoto());
        mainService.setInstitution(model);
        return "serviceOrderPhoto";
    }

    @GetMapping("/serviceOrderPhotos")
    public ModelAndView showCategories(ServiceOrderPhoto serviceOrderPhoto) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceOrderPhotos");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("serviceOrderPhoto", new ServiceOrderPhoto());
            mainService.setInstitution(mv);
            serviceOrderPhotoList.clear();
            serviceOrderPhotoList = (ArrayList<ServiceOrderPhoto>) this.serviceOrderPhotoService.findAll();
            mv.addObject("serviceOrderPhotos", this.serviceOrderPhotoService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceOrderPhoto")
    public String save(@Valid ServiceOrderPhoto serviceOrderPhoto, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceOrderPhoto", new ServiceOrderPhoto());
            return "serviceOrderPhoto";
        }
        serviceOrderPhotoService.save(serviceOrderPhoto);
        model.addAttribute("serviceOrderPhoto", new ServiceOrderPhoto());
        model.addAttribute("addMessage", " ServiceOrderPhoto Added Successfull ");
        return "serviceOrderPhoto";

    }

    @RequestMapping("/updateServiceOrderPhoto/{id}")
    public String updateServiceOrderPhoto(@PathVariable("id") int id, @Valid ServiceOrderPhoto serviceOrderPhoto,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceOrderPhoto", serviceOrderPhoto);
        this.serviceOrderPhotoService.update(serviceOrderPhoto);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceOrderPhotos", this.serviceOrderPhotoService.findAll());
        return "serviceOrderPhotos";
    }

    @GetMapping("/removeServiceOrderPhoto/{id}")
    public String deleteServiceOrderPhoto(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderPhoto serviceOrderPhoto = this.serviceOrderPhotoService.find(id);
        if (serviceOrderPhoto == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderPhoto Id:" + id);
            model.addAttribute("serviceOrderPhotos", this.serviceOrderPhotoService.findAll());
            return "serviceOrderPhotos";
        }
        this.serviceOrderPhotoService.delete(serviceOrderPhoto);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceOrderPhotos", this.serviceOrderPhotoService.findAll());
        return "serviceOrderPhotos";
    }

    @GetMapping("/editServiceOrderPhoto/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrderPhoto serviceOrderPhoto = this.serviceOrderPhotoService.find(id);
        if (serviceOrderPhoto == null) {
            model.addAttribute("addMessage", "Invalid serviceOrderPhoto Id:" + id);
            model.addAttribute("serviceOrderPhotos", this.serviceOrderPhotoService.findAll());
            return "serviceOrderPhotos";
        }
        model.addAttribute("serviceOrderPhoto", serviceOrderPhoto);
        return "updateServiceOrderPhoto";
    }


}
