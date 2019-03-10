package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.PaymentType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.PaymentTypeService;

@Controller
public class PaymentTypeController {

    private PaymentTypeService paymentTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentTypeController.class);

    ArrayList<PaymentType> paymentTypeList = new ArrayList();

    @Autowired
    public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("paymentType/new")
    public String paymentTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("paymentType", new PaymentType());
        setInstitution(model);
        return "paymentType";
    }

    @GetMapping("/paymentTypes")
    public ModelAndView showCategories(PaymentType paymentType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("paymentTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("paymentType", new PaymentType());
            setInstitution(mv);
            paymentTypeList.clear();
            paymentTypeList = (ArrayList<PaymentType>) this.paymentTypeService.findAll();
            mv.addObject("paymentTypes", this.paymentTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "paymentType")
    public String save(@Valid PaymentType paymentType, BindingResult result, Model model) {
        setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("paymentType", new PaymentType());
            return "paymentType";
        }
        paymentTypeService.save(paymentType);
        model.addAttribute("paymentType", new PaymentType());
        model.addAttribute("addMessage", " PaymentType Added Successfull ");
        return "paymentType";

    }

    @RequestMapping("/updatePaymentType/{id}")
    public String updatePaymentType(@PathVariable("id") int id, @Valid PaymentType paymentType,
            BindingResult result, Model model) {
        setInstitution(model);
        model.addAttribute("paymentType", paymentType);
        this.paymentTypeService.update(paymentType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("paymentTypes", this.paymentTypeService.findAll());
        return "paymentTypes";
    }

    @GetMapping("/removePaymentType/{id}")
    public String deletePaymentType(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        PaymentType paymentType = this.paymentTypeService.find(id);
        if (paymentType == null) {
            model.addAttribute("addMessage", "Invalid paymentType Id:" + id);
            model.addAttribute("paymentTypes", this.paymentTypeService.findAll());
            return "paymentTypes";
        }
        this.paymentTypeService.delete(paymentType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("paymentTypes", this.paymentTypeService.findAll());
        return "paymentTypes";
    }

    @GetMapping("/editPaymentType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        setInstitution(model);
        PaymentType paymentType = this.paymentTypeService.find(id);
        if (paymentType == null) {
            model.addAttribute("addMessage", "Invalid paymentType Id:" + id);
            model.addAttribute("paymentTypes", this.paymentTypeService.findAll());
            return "paymentTypes";
        }
        model.addAttribute("paymentType", paymentType);
        return "updatePaymentType";
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
