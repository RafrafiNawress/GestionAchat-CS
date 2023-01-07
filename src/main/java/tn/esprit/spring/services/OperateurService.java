package tn.esprit.spring.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Operateur;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.OperateurRepository;

@Service
@AllArgsConstructor
public class OperateurService implements IOperateurService{
	
	OperateurRepository operateurRepository;
	FactureRepository factureRepository;
	

	@Override
	public List<Operateur> retrieveOperateur() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur retrieveById(Long id) {
		return (Operateur)operateurRepository.findById(id).orElse(null);
	}

	@Override
	public Operateur saveOperateur(Operateur o) {
		return (Operateur) operateurRepository.save(o);
	}

	@Override
	public Operateur updateOperateur(Operateur o) {
		return (Operateur) operateurRepository.save(o);
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
	}

	@Override
	public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
		Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
		Facture facture = factureRepository.findById(idFacture).orElse(null);

		operateur.getFactures().add(facture);
		operateurRepository.save(operateur);

	}


}
