package fr.ynov.m1.valleau_elien.bank_projectv2.modele;


//pourquoi pas faire un enum
public class Typecpt {
    private Integer id_typecpt;
    private String libelle;
    private Float taux;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getTaux() {
        return taux;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public Integer getId_typecpt() {
        return id_typecpt;
    }

    public void setId_typecpt(Integer id_typecpt) {
        this.id_typecpt = id_typecpt;
    }
}
