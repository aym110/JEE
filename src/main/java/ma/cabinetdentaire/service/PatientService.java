package ma.cabinetdentaire.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.Personne;
import ma.cabinetdentaire.repository.IDoosierMedicaleRepository;
import ma.cabinetdentaire.repository.IPatientRepository;
import ma.cabinetdentaire.repository.IPersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data @AllArgsConstructor @NoArgsConstructor
public class PatientService {
    @Autowired // il est recommender d'utiliser Autowired dans le constructeur et pas attribue
    IPatientRepository patientRepository;
    @Autowired
    IPersonneRepository personneRepository;
    @Autowired
    DossierMedicaleService dossierMedicaleService;

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }
//    public void ajouterPatient(Patient... patients){
//        patientRepository.save(listOf(patients));
//    }

    @Transactional
    public String createPatient(Patient patient){
        dossierMedicaleService.createDossierMedicale(patient);
        patientRepository.save(patient);
        return "bien créer";
    }
    public Patient getPatientById(Long id){
        return patientRepository.findById(id).get();
    }

    public void deletePatient(Long id){ patientRepository.delete(getPatientById(id));}
    public void updatePatient(Long id, Patient newPatient){
        Patient oldPatient = getPatientById(id);
        if (oldPatient != null) {
            oldPatient.setNom(newPatient.getNom());
            oldPatient.setPrenom(newPatient.getPrenom());
            oldPatient.setAdresse(newPatient.getAdresse());
            oldPatient.setTelephone(newPatient.getTelephone());
            oldPatient.setEmail(newPatient.getEmail());
            oldPatient.setCin(newPatient.getCin());
            oldPatient.setProfession(newPatient.getProfession());
            oldPatient.setDateNaissance(newPatient.getDateNaissance());
            oldPatient.setMutuelle(newPatient.getMutuelle());
            oldPatient.setGroupSanguin(newPatient.getGroupSanguin());
            patientRepository.save(oldPatient);
        } else {
            // Handle the case where the patient with the given id is not found
            throw new EntityNotFoundException("Patient with id " + id + " not found");
        }

    }




}
