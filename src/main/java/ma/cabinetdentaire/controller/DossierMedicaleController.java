package ma.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.dto.ConsultationDTO;
import ma.cabinetdentaire.dto.FactureDTO;
import ma.cabinetdentaire.dto.InterventionDTO;
import ma.cabinetdentaire.entity.Consultation;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Facture;
import ma.cabinetdentaire.entity.InterventionMedecin;
import ma.cabinetdentaire.entity.enums.CategorieActe;
import ma.cabinetdentaire.entity.enums.TypeConsultation;
import ma.cabinetdentaire.service.ConsulationService;
import ma.cabinetdentaire.service.DossierMedicaleService;
import ma.cabinetdentaire.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Data @NoArgsConstructor @AllArgsConstructor
public class DossierMedicaleController {
    @Autowired
    DossierMedicaleService dossierMedicaleService;
    @Autowired
    ConsulationService consulationService;
    @Autowired
    FactureService factureService;


    @GetMapping("/dossier/{id}")
    public String getDossierMedicale(Model model,@PathVariable("id") Long patientId){
        DossierMedical dossierMedicale = dossierMedicaleService.getDossierMedicale(patientId);
        List<Consultation> consultationList = consulationService.getConsultations(patientId);
        List<TypeConsultation> typesConsultation = new ArrayList<>();
        typesConsultation.add(TypeConsultation.CONSULTATION_GENERALE);
        typesConsultation.add(TypeConsultation.URGENCE);
        typesConsultation.add(TypeConsultation.SUIVI);
        Consultation consult = new Consultation();
        List<FactureDTO> factures = factureService.getAllFacture(patientId);
        List<Consultation> constl = consulationService.getClnst(dossierMedicale.getId());
        Facture facture = new Facture();
        model.addAttribute("factures",factures);
        model.addAttribute("dossierMedicale", dossierMedicale);
        model.addAttribute("consultationList", consultationList);
        model.addAttribute("typesConsultation", typesConsultation);
        model.addAttribute("consult", consult);
        model.addAttribute("constl", constl);
        model.addAttribute("facture",facture);
        return "dossierMedicale";
    }

    @PostMapping("/createConsultation/{id}")
    public String createConsultation(@ModelAttribute("consultaion") Consultation consultation,@PathVariable("id") Long dossierId){
        consulationService.creationConsultation(consultation, dossierId);
        DossierMedical dossierMedical = dossierMedicaleService.getDossier(dossierId);
        Long patientId = dossierMedical.getPatient().getId();
        return "redirect:/dossier/" + patientId;
    }

    @GetMapping("/dossier/actes/{id}")
    public String getInterventions(Model model,@PathVariable("id") Long consultationId){
        List<ConsultationDTO> cslts = consulationService.consults(consultationId);
        InterventionDTO interventionDTO = new InterventionDTO();
        List<CategorieActe> categorieActeList = consulationService.categorieActeList();
        Long consul = consultationId;
        model.addAttribute("cslts",cslts);
        model.addAttribute("interventionDTO",interventionDTO);
        model.addAttribute("consul",consul);
        model.addAttribute("categorieActeList",categorieActeList);
        return "interventions";
    }
    @GetMapping("/test/{id}")
    public List<InterventionMedecin> intervs(@PathVariable("id") Long consultationId){
        return consulationService.interventionMedecinList(consultationId);
    }

    @GetMapping("/actes/{id}")
    public String inters(Model model, @PathVariable("id") Long consultationId){
        List<ConsultationDTO> cslts = consulationService.consults(consultationId);
        model.addAttribute("cslts",cslts);
        return "test";
    }

    @PostMapping("/dossier/createActe/{id}")
    public String createIntervention(@ModelAttribute("interventionDTO") InterventionDTO interventionMedecin,@PathVariable("id") Long consultationId){
        consulationService.createIntervention(interventionMedecin,consultationId);
        return "redirect:/dossier/actes/"+consultationId;
    }

    @GetMapping("/deletConsu/{idPatient}/{idCons}/")
    public String deletConsul(@PathVariable("idPatient") Long id, @PathVariable("idCons") Long idCons){
        consulationService.deleteConsult(idCons);
        return "redirect:/dossier/" + id;
    }

}
