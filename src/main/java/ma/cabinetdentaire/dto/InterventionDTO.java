package ma.cabinetdentaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Acte;
import ma.cabinetdentaire.entity.enums.CategorieActe;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionDTO {
    CategorieActe categorieActe;
    Double prixDebase;
    Long dent;
    Double prixPatient;
}
