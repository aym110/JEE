package ma.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Patient;
import ma.cabinetdentaire.entity.Personne;
import ma.cabinetdentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    public String Patients(Model model){
        List<Patient> patients = patientService.getAllPatient();
        Patient patient = new Patient();
        model.addAttribute("patients",patients);
        model.addAttribute("patient",patient);
        return "patients";
    }
    @GetMapping("/patient")
    public List<Patient> Patient(){
        return patientService.getAllPatient();
    }

    @PostMapping("/createPatient")
    public String createPatient(@ModelAttribute("patient") Patient patient){
        patientService.createPatient(patient);
        return "redirect:/patients";
    }
    @GetMapping("/getPatient/{id}")
    public Patient getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
