package tn.esprit.spring.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.CategorieProduit;
import tn.esprit.spring.services.CategorieProduitService;
import tn.esprit.spring.services.ICategorieProduitService;

@RestController
@AllArgsConstructor
public class CategorieProduitController {

	ICategorieProduitService categorieProduitService;


	@GetMapping("/get")
	List<CategorieProduit> retrieveCategorieProduit(){

		return categorieProduitService.retrieveCategorieProduit();
	}

	@GetMapping("/get/{id}")
	CategorieProduit retrieveById(@PathVariable  Long id){
		return categorieProduitService.retrieveById(id);
	}

	@PostMapping("/add")
	CategorieProduit saveCategorieProduit(@RequestBody CategorieProduit cP){
		return categorieProduitService.saveCategorieProduit(cP);
	}
	@PutMapping("/update")
	CategorieProduit updateCategorieProduit(@RequestBody CategorieProduit cP){
		return  categorieProduitService.updateCategorieProduit(cP);
	}

	@DeleteMapping("/delete/{id}")
	void deleteCategorieProduit(@PathVariable  Long id){

		categorieProduitService.deleteCategorieProduit(id);
	}

}
