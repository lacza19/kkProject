package com.example.kkproject.controller;

import com.example.kkproject.model.UDomain;
import com.example.kkproject.service.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @Autowired
    UService uService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("uDomainForm", new UDomain());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UDomain uDomain, Model model) {
        model.addAttribute("uDomainForm", new UDomain());
        uService.insertDataToTable(uDomain.getOriginalUrl());
        model.addAttribute("insertMessage", "Sikeresen megtörtént a URL rövidítés");
        return "index";
    }

    @PostMapping("/search")
    public String searchOriginalUrl(@ModelAttribute UDomain uDomain, Model model) {
        var oUrl = uService.search(uDomain.getShortedUrl());
        System.out.println(oUrl);
        model.addAttribute("uDomainForm", new UDomain());
        model.addAttribute("searchMessage", "A megadott rövidített url-hez tartozó teljes url a következő: "+uService.search(uDomain.getShortedUrl()));
        model.addAttribute("foundOriginalUrl", oUrl);
        return "index";
    }

    @PostMapping("/alldata")
    public String getAllData(@ModelAttribute UDomain uDomain, Model model) {
        model.addAttribute("uDomainForm", new UDomain());
        model.addAttribute("alldatas", uService.getAll());
        return "index";
    }
}
