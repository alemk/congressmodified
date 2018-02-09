package com.example.congresslib;

import com.example.congresslib.repository.AddBooksRepository;
import com.example.congresslib.repository.Borrowbookrepo;
import com.example.congresslib.repository.Returnedbooksrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    AddBooksRepository addbooksrepo;
    @Autowired
    Returnedbooksrepository listallbooksrepo;
    @Autowired
    Borrowbookrepo borrowbookrepo;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/addlibrarybooks")
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
    @RequestMapping("/borrowbook")
    public String borrowedbooks(Model model){
        Iterable<Addbooks> books = addbooksrepo.findAll();
        List<Addbooks> bb = new ArrayList<>();
        for(Addbooks ab:books){
            if(ab.getBookavailable()==false)
                    bb.add(ab);
        }
        model.addAttribute("borrowedbooks",bb);
        return "borrowbook";
    }
    @RequestMapping("/returnedbooks")
    public String returnedbooks(Model model) {
        Iterable<Addbooks> addbooks = addbooksrepo.findAll();
        List<Addbooks> retbooks = new ArrayList<>();
        for (Addbooks rb : addbooks) {
            if (rb.getBookavailable() == true) {
                retbooks.add(rb);
            }
            model.addAttribute("returnedbook", retbooks);
        }
        return "returnedbooks";
    }



   /*
   @RequestMapping("/borrowbook")
    public String borrowedbooks(Model model){
        Iterable<Addbooks> addbooks = addbooksrepo.findAll();
        for(Addbooks ab:addbooks){
            if(ab.getBookavailable()==false)
                model.addAttribute("borrowedbooks",ab);
        }
        return "borrowbook";
    }


   public String returnedbooks(Model model){
        Iterable<Addbooks> addbooks = addbooksrepo.findAll();
        for(Addbooks rb:addbooks){
            if(rb.getBookavailable()==true)
                model.addAttribute("returnedbook",rb);
        }
        return "returnedbooks";}*/

    @RequestMapping("/borrow/{id}")
    public String borrowedbooks(@PathVariable("id") long id, Model model){
        model.addAttribute("book", addbooksrepo.findOne(id));
        Addbooks ad = addbooksrepo.findOne(id);
        ad.setBookavailable(false);
        addbooksrepo.save(ad);
        //model.addAttribute("borrowed", ad);
        return "redirect:/borrowbook";

    }
    @RequestMapping("/return/{id}")
    public String returnedbooks(@PathVariable("id") long id, Model model){
        model.addAttribute("addedbooks", addbooksrepo.findOne(id));
        Addbooks ad = addbooksrepo.findOne(id);
        ad.setBookavailable(true);
        addbooksrepo.save(ad);
        //model.addAttribute("returned", ad);
        return "redirect:/returnedbooks";
    }

}

    /*@RequestMapping("/borrowbook/{id}")
    public String borrowbook(@PathVariable("booktitle") long id, Model model){
        model.addAttribute("booklist", listallbooksrepo.findOne(id));
        return "borrowbook";
    }
    @PostMapping("/borrowbook")
    public String processborrowbook(@Valid Borrowers borrowers, BindingResult result){
    if (result.hasErrors()){
        return "borrowbook";
    }
        borrowbookrepo.save(borrowers);
        return "redirect:/";
    }**/


