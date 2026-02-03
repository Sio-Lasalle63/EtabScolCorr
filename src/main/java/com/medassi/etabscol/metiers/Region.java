package com.medassi.etabscol.metiers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Region {

    private String code;
    private String libelle;
    private ObservableList<Academie> lesAcademies;

    public Region(String unCode, String unLibelle) {
        this.code = unCode;
        this.libelle = unLibelle;
        this.lesAcademies = FXCollections.observableArrayList();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return libelle;
    }

    public ObservableList<Academie> getLesAcademies() {
        return lesAcademies;
    }

}
