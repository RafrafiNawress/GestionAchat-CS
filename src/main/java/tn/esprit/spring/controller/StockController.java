package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.CategorieFournisseur;
import tn.esprit.spring.entities.Reglement;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.services.IStockService;

@RestController //staamlna hedhi moch controller bch get mapping tjib auto lhajet li tekhdem behom nab9ach nektebhom
@RequestMapping("stockcontroller") //tetzed 9bal kol methode
public class StockController {
	@Autowired
	IStockService stockService;
	
	@GetMapping("/afficherStocks")
	@ResponseBody //pour afficher resultat
	List<Stock> afficherStocks(){
	return stockService.retrieveStock();
	}
	
	
	@GetMapping("/afficherStock/{id}")
	@ResponseBody //pour afficher resultat
	Stock afficherstock(@PathVariable("id")Long idstcok) {
	return stockService.retrieveById(idstcok);
	}
	
	@GetMapping("/afficherStock")
	@ResponseBody //pour afficher resultat
	Stock afficherstock2(@RequestParam("id")Long idstcok) {
	return stockService.retrieveById(idstcok);
	}
	
	@PostMapping("/ajouterstock")
	@ResponseBody
	Stock ajouterStock(@RequestBody Stock s) { //yaani bch naadih fl body les info
	return stockService.saveStock(s);
	}
	
	@PutMapping("/updatestock")
	@ResponseBody
	Stock updateStock(@RequestBody Stock s) { 
	return stockService.updateStock(s);
	}
	
	@DeleteMapping("/deletestock/{id}")
	@ResponseBody
	void supprimerStock(@PathVariable("id") Long id) {
	stockService.deleteStock(id);
	}


	@GetMapping("/retrieveReglement")
	List<Reglement> retrieveReglement(CategorieFournisseur cf, Long id){
		return stockService.retrieveReglement( cf,  id);
	}
}