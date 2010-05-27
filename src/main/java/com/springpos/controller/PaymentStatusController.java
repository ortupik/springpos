package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.PaymentStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.PaymentStatusService;

@Controller
public class PaymentStatusController {

    private PaymentStatusService paymentStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentStatusController.class);

    ArrayList<PaymentStatus> paymentStatusList = new ArrayList();

    @Autowired
    public void setPaymentStatusService(PaymentStatusService paymentStatusService) {
        this.paymentStatusService = paymentStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("paymentStatus/new")
    public String paymentStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("paymentStatus", new PaymentStatus());
        mainService.setInstitution(model);
        return "paymentStatus";
    }

    @GetMapping("/paymentStatuss")
    public ModelAndView showCategories(PaymentStatus paymentStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("paymentStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("paymentStatus", new PaymentStatus());
            mainService.setInstitution(mv);
            paymentStatusList.clear();
            paymentStatusList = (ArrayList<PaymentStatus>) this.paymentStatusService.findAll();
            mv.addObject("paymentStatuss", this.paymentStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "paymentStatus")
    public String save(@Valid PaymentStatus paymentStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("paymentStatus", new PaymentStatus());
            return "paymentStatus";
        }
        paymentStatusService.save(paymentStatus);
        model.addAttribute("paymentStatus", new PaymentStatus());
        model.addAttribute("addMessage", " PaymentStatus Added Successfull ");
        return "paymentStatus";

    }

    @RequestMapping("/updatePaymentStatus/{id}")
    public String updatePaymentStatus(@PathVariable("id") int id, @Valid PaymentStatus paymentStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("paymentStatus", paymentStatus);
        this.paymentStatusService.update(paymentStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("paymentStatuss", this.paymentStatusService.findAll());
        return "paymentStatuss";
    }

    @GetMapping("/removePaymentStatus/{id}")
    public String deletePaymentStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        PaymentStatus paymentStatus = this.paymentStatusService.find(id);
        if (paymentStatus == null) {
            model.addAttribute("addMessage", "Invalid paymentStatus Id:" + id);
            model.addAttribute("paymentStatuss", this.paymentStatusService.findAll());
            return "paymentStatuss";
        }
        this.paymentStatusService.delete(paymentStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("paymentStatuss", this.paymentStatusService.findAll());
        return "paymentStatuss";
    }

    @GetMapping("/editPaymentStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        PaymentStatus paymentStatus = this.paymentStatusService.find(id);
        if (paymentStatus == null) {
            model.addAttribute("addMessage", "Invalid paymentStatus Id:" + id);
            model.addAttribute("paymentStatuss", this.paymentStatusService.findAll());
            return "paymentStatuss";
        }
        model.addAttribute("paymentStatus", paymentStatus);
        return "updatePaymentStatus";
    }


}
