package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContractorStatus;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContractorStatusService;

@Controller
public class ContractorStatusController {

    private ContractorStatusService contractorStatusService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorStatusController.class);

    ArrayList<ContractorStatus> contractorStatusList = new ArrayList();

    @Autowired
    public void setContractorStatusService(ContractorStatusService contractorStatusService) {
        this.contractorStatusService = contractorStatusService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("contractorStatus/new")
    public String contractorStatusPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("contractorStatus", new ContractorStatus());
        mainService.setInstitution(model);
        return "contractorStatus";
    }

    @GetMapping("/contractorStatuss")
    public ModelAndView showCategories(ContractorStatus contractorStatus) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractorStatuss");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("contractorStatus", new ContractorStatus());
            mainService.setInstitution(mv);
            contractorStatusList.clear();
            contractorStatusList = (ArrayList<ContractorStatus>) this.contractorStatusService.findAll();
            mv.addObject("contractorStatuss", this.contractorStatusService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contractorStatus")
    public String save(@Valid ContractorStatus contractorStatus, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("contractorStatus", new ContractorStatus());
            return "contractorStatus";
        }
        contractorStatusService.save(contractorStatus);
        model.addAttribute("contractorStatus", new ContractorStatus());
        model.addAttribute("addMessage", " ContractorStatus Added Successfull ");
        return "contractorStatus";

    }

    @RequestMapping("/updateContractorStatus/{id}")
    public String updateContractorStatus(@PathVariable("id") int id, @Valid ContractorStatus contractorStatus,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("contractorStatus", contractorStatus);
        this.contractorStatusService.update(contractorStatus);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractorStatuss", this.contractorStatusService.findAll());
        return "contractorStatuss";
    }

    @GetMapping("/removeContractorStatus/{id}")
    public String deleteContractorStatus(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContractorStatus contractorStatus = this.contractorStatusService.find(id);
        if (contractorStatus == null) {
            model.addAttribute("addMessage", "Invalid contractorStatus Id:" + id);
            model.addAttribute("contractorStatuss", this.contractorStatusService.findAll());
            return "contractorStatuss";
        }
        this.contractorStatusService.delete(contractorStatus);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contractorStatuss", this.contractorStatusService.findAll());
        return "contractorStatuss";
    }

    @GetMapping("/editContractorStatus/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContractorStatus contractorStatus = this.contractorStatusService.find(id);
        if (contractorStatus == null) {
            model.addAttribute("addMessage", "Invalid contractorStatus Id:" + id);
            model.addAttribute("contractorStatuss", this.contractorStatusService.findAll());
            return "contractorStatuss";
        }
        model.addAttribute("contractorStatus", contractorStatus);
        return "updateContractorStatus";
    }

}
