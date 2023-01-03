package tn.esprit.spring.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CategorieProduit;
import tn.esprit.spring.repository.CategorieProduitRepository;

@Service
@AllArgsConstructor
public class CategorieProduitService implements ICategorieProduitService{

	//@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<CategorieProduit> retrieveCategorieProduit() {

		return categorieProduitRepository.findAll();
	}

	@Override
	public CategorieProduit retrieveById(Long id) {
		return categorieProduitRepository.findById(id).orElse(null);
	}

	@Override
	public CategorieProduit saveCategorieProduit(CategorieProduit cP) {
		return categorieProduitRepository.save(cP);
	}

	@Override
	public CategorieProduit updateCategorieProduit(CategorieProduit cP) {
		return categorieProduitRepository.save(cP);
	}

	@Override
	public void deleteCategorieProduit(Long id) {
		categorieProduitRepository.deleteById(id);
	}
}
