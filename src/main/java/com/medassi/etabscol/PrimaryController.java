package com.medassi.etabscol;

import com.medassi.etabscol.metiers.Academie;
import com.medassi.etabscol.metiers.Commune;
import com.medassi.etabscol.metiers.Datas;
import com.medassi.etabscol.metiers.Departement;
import com.medassi.etabscol.metiers.Etablissement;
import com.medassi.etabscol.metiers.Etat;
import com.medassi.etabscol.metiers.Region;
import com.medassi.etabscol.metiers.Statut;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PrimaryController implements Initializable {

    @FXML
    private ListView<Region> lvRegions;
    @FXML
    private ListView<Academie> lvAcademies;
    @FXML
    private ListView<Departement> lvDepartements;
    @FXML
    private ListView<Commune> lvCommunes;
    @FXML
    private ListView<Etablissement> lvEtablissements;
    @FXML
    private Label lUaiEtab;
    @FXML
    private Label lLibelleEtab;
    @FXML
    private Label lAdr;
    @FXML
    private CheckBox cbStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lvRegions.getSelectionModel().selectedItemProperty().addListener(
                (ol, oldItem, newItem) -> changedRegion(newItem)
        );
        lvAcademies.getSelectionModel().selectedItemProperty().addListener(
                (ol, oldItem, newItem) -> changedAcademie(newItem)
        );
        lvDepartements.getSelectionModel().selectedItemProperty().addListener(
                (ol, oldItem, newItem) -> changedDepartement(newItem)
        );
        lvCommunes.getSelectionModel().selectedItemProperty().addListener(
                (ol, oldItem, newItem) -> changedCommune(newItem)
        );
             lvEtablissements.getSelectionModel().selectedItemProperty().addListener(
                (ol, oldItem, newItem) -> changedEtablissement(newItem)
        );
        
        lvRegions.setItems(Datas.getInstance().getLesRegions());
        lvRegions.getSelectionModel().selectFirst();
      
    }

    private void changedRegion(Region newRegion) {
        if (newRegion != null) {
            lvAcademies.setItems(newRegion.getLesAcademies());
            if (!lvAcademies.getItems().isEmpty()) {
                lvAcademies.getSelectionModel().selectFirst();
            }
        }
    }

    private void changedAcademie(Academie newAcademie) {
        if (newAcademie != null) {
            ObservableList<Departement> lesDepartements = newAcademie.getLesDepartements();
            lvDepartements.setItems(lesDepartements);
            if (!lesDepartements.isEmpty()) {
                lvDepartements.getSelectionModel().selectFirst();
            }
        }
    }

    private void changedDepartement(Departement newO) {
        if (newO != null) {
            ObservableList<Commune> lesCommunes = newO.getLesCommunes();
            lvCommunes.setItems(lesCommunes);
            if (!lesCommunes.isEmpty()) {
                lvCommunes.getSelectionModel().selectFirst();
            }
        }
    }

    private void changedCommune(Commune newItem) {
         if (newItem != null) {
            ObservableList<Etablissement> lesEtablissements = newItem.getLesEtablissements();
            lvEtablissements.setItems(lesEtablissements);
            if (!lesEtablissements.isEmpty()) {
                lvEtablissements.getSelectionModel().selectFirst();
            }
        }
    }

    private void changedEtablissement(Etablissement newItem) {
       if (newItem != null) {
           lUaiEtab.setText("UAI: "+newItem.getUai()); 
           lLibelleEtab.setText(newItem.getLibelle());
           lAdr.setText(newItem.getAdrComp());
           cbStatus.setSelected(newItem.getStatus()==Statut.PUBLIC);
        }
    }

}
