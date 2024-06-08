package ma.cabinetdentaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Patient;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierDTO {
    Patient patient;
    List<ConsultationDTO> consultationDTOList;
}
