package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.Payment;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.PaymentService;

@Controller
public class PaymentController {

    private PaymentService paymentService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    ArrayList<Payment> paymentList = new ArrayList();

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("payment/new")
    public String paymentPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("payment", new Payment());
        mainService.setInstitution(model);
        return "payment";
    }

    @GetMapping("/payments")
    public ModelAndView showCategories(Payment payment) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("payments");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("payment", new Payment());
            mainService.setInstitution(mv);
            paymentList.clear();
            paymentList = (ArrayList<Payment>) this.paymentService.findAll();
            mv.addObject("payments", this.paymentService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "payment")
    public String save(@Valid Payment payment, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("payment", new Payment());
            return "payment";
        }
        paymentService.save(payment);
        model.addAttribute("payment", new Payment());
        model.addAttribute("addMessage", " Payment Added Successfull ");
        return "payment";

    }

    @RequestMapping("/updatePayment/{id}")
    public String updatePayment(@PathVariable("id") int id, @Valid Payment payment,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("payment", payment);
        this.paymentService.update(payment);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("payments", this.paymentService.findAll());
        return "payments";
    }

    @GetMapping("/removePayment/{id}")
    public String deletePayment(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Payment payment = this.paymentService.find(id);
        if (payment == null) {
            model.addAttribute("addMessage", "Invalid payment Id:" + id);
            model.addAttribute("payments", this.paymentService.findAll());
            return "payments";
        }
        this.paymentService.delete(payment);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("payments", this.paymentService.findAll());
        return "payments";
    }

    @GetMapping("/editPayment/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        Payment payment = this.paymentService.find(id);
        if (payment == null) {
            model.addAttribute("addMessage", "Invalid payment Id:" + id);
            model.addAttribute("payments", this.paymentService.findAll());
            return "payments";
        }
        model.addAttribute("payment", payment);
        return "updatePayment";
    }

}
