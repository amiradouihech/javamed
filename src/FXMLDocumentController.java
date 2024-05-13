/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import PastionMedicament.DAOPatient;
import PastionMedicament.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class FXMLDocumentController implements Initializable {
     ObservableList<Patient> observableList;
     @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, String> codePatColumn;
    @FXML
    private TableColumn<Patient, String> nomPatColumn;
    @FXML
    private TableColumn<Patient, String> telPatColumn;
    @FXML
    private Label label;
    @FXML
    private TextField codePat;
    @FXML
    private TextField nomPat;
    @FXML
    private TextField telPat;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       if (event.getSource() instanceof Button){
           Button clickedButton=(Button)event.getSource();
           switch(clickedButton.getId()){
               case "ajouter":
                    String code = codePat.getText();
                    String nom = nomPat.getText();
                    String tel = telPat.getText();
                    Patient newPatient = new Patient(code, nom, tel);
                   System.out.println("New Patient Added: " + newPatient);
                   break;
                case "modifier":
                      Patient patientSelectionne = tableView.getSelectionModel().getSelectedItem();
    
   
    if (patientSelectionne != null) {
      
        patientSelectionne.setCodePat(codePat.getText());
        patientSelectionne.setNomPat(nomPat.getText());
        patientSelectionne.setTelPat(telPat.getText());

       
        boolean modificationReussie = DAOPatient.modifier(patientSelectionne);

        if (modificationReussie) {
            
            tableView.refresh();

           
            System.out.println("Patient modifié avec succès.");
        } else {
           
            System.out.println("La modification du patient a échoué. Veuillez réessayer.");
        }
    } else {
       
        System.out.println("Veuillez sélectionner un patient à modifier.");
    }
    break;
                    case "supprimer":
    
    Patient patientSelectionnea = tableView.getSelectionModel().getSelectedItem();

   
    if (patientSelectionnea != null) {
      
        boolean suppressionReussie = DAOPatient.supprimer(patientSelectionnea);

        if (suppressionReussie) {
           
            tableView.getItems().remove(patientSelectionnea);

          
            System.out.println("Patient supprimé avec succès.");
        } else {
            
            System.out.println("La suppression du patient a échoué. Veuillez réessayer.");
        }
    } else {
    
        System.out.println("Veuillez sélectionner un patient à supprimer.");
    }
    break;
           }
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         observableList = FXCollections.observableArrayList();
    codePatColumn.setCellValueFactory(cellData -> {
        Patient patient = cellData.getValue();
        return new SimpleStringProperty(patient.getCodePat());
    });

    nomPatColumn.setCellValueFactory(cellData -> {
        Patient patient = cellData.getValue();
        return new SimpleStringProperty(patient.getNomPat());
    });

    telPatColumn.setCellValueFactory(cellData -> {
        Patient patient = cellData.getValue();
        return new SimpleStringProperty(patient.getTelPat());
    });

    
    ArrayList<Patient> patients = DAOPatient.lister();

   
    observableList.addAll(patients);


    tableView.setItems(observableList);
    
}

    @FXML
    private void handlePatientSelection(ActionEvent event) throws IOException {
        Patient patientSelectionne = tableView.getSelectionModel().getSelectedItem();
        if (patientSelectionne != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Maladies.fxml"));
            Parent root = loader.load();
            MaladiesController controller = loader.getController();
            controller.initialize(null, null);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Veuillez sélectionner un patient.");
        }
    }}
