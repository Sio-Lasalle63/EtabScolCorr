package com.medassi.etabscol.metiers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Departement {

    private String code;
    private String libelle;
    private Academie lAcademie;
    private ObservableList<Commune> lesCommunes;

    public Departement(String code, String libelle, Academie lAcademie) {
        this.code = code;
        this.libelle = libelle;
        this.lAcademie = lAcademie;
        this.lesCommunes = FXCollections.observableArrayList();
    }

    public ObservableList<Commune> getLesCommunes() {
        return lesCommunes;
    }

    @Override
    public String toString() {
        return this.libelle;
    }

    public String getCode() {
        return code;
    }

}
