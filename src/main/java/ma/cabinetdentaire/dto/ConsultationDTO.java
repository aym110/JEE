package ma.cabinetdentaire.dto;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Acte;
import ma.cabinetdentaire.entity.enums.TypeConsultation;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConsultationDTO {
    private String acte;
    private Long dent;
    private Double prix;

}
