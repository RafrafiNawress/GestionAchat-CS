package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Fournisseur;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

	@Query("select f from Facture f where f.fournisseur.idFournisseur =?1 ")
	List<Facture> retrieveFactureByFournisseur(Long id);

	List<Facture> findByFournisseurIdFournisseur(Long id);

}
