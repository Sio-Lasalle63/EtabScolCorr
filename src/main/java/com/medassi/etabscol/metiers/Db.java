package com.medassi.etabscol.metiers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Db {

    private static Db baseMariadb = null;
    private Statement statement;

    private Db(String adresse, String nomBase, String login, String mdp) {
        try {
            String chaineConnexion = "jdbc:mariadb://" + adresse + "/" + nomBase;
            Connection connection
                    = DriverManager.getConnection(chaineConnexion, login, mdp);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Db getDatabase() {
        if (baseMariadb == null) {
            baseMariadb = new Db("192.168.153.10:3306", "etab_scol_fr", "sio", "sio");
        }
        return baseMariadb;
    }

    public ObservableList<Academie> extractAcademiesFromDB() {
        ObservableList<Academie> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Academie");
            while (rs.next()) {
                String code = rs.getString("code");
                String libelle = rs.getString("libelle");
                String codeRegion = rs.getString("region_code");
                Region laRegion = Datas.getRegionByCode(codeRegion);
                Academie item = new Academie(code, libelle, laRegion);
                laRegion.getLesAcademies().add(item);
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Region> extractRegionsFromDB() {
        ObservableList<Region> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Region");
            while (rs.next()) {
                String code = rs.getString("code");
                String libelle = rs.getString("libelle");
                Region item = new Region(code, libelle);
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    void blabla() {
        System.out.println("Blabla");
    }

}
