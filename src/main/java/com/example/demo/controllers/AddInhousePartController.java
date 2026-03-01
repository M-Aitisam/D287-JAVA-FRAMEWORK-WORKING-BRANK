package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
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
public class AddInhousePartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInhousePart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInhousePart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult,
            Model theModel) {
        theModel.addAttribute("inhousepart", part);
        if (theBindingResult.hasErrors()) {
            return "InhousePartForm";
        } else {
            // Requirement G: Enforce inventory between min and max
            if (part.getInv() < part.getMin() || part.getInv() > part.getMax()) {
                if (part.getInv() < part.getMin()) {
                    theBindingResult.rejectValue("inv", "inv.belowMin",
                            "Inventory is less than the minimum number of parts.");
                } else {
                    theBindingResult.rejectValue("inv", "inv.aboveMax", "Inventory is greater than the maximum.");
                }
                return "InhousePartForm";
            }

            InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
            repo.save(part);
            return "confirmationaddpart";
        }
    }
}
