package fr.ynov.m1.valleau_elien.bank_projectv2.modele;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_transaction;
    private Float montant;
    private Integer cpt_source;
    private Integer cpt_dest;
    private Date date;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "compte_associe")
    private Compte leSuperCompte;

    public Integer getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(Integer id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Integer getCpt_source() {
        return cpt_source;
    }

    public void setCpt_source(Integer cpt_source) {
        this.cpt_source = cpt_source;
    }

    public Integer getCpt_dest() {
        return cpt_dest;
    }

    public void setCpt_dest(Integer cpt_dest) {
        this.cpt_dest = cpt_dest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Compte getLeSuperCompte() {
        return leSuperCompte;
    }

    public void setLeSuperCompte(Compte leSuperCompte) {
        this.leSuperCompte = leSuperCompte;
    }
}
