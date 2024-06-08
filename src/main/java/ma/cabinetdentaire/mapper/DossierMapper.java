package ma.cabinetdentaire.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.dto.ConsultationDTO;
import ma.cabinetdentaire.dto.DossierDTO;
import ma.cabinetdentaire.dto.FactureDTO;
import ma.cabinetdentaire.entity.DossierMedical;
import ma.cabinetdentaire.entity.Facture;
import ma.cabinetdentaire.entity.InterventionMedecin;
import org.springframework.stereotype.Component;

@Component

public class DossierMapper {

    public DossierDTO mappingDossierToDossierDTO(DossierMedical dossierMedical){
        DossierDTO dossierDTO = new DossierDTO();
        return dossierDTO;
    }
    public ConsultationDTO mappingConsultationToConsultationDTO(InterventionMedecin interventionMedecin){
        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setActe(interventionMedecin.getActe().getCategorieActe().toString());
        consultationDTO.setDent(interventionMedecin.getDent());
        consultationDTO.setPrix(interventionMedecin.getPrixPatient());
        return consultationDTO;
    }

    public FactureDTO mappingFactureToFactureDTO(Facture facture){
        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setDateFacture(facture.getDateFacturation());
        factureDTO.setEtat(facture.getStatutPaiement().toString());
        factureDTO.setMontantTotale(facture.getMontantTotal());
        return factureDTO;
    }
}
