package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Produit;


public interface IProduitService {
	
	List<Produit> retrieveProduit();
	Produit retrieveById(Long id);
	Produit saveProduit(Produit p, Long idCategorieProduit, Long idStock);
	Produit updateProduit(Produit p, Long idCategorieProduit, Long idStock);
	void deleteProduit(Long id);
	Produit addProduit (Produit p, Long idCategorieProduit, Long idStock);

	void assignProduitToStock(Long idProduit, Long idStock);
}
