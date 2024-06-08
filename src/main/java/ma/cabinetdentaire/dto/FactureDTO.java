package ma.cabinetdentaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data @AllArgsConstructor @NoArgsConstructor
public class FactureDTO {
    Long consultationId;
    LocalDate dateFacture;
    String etat;
    Double montantTotale;
}
