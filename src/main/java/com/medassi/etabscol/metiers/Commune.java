package com.medassi.etabscol.metiers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Commune {

    private String insee;
    private String libelle;
    private ObservableList<Etablissement> lesEtablissements;
    private Departement leDepartement;

    public Commune(String insee, String libelle, Departement leDepartement) {
        this.insee = insee;
        this.libelle = libelle;
        this.lesEtablissements = FXCollections.observableArrayList();
        this.leDepartement = leDepartement;
    }

    public String getInsee() {
        return insee;
    }

    public String getLibelle() {
        return libelle;
    }
    

    @Override
    public String toString() {
        return this.libelle;
    }

    public ObservableList<Etablissement> getLesEtablissements() {
        return lesEtablissements;
    }

   
}
