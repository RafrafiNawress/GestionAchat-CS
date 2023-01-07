package tn.esprit.spring.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.DetailFournisseur;
import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.entities.SecteurActivite;
import tn.esprit.spring.repository.DetailFournisseurRepository;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.SecteurActiviteRepository;

@Service
@AllArgsConstructor
public class FournisseurService implements IFournisseurService{

	
	FournisseurRepository fournisseurRepository;
	DetailFournisseurRepository detailFournisseurRepository;

	SecteurActiviteRepository secteurActiviteRepository;
	
	@Override
	public List<Fournisseur> retrieveFournisseur() {
		return (List<Fournisseur>) fournisseurRepository.findAll();
	}

	@Override
	public Fournisseur retrieveById(Long id) {
		return (Fournisseur) fournisseurRepository.findById(id).orElse(null);
	}

	@Override
	public Fournisseur saveFournisseur(Fournisseur f) {
		return fournisseurRepository.save(f);
		
	}

	private DetailFournisseur addDetailFournisseur(Fournisseur f) {
		DetailFournisseur df= f.getDetailFournisseur();
		return detailFournisseurRepository.save(df);
	}

	@Override
	public Fournisseur updateFournisseur(Fournisseur f) {
		return (Fournisseur) fournisseurRepository.save(f);
	}

	@Override
	public void deleteFournisseur(Long id) {
		fournisseurRepository.deleteById(id);
		
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long fournisseurId, Long secteurActiviteId) {
		Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);
		SecteurActivite secteurActivite = secteurActiviteRepository.findById(secteurActiviteId).orElse(null);

		fournisseur.getSecteurActivites().add(secteurActivite);
		fournisseurRepository.save(fournisseur);
	}


}
