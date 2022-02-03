package cous.stic4.scolariteapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (length = 20)
    private String libelle;
    @Column (length = 10)
    private int montant_inscription;
    @Column (length = 10)
    private int mensualite;
    @Column (length = 10)
    private int autre_frais;
    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private List<Inscription> inscriptions;

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontant_inscription() {
        return montant_inscription;
    }

    public void setMontant_inscription(int montant_inscription) {
        this.montant_inscription = montant_inscription;
    }

    public int getMensualite() {
        return mensualite;
    }

    public void setMensualite(int mensualite) {
        this.mensualite = mensualite;
    }

    public int getAutre_frais() {
        return autre_frais;
    }

    public void setAutre_frais(int autre_frais) {
        this.autre_frais = autre_frais;
    }
}
