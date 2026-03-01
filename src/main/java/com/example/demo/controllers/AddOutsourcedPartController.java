package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutsourcedPart")
    public String showFormAddOutsourcedPart(Model theModel) {
        OutsourcedPart outsourcedpart = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", outsourcedpart);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutsourcedPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part,
            BindingResult theBindingResult, Model theModel) {
        theModel.addAttribute("outsourcedpart", part);
        if (theBindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        } else {
            // Requirement G: Enforce inventory between min and max
            if (part.getInv() < part.getMin() || part.getInv() > part.getMax()) {
                if (part.getInv() < part.getMin()) {
                    theBindingResult.rejectValue("inv", "inv.belowMin",
                            "Inventory is less than the minimum number of parts.");
                } else {
                    theBindingResult.rejectValue("inv", "inv.aboveMax", "Inventory is greater than the maximum.");
                }
                return "OutsourcedPartForm";
            }

            OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
            repo.save(part);
            return "confirmationaddpart";
        }
    }
}
