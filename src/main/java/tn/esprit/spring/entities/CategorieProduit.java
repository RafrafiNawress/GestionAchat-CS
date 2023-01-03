package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorieProduit implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long idCategorieProduit;
	String codeProduit;
	String libelleCategorieProduit;
	
	@OneToMany(mappedBy = "categorieProduit")
	public Set<Produit> produits;
}
