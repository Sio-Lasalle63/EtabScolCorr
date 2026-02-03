package com.medassi.etabscol.metiers;

import javafx.collections.ObservableList;


public class Academie {
    private String code;
    private String libelle;
    private ObservableList<Departement> lesDepartements ;
    private Region laRegion ;

    public Academie(String code, String libelle,Region region) {
        this.code = code;
        this.libelle = libelle;
        this.laRegion = region ;
    }

    @Override
    public String toString() {
        return libelle ;
    }
    
    


}
