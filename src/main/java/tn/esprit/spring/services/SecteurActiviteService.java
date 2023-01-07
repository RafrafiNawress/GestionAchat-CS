package tn.esprit.spring.services;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.entities.SecteurActivite;
import tn.esprit.spring.repository.FournisseurRepository;
import tn.esprit.spring.repository.SecteurActiviteRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class SecteurActiviteService implements ISecteurActiviteService{
	
	SecteurActiviteRepository secteurActiviteRepository;
	FournisseurRepository fournisseurRepository;

	@Override
	public List<SecteurActivite> retrieveSecteurActivite() {
		return (List<SecteurActivite>) secteurActiviteRepository.findAll();
	}

	@Override
	public SecteurActivite retrieveById(Long id) {
		log.info("in the method");
		 SecteurActivite s = secteurActiviteRepository.findById(id).orElse(null);
		log.info("out of the method");
		return s;
	}

	@Override
	@Transactional
	public SecteurActivite saveSecteurActivite(SecteurActivite sA) {
		Set<Fournisseur> fournisseurs= sA.getFournisseurs();
		for (Fournisseur f : fournisseurs ) {
			f.getSecteurActivites().add(sA);
			fournisseurRepository.save(f);
		}
		return  secteurActiviteRepository.save(sA);
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActivite sA) {
		return (SecteurActivite) secteurActiviteRepository.save(sA);
	}

	@Override
	public void deleteSecteurActivite(Long id) {
		log.info("In the method");
		secteurActiviteRepository.deleteById(id);
		log.info("out of the method");
		
	}

	@Override
	public Fournisseur assignSecteurActiviteToFournisseur(Long fournisseurId,
												   Long secteurActiviteId) {
		
		Fournisseur f= fournisseurRepository.findById(fournisseurId).orElse(null);
				f.getSecteurActivites()
				.add(secteurActiviteRepository
				.findById(secteurActiviteId).get());
		Fournisseur f1 = fournisseurRepository.save(f);
		return f1;
	}

}
