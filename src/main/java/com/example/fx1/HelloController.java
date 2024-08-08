package com.example.fx1;

import com.example.fx1.model.Personne;
import com.example.fx1.repository.PersonneRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField cAge;

    @FXML
    private TextField cNom;

    @FXML
    private TextField cPrenom;

    private PersonneRepository personneRepository ;

    @FXML
    void addPersonne(ActionEvent event) {
        Personne personne = new Personne(cNom.getText(), cPrenom.getText(), Integer.parseInt(cAge.getText()));
        personneRepository.insert(personne);
        affiche();
        clear(event);
    }

    @FXML
    void clear(ActionEvent event) {
        cNom.setText("");
        cPrenom.setText("");
        cAge.setText("");

    }

    @FXML
    void deletePersonne(ActionEvent event) {
       int id = tablePersonne.getSelectionModel().getSelectedItem().getId();
       personneRepository.delete(id);
       affiche();
    }

    @FXML
    void savePersonne(ActionEvent event) {

    }

    @FXML
    void updatePersonne(ActionEvent event) {
        personneRepository.updatePersonne(new Personne(
                Integer.parseInt(colId.getText()),
                cNom.getText(),
                cPrenom.getText(),
                Integer.parseInt(cAge.getText())

        ));
        affiche();
        clear(event);

    }

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableView<Personne> tablePersonne;

    void affiche(){
        ObservableList<Personne> personnes = personneRepository.allPersonne();

        colId.setCellValueFactory(new PropertyValueFactory<>("id")); // Assurez-vous que "id" est bien un getter pour id
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom")); // Assurez-vous que "libelle" est bien un getter pour libelle
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom")); // Assurez-vous que "quantite" est bien un getter pour quantite
        colAge.setCellValueFactory(new PropertyValueFactory<>("age")); // Assurez-vous que "prix" est bien un getter pour prix

        tablePersonne.setItems(personnes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         personneRepository = new PersonneRepository();
         affiche();

    }



    public void chargement(javafx.scene.input.MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() ==2){
            Personne personne = tablePersonne.getSelectionModel().getSelectedItem();
            cNom.setText(personne.getNom());
            cPrenom.setText(String.valueOf(personne.getPrenom()));
            cAge.setText(String.valueOf(personne.getAge()));
            colId.setText(String.valueOf(personne.getId()));
        }
    }
}