package tn.esprit.spring.entities;

import java.io.Serializable;

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
public class DetailFacture implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDetailFacture;
	
	int qteCommandee;
	float prixTotalDetail;
	int pourcentageRemise;
	float montantRemise;
	
	@ManyToOne
	public Facture facture;
	
	@ManyToOne
	public Produit produit;

}
