package com.example.maxencefabiola_pokehack.dao;

import com.example.maxencefabiola_pokehack.model.Pokemon;
import com.example.maxencefabiola_pokehack.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImpl implements PokemonDAO {

    @Override
    public void upsert(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO pokemon " +
                "(numero_pokedex, nom, type_principal, type_secondaire, hp, attaque, defense, attaque_speciale, defense_speciale, vitesse, sprite_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (numero_pokedex) DO UPDATE SET " +
                "nom = EXCLUDED.nom, " +
                "type_principal = EXCLUDED.type_principal, " +
                "type_secondaire = EXCLUDED.type_secondaire, " +
                "hp = EXCLUDED.hp, " +
                "attaque = EXCLUDED.attaque, " +
                "defense = EXCLUDED.defense, " +
                "attaque_speciale = EXCLUDED.attaque_speciale, " +
                "defense_speciale = EXCLUDED.defense_speciale, " +
                "vitesse = EXCLUDED.vitesse, " +
                "sprite_url = EXCLUDED.sprite_url";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pokemon.getNumeroPokedex());
            stmt.setString(2, pokemon.getNom());
            stmt.setString(3, pokemon.getTypePrincipal());
            stmt.setString(4, pokemon.getTypeSecondaire());
            stmt.setInt(5, pokemon.getHp());
            stmt.setInt(6, pokemon.getAttaque());
            stmt.setInt(7, pokemon.getDefense());
            stmt.setInt(8, pokemon.getAttaqueSpeciale());
            stmt.setInt(9, pokemon.getDefenseSpeciale());
            stmt.setInt(10, pokemon.getVitesse());
            stmt.setString(11, pokemon.getSpriteUrl());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Pokemon> findAll() throws SQLException {
        List<Pokemon> pokemons = new ArrayList<>();
        String sql = "SELECT * FROM pokemon ORDER BY numero_pokedex ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setNumeroPokedex(rs.getInt("numero_pokedex"));
                p.setNom(rs.getString("nom"));
                p.setTypePrincipal(rs.getString("type_principal"));
                p.setTypeSecondaire(rs.getString("type_secondaire"));
                p.setHp(rs.getInt("hp"));
                p.setAttaque(rs.getInt("attaque"));
                p.setDefense(rs.getInt("defense"));
                p.setAttaqueSpeciale(rs.getInt("attaque_speciale"));
                p.setDefenseSpeciale(rs.getInt("defense_speciale"));
                p.setVitesse(rs.getInt("vitesse"));
                p.setSpriteUrl(rs.getString("sprite_url"));
                pokemons.add(p);
            }
        }
        return pokemons;
    }

    @Override
    public void deleteById(int numeroPokedex) throws SQLException {
        String sql = "DELETE FROM pokemon WHERE numero_pokedex = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroPokedex);
            stmt.executeUpdate();
        }
    }
}