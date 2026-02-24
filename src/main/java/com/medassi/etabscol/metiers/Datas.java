package com.medassi.etabscol.metiers;

import javafx.collections.ObservableList;

public class Datas {

    private static Datas instance = null;
    private static ObservableList<Region> lesRegions;
    private static ObservableList<Academie> lesAcademies;
    private static ObservableList<Departement> lesDepartements;
    private static ObservableList<Commune> lesCommunes;
    private static ObservableList<Etablissement> lesEtablissements;

    private Datas() {
        Datas.lesRegions = Db.getDatabase().extractRegionsFromDB();
        Datas.lesAcademies = Db.getDatabase().extractAcademiesFromDB();
        Datas.lesDepartements = Db.getDatabase().extractDepartementsFromDB();
        Datas.lesCommunes = Db.getDatabase().extractCommunesFromDB();
        Datas.lesEtablissements = Db.getDatabase().extractEtablissementsFromDB();
    }

    public static Datas getInstance() {
        if (Datas.instance == null) {
            Datas.instance = new Datas();
        }
        return Datas.instance;
    }

    public ObservableList<Region> getLesRegions() {
        return Datas.lesRegions;
    }

    public static Region getRegionByCode(String codeR) {
        for (Region r : Datas.lesRegions) {
            if (r.getCode().equals(codeR)) {
                return r;
            }
        }
        return null;
    }

    static Academie getAcademieByCode(String codeAca) {
        for (Academie a : Datas.lesAcademies) {
            if (a.getCode().equals(codeAca)) {
                return a;
            }
        }
        return null;
    }

    static Departement getDepartementByCode(String codeDep) {
        for (Departement d : Datas.lesDepartements) {
            if (d.getCode().equals(codeDep)) {
                return d;
            }
        }
        return null;
    }

    static Commune getCommuneByInsee(String insee) {
        for (Commune c : Datas.lesCommunes) {
            if (c.getInsee().equals(insee)) {
                return c;
            }
        }
        return null;
    }

}
