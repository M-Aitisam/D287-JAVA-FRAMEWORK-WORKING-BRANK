package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddProductController {
    @Autowired
    private ApplicationContext context;
    private PartService partService;
    private ProductService productService;
    private static Product product1;
    private Product product;

    public AddProductController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/showFormAddProduct")
    public String showFormAddPart(Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        product = new Product();
        product1 = product;
        theModel.addAttribute("product", product);

        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product.getParts().contains(p))
                availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        theModel.addAttribute("assparts", product.getParts());
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
            Model theModel) {
        theModel.addAttribute("product", product);

        if (bindingResult.hasErrors()) {
            theModel.addAttribute("parts", partService.findAll());
            List<Part> availParts = new ArrayList<>();
            for (Part p : partService.findAll()) {
                if (!product.getParts().contains(p))
                    availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            theModel.addAttribute("assparts", product.getParts());
            return "productForm";
        } else {
            if (product.getId() != 0) {
                Product product2 = productService.findById((int) product.getId());
                if (product.getInv() - product2.getInv() > 0) {
                    for (Part p : product2.getParts()) {
                        int inv = p.getInv();
                        p.setInv(inv - (product.getInv() - product2.getInv()));
                        partService.save(p);
                    }
                }
            } else {
                product.setInv(0);
            }
            productService.save(product);
            return "confirmationaddproduct";
        }
    }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int theId, Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        Product theProduct = productService.findById(theId);
        product1 = theProduct;
        theModel.addAttribute("product", theProduct);
        theModel.addAttribute("assparts", theProduct.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!theProduct.getParts().contains(p))
                availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int theId, Model theModel) {
        Product product2 = productService.findById(theId);
        for (Part part : product2.getParts()) {
            part.getProducts().remove(product2);
            partService.save(part);
        }
        product2.getParts().clear();
        productService.save(product2);
        productService.deleteById(theId);

        return "confirmationdeleteproduct";
    }

    @GetMapping("/associatepart")
    public String associatePart(@Valid @RequestParam("partID") int theID, Model theModel) {
        if (product1 == null || product1.getName() == null) {
            return "saveproductscreen";
        } else {
            product1.getParts().add(partService.findById(theID));
            partService.findById(theID).getProducts().add(product1);
            productService.save(product1);
            partService.save(partService.findById(theID));
            theModel.addAttribute("product", product1);
            theModel.addAttribute("assparts", product1.getParts());
            List<Part> availParts = new ArrayList<>();
            for (Part p : partService.findAll()) {
                if (!product1.getParts().contains(p))
                    availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            return "productForm";
        }
    }

    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int theID, Model theModel) {
        product1.getParts().remove(partService.findById(theID));
        partService.findById(theID).getProducts().remove(product1);
        productService.save(product1);
        partService.save(partService.findById(theID));
        theModel.addAttribute("product", product1);
        theModel.addAttribute("assparts", product1.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product1.getParts().contains(p))
                availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        return "productForm";
    }

    // Requirement F: Add a "Buy Now" mapping
    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") int theId, RedirectAttributes redirectAttributes) {
        boolean success = productService.buyNow(theId);
        if (success) {
            redirectAttributes.addFlashAttribute("buyMessage", "Purchase successful!");
        } else {
            redirectAttributes.addFlashAttribute("buyMessage", "Purchase failed! Product may be out of stock.");
        }
        return "redirect:/mainscreen";
    }
}
