package com.medassi.etabscol;

import com.medassi.etabscol.metiers.Academie;
import com.medassi.etabscol.metiers.Datas;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvRegions.setItems(Datas.getInstance().getLesRegions());
        lvRegions.getSelectionModel().selectedItemProperty().addListener( (ol, oldRegion, newRegion) -> {
            lvAcademies.setItems(newRegion.getLesAcademies()) ;
        }  );
    }    
    
}
