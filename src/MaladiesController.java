
import PastionMedicament.DAOMedicament;
import PastionMedicament.DAOPamed;
import PastionMedicament.Medicament;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class MaladiesController implements Initializable{
      @FXML
    private TableView<Medicament> medicamentsTable;
    @FXML
    private TableColumn<Medicament, String> codeMedColumn;
    @FXML
    private TableColumn<Medicament, String> nomMedColumn;
    @FXML
    private TableColumn<Medicament, Float> prixMedColumn;
    @FXML
    private TableColumn<Medicament, Integer> qteMedColumn;
    @FXML
    private TableColumn<Medicament, String> typeMedColumn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        codeMedColumn.setCellValueFactory(cellData -> cellData.getValue().codeMedProperty());
        nomMedColumn.setCellValueFactory(cellData -> cellData.getValue().nomMedProperty());
        prixMedColumn.setCellValueFactory(cellData -> cellData.getValue().prixMedProperty().asObject());
        qteMedColumn.setCellValueFactory(cellData -> cellData.getValue().qteMedProperty().asObject());
        typeMedColumn.setCellValueFactory(cellData -> cellData.getValue().typeMedProperty());

        afficherMedicaments("idPatient");
    }

    private void afficherMedicaments(String idPatient) {
        ArrayList<Medicament> medicaments = DAOPamed.getMedicamentsByPatientId(idPatient);
        medicamentsTable.getItems().addAll(medicaments);
    }
  
}

