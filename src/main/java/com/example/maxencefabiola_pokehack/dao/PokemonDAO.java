package com.example.maxencefabiola_pokehack.dao;

import com.example.maxencefabiola_pokehack.model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public interface PokemonDAO {

    void upsert(Pokemon pokemon) throws SQLException;

    List<Pokemon> findAll() throws SQLException;

    void deleteById(int numeroPokedex) throws SQLException;
}