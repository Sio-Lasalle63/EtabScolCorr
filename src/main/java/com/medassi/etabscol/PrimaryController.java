package com.medassi.etabscol;

import com.medassi.etabscol.metiers.Academie;
import com.medassi.etabscol.metiers.Commune;
import com.medassi.etabscol.metiers.Datas;
import com.medassi.etabscol.metiers.Departement;
import com.medassi.etabscol.metiers.Etablissement;
import com.medassi.etabscol.metiers.Etat;
import com.medassi.etabscol.metiers.Region;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvRegions.setItems(Datas.getInstance().getLesRegions());
        if (!lvRegions.getItems().isEmpty()) {
            lvRegions.getSelectionModel().selectFirst();
        }
        lvRegions.getSelectionModel().selectedItemProperty().addListener((ol, oldRegion, newRegion) -> {
            if (newRegion != null) {
                lvAcademies.setItems(newRegion.getLesAcademies());
                if (!lvAcademies.getItems().isEmpty()) {
                    lvAcademies.getSelectionModel().selectFirst();
                }
            }
        });
        lvDepartements.getSelectionModel().selectedItemProperty().addListener((ol, oldDepartement, newDepartement) -> {
            if (newDepartement != null) {
               /* lvAcademies.setItems(newDepartement.getLesAcademies());
                if (!lvAcademies.getItems().isEmpty()) {
                    lvAcademies.getSelectionModel().selectFirst();
                }*/
            }
            
            
            
        });
        
        
        String s = "A_FERMER" ;
        Etat e = Etat.valueOf(s) ;
    }

}
