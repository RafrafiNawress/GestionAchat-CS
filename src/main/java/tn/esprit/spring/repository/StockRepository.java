package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
	List<Stock> findByQteMinGreaterThan(Integer qt);

	@Query("Select s from Stock s where s.libelleStock =: libelle and s.qte =: qtt")
	List<Stock> retrieveStockByLibelleEtQte(@Param("libelle") String libelle, @Param("qtt") Integer qteTes);

	//methode 2
	@Query("Select s from Stock s where s.libelleStock = ?1 and s.qte = ?2")
	List<Stock> retrieveStockByLibelleEtQte2(String libelle, Integer qteTes);

	List<Stock> findByLibelleStockAndQte(String libelle, int qte);
	
	//methode 3
	@Query(value="Select * from stock where s.libelle_stock =?1 and s.qte =?2", nativeQuery=true)
	List<Stock> retrieveStockByLibelleEtQte3(String libelle, Integer qteTes);
}

