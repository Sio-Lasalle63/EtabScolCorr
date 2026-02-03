package com.medassi.etabscol.metiers;

import javafx.collections.ObservableList;

public class Datas {

    private static Datas instance = null;
    private static ObservableList<Region> lesRegions;
    private static ObservableList<Academie> lesAcademies;

    private Datas() {
        lesRegions = Db.getDatabase().extractRegionsFromDB();
        lesAcademies = Db.getDatabase().extractAcademiesFromDB();
    }

    public static Datas getInstance() {
        if (instance == null) {
            instance = new Datas();
        }
        return instance;
    }

    public ObservableList<Region> getLesRegions() {
        return lesRegions;
    }

    public static Region getRegionByCode(String codeR) {
        for (Region r : lesRegions) {
            if (r.getCode().equals(codeR)) {
                return r;
            }
        }
        return null;
    }

}
