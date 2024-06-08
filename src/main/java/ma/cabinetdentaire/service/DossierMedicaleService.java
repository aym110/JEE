package ma.cabinetdentaire.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.enums.CategorieActe;
import ma.cabinetdentaire.repository.IDoosierMedicaleRepository;
import ma.cabinetdentaire.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Data @NoArgsConstructor @AllArgsConstructor
public class DossierMedicaleService {
    @Autowired
    IDoosierMedicaleRepository dossierMedicaleRepository;
    @Autowired
    IPatientRepository patientRepository;


    public void createDossierMedicale(Patient patient){
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setPatient(patient);
        dossierMedicaleRepository.save(dossierMedical);
    }

    public DossierMedical getDossierMedicale(Long idPatient){
        Patient patient = patientRepository.findById(idPatient).get();
        return dossierMedicaleRepository.findByPatient(patient);
    }

    public DossierMedical getDossier(Long dossierId){
        return dossierMedicaleRepository.findById(dossierId).get();
    }

    public void saveDossier(DossierMedical dossierMedical){
        dossierMedicaleRepository.save(dossierMedical);
    }

//    @Transactional
//    public void deleteDossierMedical(Long dossierMedicalId) {
//        // Find the DossierMedical
//        DossierMedical dossierMedical = dossierMedicaleRepository.findById(dossierMedicalId).get();
//
//        // Delete associated consultations
//        consultationRepository.deleteByDossierMedicalId(dossierMedicalId);
//
//        // Delete the DossierMedical
//        dossierMedicalRepository.delete(dossierMedical);
//    }





}
