package com.medassi.etabscol.metiers;

import com.medassi.etabscol.Config;
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
            baseMariadb = new Db(Config.host, Config.dbname, Config.login, Config.password);
        }
        return baseMariadb;
    }

    public ObservableList<Region> extractRegionsFromDB() {
        ObservableList<Region> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Region order by libelle");
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

    public ObservableList<Academie> extractAcademiesFromDB() {
        ObservableList<Academie> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Academie order by libelle");
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

    public ObservableList<Departement> extractDepartementsFromDB() {
        ObservableList<Departement> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Departement order by libelle");
            while (rs.next()) {
                String code = rs.getString("code");
                String libelle = rs.getString("libelle");
                String codeAca = rs.getString("academie_code");
                Academie aca = Datas.getAcademieByCode(codeAca);
                Departement item = new Departement(code, libelle, aca);
                aca.getLesDepartements().add(item);
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Commune> extractCommunesFromDB() {
        ObservableList<Commune> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Commune order by libelle");
            while (rs.next()) {
                String insee = rs.getString("insee");
                String libelle = rs.getString("libelle");
                String codeDep = rs.getString("departement_code");
                Departement dep = Datas.getDepartementByCode(codeDep);
                Commune item = new Commune(insee, libelle, dep);
                dep.getLesCommunes().add(item);
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Etablissement> extractEtablissementsFromDB() {
        ObservableList<Etablissement> list = FXCollections.observableArrayList();
        try {
            ResultSet rs;
            rs = statement.executeQuery("Select * from Etablissement order by libelle");
            while (rs.next()) {
                String uai = rs.getString("uai");
                String libelle = rs.getString("libelle");
                String adr = rs.getString("adr");
                String cp = rs.getString("cp");
                String lieuDit = rs.getString("lieu_dit");
                String insee = rs.getString("commune_insee");
                String statut = rs.getString("public_prive");
                String etat = rs.getString("etat_code");
                Commune com = Datas.getCommuneByInsee(insee);
                Etablissement item = new Etablissement(uai, libelle, com);
                item.setAdr(adr);
                item.setCp(cp);
                item.setLieu_dit(lieuDit);
                Statut enumStatut = ("Privé".equals(statut)) ? Statut.PRIVE : Statut.PUBLIC ;
                item.setStatus(enumStatut);
                com.getLesEtablissements().add(item);
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
