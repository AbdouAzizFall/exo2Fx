package com.example.fx1.repository;

import com.example.fx1.model.Personne;
import com.example.fx1.model.db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonneRepository {
    private Connection connection;

    public PersonneRepository() {
        this.connection = new db().getConnection();
    }

    //Insérer une personne
    public void insert(Personne personne){
        String sql = "INSERT INTO personne(nom, prenom,age) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, personne.getNom());
            preparedStatement.setString(2, personne.getPrenom());
            preparedStatement.setInt(3, personne.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Supprimer une personne
    public void delete(int id){
        String sql = "Delete from personne where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Afficher
    public ObservableList<Personne> allPersonne(){
        ObservableList<Personne> personnes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM personne";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
           ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            Personne personne = new Personne();
            personne.setId(result.getInt(1));
            personne.setNom(result.getString(2));
            personne.setPrenom(result.getString(3));
            personne.setAge(result.getInt(4));
            personnes.add(personne);
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }

    // Mis à jour
    public void updatePersonne(Personne personne){
        String sql = "Update personne set nom = ?, prenom = ?, age = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, personne.getNom());
            preparedStatement.setString(2, personne.getPrenom());
            preparedStatement.setInt(3, personne.getAge());
            preparedStatement.setInt(4, personne.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Rechercher
    public List<Personne> searchPersonne(String mot){
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM personne where nom LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,'%'+mot+'%');
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                Personne personne = new Personne();
                personne.setId(result.getInt(1));
                personne.setNom(result.getString(2));
                personne.setPrenom(result.getString(3));
                personne.setAge(result.getInt(4));
                personnes.add(personne);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }





}
