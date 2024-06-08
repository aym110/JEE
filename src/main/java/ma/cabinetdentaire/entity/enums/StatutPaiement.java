package ma.cabinetdentaire.entity.enums;

public enum StatutPaiement {
    EN_ATTENTE,
    PAYE,
    INPAYE;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
