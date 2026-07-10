package com.example.maxencefabiola_pokehack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class PokedexController {

    @FXML
    private TextField searchField;

    @FXML
    private ImageView pokemonImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private ListView<String> pokemonList;

    @FXML
    private Label counterLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private void searchPokemon() {

        String recherche = searchField.getText();

        System.out.println("Recherche : " + recherche);

        // Le backend sera branché ici plus tard.
    }
}
