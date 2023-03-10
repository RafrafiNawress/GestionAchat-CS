package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.DetailFournisseur;
import tn.esprit.spring.entities.Fournisseur;


public interface IFournisseurService {

	List<Fournisseur> retrieveFournisseur();
	Fournisseur retrieveById(Long id);
	Fournisseur saveFournisseur(Fournisseur f);
	Fournisseur updateFournisseur(Fournisseur f);
	void deleteFournisseur(Long id);

	public void assignSecteurActiviteToFournisseur(Long fournisseurId, Long
			secteurActiviteId) ;
}
