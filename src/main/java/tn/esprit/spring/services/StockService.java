package tn.esprit.spring.services;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.CategorieFournisseur;
import tn.esprit.spring.entities.Reglement;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.repository.ReglementRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
@AllArgsConstructor
@Slf4j
public class StockService implements IStockService{

	StockRepository stockRepository;
	ReglementRepository reglementRepository;
	
	@Override
	public List<Stock> retrieveStock() {
		return stockRepository.findAll();

	}

	@Override
	public Stock retrieveById(Long id) {
		return stockRepository.findById(id).orElse(null);
	}

	@Override
	public Stock saveStock(Stock s) {
		Stock s1 = stockRepository.save(s);
		return s1;

	}

	@Override
	public Stock updateStock(Stock s) {
		return stockRepository.save(s);
	}

	@Override
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);
		
	}

	@Override
	public List<Stock> testQte(Integer qt) {
		return stockRepository.findByQteMinGreaterThan(qt);
	}

//	@Scheduled(cron = "*/15 * * * * *")
	@Override
	public void retrieveStatusStock() {
			
		List<Stock> stocks= stockRepository.findAll();
		for (Stock stock : stocks) {
			if(stock.getQteMin()>stock.getQte()){

				stock.getProduits().stream().forEach(produit -> log.info(produit.getCodeProduit() ));
			}
		}
	}

	@Scheduled(cron = "* 0/30 * * * *")
	public void retrieveStatusStock1() {

		List<Stock> stocks= stockRepository.findAll();
		for (Stock stock : stocks) {
			if(stock.getQteMin()>stock.getQte()){
				log.info(stock.toString());
			}
		}
	}

	public void retrieveLebelleStock() {

		List<Stock> stocks= stockRepository.findAll();
		for (Stock stock : stocks) {
			if(stock.getQteMin()>stock.getQte()){
				//log.info("stock "+stock.toString());
				log.info("libelle :  "+ stock.getLibelleStock().toString());
			}
		}
	}

	@Override
	public List<Reglement> retrieveReglement(CategorieFournisseur cf, Long id){
		return reglementRepository.findByFactureFournisseurCategorieFournisseurAndFactureDetailFacturesProduitCategorieProduitIdCategorieProduit(cf,id);
	}
}
