package ma.cabinetdentaire.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.dto.FactureDTO;
import ma.cabinetdentaire.entity.*;
import ma.cabinetdentaire.entity.enums.StatutPaiement;
import ma.cabinetdentaire.mapper.DossierMapper;
import ma.cabinetdentaire.repository.IConsultationRepository;
import ma.cabinetdentaire.repository.IFactureRepository;
import ma.cabinetdentaire.repository.ISituationFinancierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
@Service
public class FactureService {

    DossierMedicaleService dossierMedicaleService;
    ConsulationService consulationService;
    IFactureRepository factureRepository;
    ISituationFinancierRepository situationFinancierRepository;
    DossierMapper dossierMapper;


    @Autowired
    public FactureService(ConsulationService consulationService, IFactureRepository factureRepository, ISituationFinancierRepository situationFinancierRepository, DossierMedicaleService dossierMedicaleService, DossierMapper dossierMapper){
        this.consulationService=consulationService;
        this.factureRepository=factureRepository;
        this.situationFinancierRepository=situationFinancierRepository;
        this.dossierMedicaleService=dossierMedicaleService;
        this.dossierMapper=dossierMapper;
    }

    public void createFacture(Facture newFacture, Long colsultationId){
        Double montantTotale =0.0 ;
        Double montantReste = newFacture.getMontantTotal() - newFacture.getMontantPaye();

        Consultation consultation = consulationService.getConsultatio(colsultationId);
        DossierMedical dossierMedical = dossierMedicaleService.getDossier(consultation.getDossierMedicale().getId());
        List<InterventionMedecin> interventionMedecinList = consulationService.interventionMedecinList(colsultationId);
        for(InterventionMedecin i : interventionMedecinList){
            montantTotale+=i.getPrixPatient();
        }
        Facture facture = new Facture();
        facture.setDateFacturation(LocalDate.now());
        facture.setMontantTotal(montantTotale);
        facture.setMontantPaye(newFacture.getMontantPaye());
        if(montantReste > 0){
            facture.setStatutPaiement(StatutPaiement.EN_ATTENTE);
        }else{
            facture.setStatutPaiement(StatutPaiement.PAYE);
        }
        facture.setMontantRestant(montantReste);
        SituationFinanciere situationFinanciere = situationFinancierRepository.findByDossierMedical(dossierMedical);
        situationFinanciere.getFactures().add(facture);
        facture.setSituationFinanciere(situationFinanciere);
        factureRepository.save(facture);
        consultation.setFacture(facture);
        consulationService.saveConsulation(consultation);

    }



    public List<FactureDTO> getAllFacture(Long patientId) {
        DossierMedical dossierMedical = dossierMedicaleService.getDossierMedicale(patientId);
//        SituationFinanciere situationFinanciere = new SituationFinanciere();
//        if (dossierMedical.getSituationFinanciere() == null) {
//            // Save the situationFinanciere instance before associating it with dossierMedical
//            // Ensure you have a service to save SituationFinanciere
//            dossierMedical.setSituationFinanciere(situationFinanciere);
//            dossierMedicaleService.saveDossier(dossierMedical);
//        }
        SituationFinanciere situationFinanciere = situationFinancierRepository.findByDossierMedical(dossierMedical);
        List<Facture> factures =factureRepository.findBySituationFinanciere(situationFinanciere);
        List<FactureDTO> factureDTOList = new ArrayList<>();
        for (Facture f : factures) {
            factureDTOList.add(dossierMapper.mappingFactureToFactureDTO(f));
        }
        return factureDTOList;
    }







}
