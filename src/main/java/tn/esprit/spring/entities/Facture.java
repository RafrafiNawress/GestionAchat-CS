package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facture implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long idFacture;
	float montantRemise;
	float montantFacture;
	Date dataCreationFacture;
	Date dataDerniereModification;
	boolean archivee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Fournisseur fournisseur;
	
	@OneToMany(mappedBy = "facture")
	public Set<Reglement> reglements;
	
	@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
	public Set<DetailFacture> detailFactures;

	
	

}
