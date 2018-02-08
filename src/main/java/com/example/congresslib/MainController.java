package com.example.congresslib;

import com.example.congresslib.repository.AddBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.xml.bind.Binder;

@Controller
public class MainController {
    @Autowired
    AddBooksRepository addbooksrepo;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/addlibrarybooks")
    public String addbooks(Model model) {
        model.addAttribute("addbooks", new Addbooks());
        return "addlibrarybooks";
    }

    @PostMapping("/addlibrarybooks")
    public String processAddbooks(@Valid Addbooks addbooks, BindingResult result) {
        if (result.hasErrors()) {
            return "addlibrarybooks";
        }
        addbooksrepo.save(addbooks);
        return "redirect:/";
    }
    @GetMapping("/listallbooks")
    public String listallbooks(Model model){
        model.addAttribute("addedbooks", addbooksrepo.findAll());
        return "listallbooks";
    }
}

