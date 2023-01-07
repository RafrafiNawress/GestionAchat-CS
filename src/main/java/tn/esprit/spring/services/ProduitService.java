package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CategorieProduit;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.repository.CategorieProduitRepository;
import tn.esprit.spring.repository.ProduitRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
@AllArgsConstructor
public class ProduitService implements IProduitService{

	ProduitRepository produitRepository;

	CategorieProduitRepository categorieProduitRepository;
	
	StockRepository stockRepository;
	
	@Override
	public List<Produit> retrieveProduit() {
		return ( List<Produit>)  produitRepository.findAll();
	}

	@Override
	public Produit retrieveById(Long id) {
		return (Produit ) produitRepository.findById(id).orElse(null);
	}

	@Override
	public Produit saveProduit(Produit p, Long idCategorieProduit, Long idStock) {
	
	/*	p.setCategorieProduit(categorieProduitService.getById(idCategorieProduit));
		p.setStock(stockRepository.getById(idStock));*/
		return null;
	}

	@Override
	public Produit updateProduit(Produit p, Long idCategorieProduit, Long idStock) {
		return (Produit) produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);
		
	}

	@Override
	public Produit addProduit(Produit p, Long idCategorieProduit, Long idStock) {

		CategorieProduit categorieProduit = categorieProduitRepository.findById(idCategorieProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		p.setCategorieProduit(categorieProduit);
		p.setStock(stock);
		return produitRepository.save(p);
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		produit.setStock(stock);
		produitRepository.save(produit);

	}


}
