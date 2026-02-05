package com.medassi.etabscol.metiers;

import javafx.collections.ObservableList;

public class Datas {

    private static Datas instance = null;
    private static ObservableList<Region> lesRegions;
    private static ObservableList<Academie> lesAcademies;

    private Datas() {
        Datas.lesRegions = Db.getDatabase().extractRegionsFromDB();
        Datas.lesAcademies = Db.getDatabase().extractAcademiesFromDB();
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

}
