package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AddPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") int theId, Model theModel) {
        PartService repo = context.getBean(PartServiceImpl.class);
        Part thePart = repo.findById(theId);
        if (thePart instanceof InhousePart) {
            InhousePart inhousePart = (InhousePart) thePart;
            theModel.addAttribute("inhousepart", inhousePart);
            return "InhousePartForm";
        } else {
            OutsourcedPart outsourcedPart = (OutsourcedPart) thePart;
            theModel.addAttribute("outsourcedpart", outsourcedPart);
            return "OutsourcedPartForm";
        }
    }

    @GetMapping("/deletepart")
    public String deletePart(@RequestParam("partID") int theId, Model theModel) {
        PartService repo = context.getBean(PartServiceImpl.class);
        Part part = repo.findById(theId);
        if (part.getProducts().isEmpty()) {
            repo.deleteById(theId);
            return "confirmationdeletepart";
        } else {
            return "negativeerror";
        }
    }
}
