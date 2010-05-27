package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ContractorType;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ContractorTypeService;

@Controller
public class ContractorTypeController {

    private ContractorTypeService contractorTypeService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorTypeController.class);

    ArrayList<ContractorType> contractorTypeList = new ArrayList();

    @Autowired
    public void setContractorTypeService(ContractorTypeService contractorTypeService) {
        this.contractorTypeService = contractorTypeService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("contractorType/new")
    public String contractorTypePage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "index";
        }
        model.addAttribute("contractorType", new ContractorType());
        mainService.setInstitution(model);
        return "contractorType";
    }

    @GetMapping("/contractorTypes")
    public ModelAndView showCategories(ContractorType contractorType) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contractorTypes");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("index");
        } else {
            mv.addObject("contractorType", new ContractorType());
            mainService.setInstitution(mv);
            contractorTypeList.clear();
            contractorTypeList = (ArrayList<ContractorType>) this.contractorTypeService.findAll();
            mv.addObject("contractorTypes", this.contractorTypeService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "contractorType")
    public String save(@Valid ContractorType contractorType, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("contractorType", new ContractorType());
            return "contractorType";
        }
        contractorTypeService.save(contractorType);
        model.addAttribute("contractorType", new ContractorType());
        model.addAttribute("addMessage", " ContractorType Added Successfull ");
        return "contractorType";

    }

    @RequestMapping("/updateContractorType/{id}")
    public String updateContractorType(@PathVariable("id") int id, @Valid ContractorType contractorType,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("contractorType", contractorType);
        this.contractorTypeService.update(contractorType);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("contractorTypes", this.contractorTypeService.findAll());
        return "contractorTypes";
    }

    @GetMapping("/removeContractorType/{id}")
    public String deleteContractorType(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContractorType contractorType = this.contractorTypeService.find(id);
        if (contractorType == null) {
            model.addAttribute("addMessage", "Invalid contractorType Id:" + id);
            model.addAttribute("contractorTypes", this.contractorTypeService.findAll());
            return "contractorTypes";
        }
        this.contractorTypeService.delete(contractorType);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("contractorTypes", this.contractorTypeService.findAll());
        return "contractorTypes";
    }

    @GetMapping("/editContractorType/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ContractorType contractorType = this.contractorTypeService.find(id);
        if (contractorType == null) {
            model.addAttribute("addMessage", "Invalid contractorType Id:" + id);
            model.addAttribute("contractorTypes", this.contractorTypeService.findAll());
            return "contractorTypes";
        }
        model.addAttribute("contractorType", contractorType);
        return "updateContractorType";
    }


}
