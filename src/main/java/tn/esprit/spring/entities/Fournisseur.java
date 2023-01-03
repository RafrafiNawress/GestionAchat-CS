package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ManyToAny;

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
public class Fournisseur implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long idFournisseur;
	
	String codeFournisseur;
	String libelleFournisseur;
	
	@Enumerated(EnumType.STRING)
	CategorieFournisseur categorieFournisseur;
	
	@OneToOne(cascade = CascadeType.ALL)
	public DetailFournisseur detailFournisseur;
	
	@ManyToMany
	public Set<SecteurActivite> secteurActivites;
	
	@OneToMany(mappedBy = "fournisseur")
	public Set<Facture> factures; 
	

}
