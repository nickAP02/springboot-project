package com.example.demo.service;

import com.example.demo.model.Produit;
import com.example.demo.model.repository.ProduitRepository;
import com.example.demo.model.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    private UtilisateurRepository utilisateurRepository;
    public void saveProduit(Produit produit){
        produitRepository.save(produit);
    }

    public List<Produit> showProduits(){
       return produitRepository.findAll();
    }
    public Produit showOneProduit(int id){
        Optional<Produit> optional = produitRepository.findById(id);
        Produit produit = null;
        if(optional.isPresent()){
            produit = optional.get();
        }
        else {
            throw new RuntimeException("Produit introuvable");
        }
        return produit;
    }
    public void deleteProduit(int id)
    {
        if(showOneProduit(id)!=null)
            produitRepository.deleteById(id);
    }
    public void updateQteStock(int id, int qte)
    {
        produitRepository.UpdateQteProduit(id,qte);
    }

    public List<Produit> findByKeyword(String keyword){
        return produitRepository.findByKeyword(keyword);
    }

}
