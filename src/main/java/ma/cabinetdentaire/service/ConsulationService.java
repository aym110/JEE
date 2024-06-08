package ma.cabinetdentaire.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.dto.ConsultationDTO;
import ma.cabinetdentaire.dto.InterventionDTO;
import ma.cabinetdentaire.entity.*;
import ma.cabinetdentaire.entity.enums.CategorieActe;
import ma.cabinetdentaire.mapper.DossierMapper;
import ma.cabinetdentaire.repository.IActeRepository;
import ma.cabinetdentaire.repository.IConsultationRepository;
import ma.cabinetdentaire.repository.IInterventionRepository;
import ma.cabinetdentaire.repository.ISituationFinancierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ConsulationService {
    @Autowired
    DossierMedicaleService dossierMedicaleService;
    @Autowired
    IConsultationRepository consultationRepository;
    @Autowired
    IInterventionRepository interventionRepository;
    @Autowired
    IActeRepository acteRepository;
    @Autowired
    DossierMapper dossierMapper;
    @Autowired
    ISituationFinancierRepository situationFinancierRepository;

    public void creationConsultation(Consultation newconsultation,Long dossierId){
        DossierMedical dossierMedical = dossierMedicaleService.getDossier(dossierId);
        SituationFinanciere situationFinanciere = new SituationFinanciere();
        Consultation consultation = new Consultation();
        consultation.setDossierMedicale(dossierMedical);
        consultation.setDateConsultation(LocalDate.now());
        consultation.setTypeConsultation(newconsultation.getTypeConsultation());
        dossierMedical.getConsultations().add(consultation);
        situationFinanciere.setDateCreation(LocalDate.now());
        situationFinanciere.setDossierMedical(dossierMedical);
        dossierMedicaleService.saveDossier(dossierMedical);
        consultationRepository.save(consultation);
        situationFinancierRepository.save(situationFinanciere);
    }

    public List<Consultation>  getConsultations(Long patientId){
        DossierMedical dossierMedical = dossierMedicaleService.getDossierMedicale(patientId);
        List<Consultation> consultations = dossierMedical.getConsultations();
        return consultations;
    }



    public Consultation getConsultatio(Long consultationId){
        return consultationRepository.findById(consultationId).get();
    }

    public void updateConsultation(Consultation newConsultation){
        Consultation consultation = consultationRepository.findById(newConsultation.getId()).get();
        consultation.setTypeConsultation(newConsultation.getTypeConsultation());
        consultationRepository.save(consultation);
    }

    public List<ConsultationDTO> consultationDTOList(Long consultationId){
        List<ConsultationDTO> consultationDTOList = new ArrayList<>();
        Optional<Consultation> optionalConsultation = consultationRepository.findById(consultationId);
        if (!optionalConsultation.isPresent()) {
            throw new EntityNotFoundException("Consultation not found for id: " + consultationId);
        }
        Consultation consultation=optionalConsultation.get();
        List<InterventionMedecin> interventionsMedecin = interventionRepository.findAllByConsultation(consultation);
        for(InterventionMedecin interventionMedecin : interventionsMedecin){
            consultationDTOList.add(dossierMapper.mappingConsultationToConsultationDTO(interventionMedecin));
        }
        return consultationDTOList;
    }

    public List<InterventionMedecin> interventionMedecinList(Long consultationId){
        Optional<Consultation> optionalConsultation = consultationRepository.findById(consultationId);
        if (!optionalConsultation.isPresent()) {
            throw new EntityNotFoundException("Consultation not found for id: " + consultationId);
        }
        Consultation consultation=optionalConsultation.get();
        return interventionRepository.findAllByConsultation(consultation);
    }

    public List<ConsultationDTO> consults (Long consultationId){
        List<ConsultationDTO> cnsts = new ArrayList<>();
        List<InterventionMedecin> in = interventionMedecinList(consultationId);
        for(InterventionMedecin i : in){
            cnsts.add(dossierMapper.mappingConsultationToConsultationDTO(i));
        }
        return cnsts;
    }

    @Transactional
    public void createIntervention(InterventionDTO interventionDTO, Long idConsultation) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(idConsultation);
        if (!optionalConsultation.isPresent()) {
            throw new EntityNotFoundException("Consultation not found for id: " + idConsultation);
        }

        Consultation consultation = optionalConsultation.get();
        Acte acte = new Acte();
        acte.setCategorieActe(interventionDTO.getCategorieActe());
        acte.setPrixDeBase(interventionDTO.getPrixDebase());
        acteRepository.save(acte);

        InterventionMedecin interventionMedecin = new InterventionMedecin();
        interventionMedecin.setConsultation(consultation);
        interventionMedecin.setActe(acte);
        interventionMedecin.setDent(interventionDTO.getDent());
        interventionMedecin.setPrixPatient(interventionDTO.getPrixPatient());

        // Assurer que les listes d'interventions ne sont pas nulles
        if (consultation.getInterventionsMedecin() == null) {
            consultation.setInterventionsMedecin(new ArrayList<>());
        }
        if (acte.getInterventionsMedecin() == null) {
            acte.setInterventionsMedecin(new ArrayList<>());
        }

        consultation.getInterventionsMedecin().add(interventionMedecin);
        acte.getInterventionsMedecin().add(interventionMedecin);

        // Sauvegarder l'intervention en dernier pour garantir que toutes les relations sont bien établies
        interventionRepository.save(interventionMedecin);
    }

    public List<CategorieActe> categorieActeList(){
        List<CategorieActe> categorieActeList = new ArrayList<>();
        categorieActeList.add(CategorieActe.PREVENTION);
        categorieActeList.add(CategorieActe.CHIRURGIE);
        categorieActeList.add(CategorieActe.ORTHODONTIE);
        categorieActeList.add(CategorieActe.IMPLANTOLOGIE);
        categorieActeList.add(CategorieActe.SOINS_DES_CARIES);
        categorieActeList.add(CategorieActe.PROTHESES_DENTAIRES);
        categorieActeList.add(CategorieActe.DIAGNOSTIC);
        categorieActeList.add(CategorieActe.SOINS_DES_TISSUS_MOUS);
        return categorieActeList;

    }

    public List<Consultation> getClnst(Long id){
        DossierMedical  dossierMedical = dossierMedicaleService.getDossier(id);
        return consultationRepository.findAllByDossierMedicale(dossierMedical);
    }
    public void saveConsulation(Consultation consultation){
        consultationRepository.save(consultation);
    }

    public DossierMedical getDossier(Long dossierId){
        return dossierMedicaleService.getDossier(dossierId);
    }

    @Transactional
    public void deleteConsult(Long id){consultationRepository.deleteById(id);}

}
