package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.CategorieFournisseur;
import tn.esprit.spring.entities.Reglement;

import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement,Long> {

    List<Reglement>  findByFactureFournisseurCategorieFournisseurAndFactureDetailFacturesProduitCategorieProduitIdCategorieProduit(CategorieFournisseur cf,Long id);
}
