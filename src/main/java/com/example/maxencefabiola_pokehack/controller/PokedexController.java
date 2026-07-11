package com.example.maxencefabiola_pokehack.controller;

import com.example.maxencefabiola_pokehack.dao.PokemonDAO;
import com.example.maxencefabiola_pokehack.dao.PokemonDAOImpl;
import com.example.maxencefabiola_pokehack.model.Pokemon;
import com.example.maxencefabiola_pokehack.service.PokeApiService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.util.List;

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
    private Button captureButton;

    @FXML
    private Button deleteButton;

    private Pokemon pokemonActuel;

    private final PokeApiService pokeApiService = new PokeApiService();
    private final PokemonDAO pokemonDAO = new PokemonDAOImpl();

    @FXML
    private void initialize() {
        captureButton.setDisable(true);
        deleteButton.setDisable(true);

        pokemonList.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, ancienPokemon, nouveauPokemon) ->
                        deleteButton.setDisable(nouveauPokemon == null)
                );

        actualiserListe();
    }

    @FXML
    private void searchPokemon() {
        String recherche = searchField.getText().trim();

        if (recherche.isEmpty()) {
            messageLabel.setText("Veuillez entrer un nom ou un ID.");
            return;
        }

        captureButton.setDisable(true);
        pokemonActuel = null;
        messageLabel.setText("Recherche en cours...");

        Task<Pokemon> rechercheTask = new Task<>() {
            @Override
            protected Pokemon call() throws Exception {
                return pokeApiService.rechercher(recherche);
            }
        };

        rechercheTask.setOnSucceeded(event -> {
            Pokemon pokemon = rechercheTask.getValue();

            if (pokemon == null) {
                messageLabel.setText("Pokémon introuvable.");
                reinitialiserAffichage();
                return;
            }

            pokemonActuel = pokemon;
            afficherPokemon(pokemon);
            captureButton.setDisable(false);
            messageLabel.setText("Pokémon trouvé !");
        });

        rechercheTask.setOnFailed(event -> {
            pokemonActuel = null;
            captureButton.setDisable(true);
            messageLabel.setText("Erreur pendant la recherche.");
            rechercheTask.getException().printStackTrace();
        });

        Thread thread = new Thread(rechercheTask);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void capturePokemon() {
        if (pokemonActuel == null) {
            messageLabel.setText("Aucun Pokémon à capturer.");
            return;
        }

        try {
            pokemonDAO.upsert(pokemonActuel);
            messageLabel.setText("Pokémon capturé !");
            actualiserListe();
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors de la capture.");
            e.printStackTrace();
        }
    }

    @FXML
    private void deletePokemon() {
        String selection = pokemonList.getSelectionModel().getSelectedItem();

        if (selection == null) {
            messageLabel.setText("Sélectionnez un Pokémon.");
            return;
        }

        try {
            int numeroPokedex = Integer.parseInt(selection.split(" - ")[0]);

            pokemonDAO.deleteById(numeroPokedex);
            messageLabel.setText("Pokémon supprimé.");
            actualiserListe();
        } catch (SQLException | NumberFormatException e) {
            messageLabel.setText("Erreur lors de la suppression.");
            e.printStackTrace();
        }
    }

    private void actualiserListe() {
        try {
            List<Pokemon> pokemons = pokemonDAO.findAll();

            pokemonList.getItems().clear();

            for (Pokemon pokemon : pokemons) {
                pokemonList.getItems().add(
                        pokemon.getNumeroPokedex()
                                + " - "
                                + capitaliser(pokemon.getNom())
                );
            }

            int total = pokemons.size();

            if (total > 1) {
                counterLabel.setText(total + " Pokémon capturés");
            } else {
                counterLabel.setText(total + " Pokémon capturé");
            }

            deleteButton.setDisable(true);
        } catch (SQLException e) {
            messageLabel.setText("Impossible de charger les Pokémon.");
            e.printStackTrace();
        }
    }

    private void afficherPokemon(Pokemon pokemon) {
        nameLabel.setText(capitaliser(pokemon.getNom()));
        idLabel.setText("ID : " + pokemon.getNumeroPokedex());

        String types = pokemon.getTypePrincipal();

        if (pokemon.getTypeSecondaire() != null
                && !pokemon.getTypeSecondaire().isBlank()) {
            types += " / " + pokemon.getTypeSecondaire();
        }

        typeLabel.setText("Type : " + types);
        heightLabel.setText("PV : " + pokemon.getHp());
        weightLabel.setText("Vitesse : " + pokemon.getVitesse());

        if (pokemon.getSpriteUrl() != null
                && !pokemon.getSpriteUrl().isBlank()) {
            Image image = new Image(pokemon.getSpriteUrl(), true);
            pokemonImage.setImage(image);
        }
    }

    private void reinitialiserAffichage() {
        pokemonImage.setImage(null);
        nameLabel.setText("Nom");
        idLabel.setText("ID : ---");
        typeLabel.setText("Type : ---");
        heightLabel.setText("PV : ---");
        weightLabel.setText("Vitesse : ---");
        pokemonActuel = null;
        captureButton.setDisable(true);
    }

    private String capitaliser(String texte) {
        if (texte == null || texte.isBlank()) {
            return "";
        }

        return texte.substring(0, 1).toUpperCase()
                + texte.substring(1).toLowerCase();
    }
}

