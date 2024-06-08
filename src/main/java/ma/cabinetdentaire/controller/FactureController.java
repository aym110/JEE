package ma.cabinetdentaire.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.dto.FactureDTO;
import ma.cabinetdentaire.dto.InterventionDTO;
import ma.cabinetdentaire.entity.Consultation;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Facture;
import ma.cabinetdentaire.service.ConsulationService;
import ma.cabinetdentaire.service.FactureService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Data @NoArgsConstructor
@Controller
public class FactureController {

    ConsulationService consulationService;
    FactureService factureService;

    public FactureController(FactureService factureService, ConsulationService consulationService){
        this.factureService=factureService;
        this.consulationService=consulationService;
    }
    @GetMapping("/dossier")
    public String getFactures(Model model, Long dossierId){
        List<FactureDTO> factures = factureService.getAllFacture(dossierId);
        model.addAttribute("factures",factures);
        return "factures";
    }

    @PostMapping("/createFacture/{id}")
    public String createFacture(@ModelAttribute("facture") Facture facture,@PathVariable("id") Long consultationId){
        factureService.createFacture(facture, consultationId);
        Consultation consultation = consulationService.getConsultatio(consultationId);
        Long patientId = consultation.getDossierMedicale().getPatient().getId();
        return "redirect:/dossier/" + patientId;
    }

}
