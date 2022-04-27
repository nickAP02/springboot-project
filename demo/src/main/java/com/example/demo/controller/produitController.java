package com.example.demo.controller;
import com.example.demo.model.Produit;
import com.example.demo.model.repository.ProduitRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/produit")
public class produitController {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/afficher")
    public String displayProduit(Model model){
        model.addAttribute("produits",produitService.showProduits());
        return "Produits/showProduit";
    }
    @GetMapping("/seuil")
    public String displaySeuil(Model model){
        model.addAttribute("seuil",produitService.showSeuil());
        return "Produits/seuil";
    }
    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("Listcategory",categoryService.showAllCategory());
        return "Produits/produit";
    }
    @PostMapping("/save")
    public String save(Produit produit){
        produit.setDateCreation(LocalDate.now());
        produit.setQteStock(0);
        produitService.saveProduit(produit);
        return "redirect:/produit/afficher";
    }
   @GetMapping("/edit/{id}")
   public String edit(@PathVariable("id") int id, Model model){
       model.addAttribute("produitId",produitService.showOneProduit(id));
       return "Produits/modification";
   }
    @PostMapping("/edit")
    public String edit(@ModelAttribute("produit") Produit produit)
    {
        produitService.saveProduit(produit);
        return "redirect:/produit/afficher";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        model.addAttribute("produitId",produitService.showOneProduit(id));
        return "Produits/suppression";
    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("produitId") int id){
        produitService.deleteProduit(id);
        return "redirect:/produit/afficher";
    }
    @GetMapping("/search")
    public String recherche(@RequestParam("keyword") String libelle, Model model){
        List<Produit> produitList = produitService.findByKeyword(libelle);
        model.addAttribute("search",produitList);
        System.out.println(libelle);
        return "Produits/search";
    }
}
