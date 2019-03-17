package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ServiceOrder;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ServiceOrderService;

@Controller
public class ServiceOrderController {

    private ServiceOrderService serviceOrderService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceOrderController.class);

    ArrayList<ServiceOrder> serviceOrderList = new ArrayList();

    @Autowired
    public void setServiceOrderService(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("serviceOrder/new")
    public String serviceOrderPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("serviceOrder", new ServiceOrder());
        mainService.setInstitution(model);
        return "serviceOrder";
    }

    @GetMapping("/serviceOrders")
    public ModelAndView showCategories(ServiceOrder serviceOrder) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("serviceOrders");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("serviceOrder", new ServiceOrder());
            mainService.setInstitution(mv);
            serviceOrderList.clear();
            serviceOrderList = (ArrayList<ServiceOrder>) this.serviceOrderService.findAll();
            mv.addObject("serviceOrders", this.serviceOrderService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "serviceOrder")
    public String save(@Valid ServiceOrder serviceOrder, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("serviceOrder", new ServiceOrder());
            return "serviceOrder";
        }
        serviceOrderService.save(serviceOrder);
        model.addAttribute("serviceOrder", new ServiceOrder());
        model.addAttribute("addMessage", " ServiceOrder Added Successfull ");
        return "serviceOrder";

    }

    @RequestMapping("/updateServiceOrder/{id}")
    public String updateServiceOrder(@PathVariable("id") int id, @Valid ServiceOrder serviceOrder,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("serviceOrder", serviceOrder);
        this.serviceOrderService.update(serviceOrder);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("serviceOrders", this.serviceOrderService.findAll());
        return "serviceOrders";
    }

    @GetMapping("/removeServiceOrder/{id}")
    public String deleteServiceOrder(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrder serviceOrder = this.serviceOrderService.find(id);
        if (serviceOrder == null) {
            model.addAttribute("addMessage", "Invalid serviceOrder Id:" + id);
            model.addAttribute("serviceOrders", this.serviceOrderService.findAll());
            return "serviceOrders";
        }
        this.serviceOrderService.delete(serviceOrder);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("serviceOrders", this.serviceOrderService.findAll());
        return "serviceOrders";
    }

    @GetMapping("/editServiceOrder/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ServiceOrder serviceOrder = this.serviceOrderService.find(id);
        if (serviceOrder == null) {
            model.addAttribute("addMessage", "Invalid serviceOrder Id:" + id);
            model.addAttribute("serviceOrders", this.serviceOrderService.findAll());
            return "serviceOrders";
        }
        model.addAttribute("serviceOrder", serviceOrder);
        return "updateServiceOrder";
    }

}
