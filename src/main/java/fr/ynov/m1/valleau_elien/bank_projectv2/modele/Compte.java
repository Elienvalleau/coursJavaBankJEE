package fr.ynov.m1.valleau_elien.bank_projectv2.modele;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_compte;
    private Integer typecpt;
    private Float solde;
    private Date date_creation;
    @OneToMany(mappedBy = "leSuperCompte", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new LinkedHashSet<Transaction>();
    @ManyToOne
    @JoinColumn(name = "utilisateur_associe")
    private Utilisateur robert;

    public Integer getId_compte() {
        return id_compte;
    }

    public void setId_compte(Integer id_compte) {
        this.id_compte = id_compte;
    }

    public Integer getTypecpt() {
        return typecpt;
    }

    public void setTypecpt(Integer typecpt) {
        this.typecpt = typecpt;
    }

    public Float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Utilisateur getRobert() {
        return robert;
    }

    public void setRobert(Utilisateur robert) {
        this.robert = robert;
    }

    public JsonObject soldeToJson() {
        return Json.createObjectBuilder()
                .add("solde", this.getSolde().toString())
                .build();
    }
}
