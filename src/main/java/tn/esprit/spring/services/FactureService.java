package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.DetailFacture;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Fournisseur;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.entities.Reglement;
import tn.esprit.spring.repository.DetailFactureRepository;
import tn.esprit.spring.repository.FactureRepository;
import tn.esprit.spring.repository.FournisseurRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class FactureService implements IFactureService{

	FactureRepository factureRepository;
	FournisseurRepository fournisseurRepository;
	DetailFactureRepository detailFactureRepository;
	
	@Override
	public List<Facture> retrieveAllFactures() {
		return (List<Facture>)factureRepository.findAll();
	}

	@Override
	public Facture retrieveFacture(Long id) {
		return (Facture) factureRepository.findById(id).orElse(null);
	}

	@Override
	public void cancelFacture(Long id) {
		Facture f=factureRepository.findById(id).get();
		f.setArchivee(true);
		
	}



	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		Facture f= factureRepository.findById(idFacture).get(); 
		return (List<Reglement>)f.getReglements();
	}

	@Override
	public Facture addFacture(Facture f, Long idFournisseur) {
		Set<DetailFacture> df=f.getDetailFactures();
		float montantRemise=0f;
		float montantFacture=0f;
		for (DetailFacture detailFacture : df) {
			Produit p=detailFacture.getProduit();
			detailFacture.setPrixTotalDetail(p.getPrix()*detailFacture.getQteCommandee());
			detailFacture.setMontantRemise(detailFacture.getPrixTotalDetail()*detailFacture.getPourcentageRemise()/100);
			montantFacture+=detailFacture.getPrixTotalDetail();
			montantRemise+=detailFacture.getMontantRemise();
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		Fournisseur four=fournisseurRepository.findById(idFournisseur).orElse(null);
		four.getFactures().add(f);
		fournisseurRepository.save(four);
		return f;
	}

	@Override
	public Reglement addReglement(Reglement r, Long idFacture) {
		Facture f=factureRepository.findById(idFacture).orElse(null);
		Set<Reglement> rs=f.getReglements();
		float sMP=0f;
		for (Reglement reglement : rs) {
			sMP+=reglement.getMontantPaye();
		}
		if((sMP+r.getMontantPaye())<f.getMontantFacture()){
			r.setMontantPaye(sMP+r.getMontantPaye());
			r.setMontantRestant(f.getMontantFacture()-(r.getMontantPaye()+sMP));
			f.getReglements().add(r);
		}else if((sMP+r.getMontantPaye())==f.getMontantFacture()){
			r.setMontantPaye(f.getMontantFacture());
			r.setMontantRestant(0f);
			r.setPayee(true);
			f.getReglements().add(r);
		}
		return r;
	}

	/*@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		Set<Facture> f= (Set<Facture>) factureRepository/*.findByDataCreationFactureBetween(startDate,endDate);
		float chAff=0f;
		for (Facture facture : f) {
			if (!facture.isArchivee()) {
				for (Reglement reglement : facture.getReglements()) {
					chAff+=reglement.getMontantPaye();
				}
			}
		}
		return chAff;
	}*/

	/*@Override
	public float pourcentageRecouvrement(Date startDate, Date endDate) {
		Set<Facture> f= (Set<Facture>) factureRepository.findByDataCreationFactureBetween(startDate,endDate);
		int s=0;
		int nbf=0;
		
		for (Facture facture : f) {
			if (!facture.isArchivee()) {
				nbf++;
				s+=facture.getReglements().size();
				
			}
		}
		return (float)s/nbf;
	}
*/

/*	@Scheduled(cron = "0 0 0 1 1 *" )
	public float getChiffreAffaireMensuel() {
		//get current date 
		Date currentDate = Calendar.getInstance().getTime();
		// get current date minus 1 year
		Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Date " + dateFormat.format(date));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        Date startDate = c.getTime();
        
		Set<Facture> f= (Set<Facture>) factureRepository.findByDataCreationFactureBetween(startDate,currentDate);
		float chAff=0f;
		for (Facture facture : f) {
			if (!facture.isArchivee()) {
				for (Reglement reglement : facture.getReglements()) {
					chAff+=reglement.getMontantPaye();
				}
			}
		}
		return chAff;
	}

*/


	/*void cancelFacture2(Long id) {
		Facture facture = factureRepository.findByIdFactureAndArchivee(id, false);
		facture.setArchivee(true);
		factureRepository.save(facture);

	}*/

	/*List<Facture> getFacturesByFournisseur2(Fournisseur fournisseur){
		return factureRepository.findByFournisseur(fournisseur);
	}*/

}
