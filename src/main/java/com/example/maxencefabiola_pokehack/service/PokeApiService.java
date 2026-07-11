package com.example.maxencefabiola_pokehack.service;

import com.example.maxencefabiola_pokehack.model.Pokemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class PokeApiService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private static final Map<String, String> TRADUCTION_TYPES = new HashMap<>();
    static {
        TRADUCTION_TYPES.put("normal", "NORMAL");
        TRADUCTION_TYPES.put("fire", "FEU");
        TRADUCTION_TYPES.put("water", "EAU");
        TRADUCTION_TYPES.put("grass", "PLANTE");
        TRADUCTION_TYPES.put("electric", "ELECTRIK");
        TRADUCTION_TYPES.put("ice", "GLACE");
        TRADUCTION_TYPES.put("fighting", "COMBAT");
        TRADUCTION_TYPES.put("poison", "POISON");
        TRADUCTION_TYPES.put("ground", "SOL");
        TRADUCTION_TYPES.put("flying", "VOL");
        TRADUCTION_TYPES.put("psychic", "PSY");
        TRADUCTION_TYPES.put("bug", "INSECTE");
        TRADUCTION_TYPES.put("rock", "ROCHE");
        TRADUCTION_TYPES.put("ghost", "SPECTRE");
        TRADUCTION_TYPES.put("dragon", "DRAGON");
        TRADUCTION_TYPES.put("dark", "TENEBRES");
        TRADUCTION_TYPES.put("steel", "ACIER");
        TRADUCTION_TYPES.put("fairy", "FEE");
    }

    public Pokemon rechercher(String nom) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + nom.toLowerCase().trim()))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            return null;
        }

        JsonNode racine = mapper.readTree(response.body());
        return construirePokemon(racine);
    }

    private Pokemon construirePokemon(JsonNode racine) {
        Pokemon pokemon = new Pokemon();

        pokemon.setNumeroPokedex(racine.get("id").asInt());
        pokemon.setNom(racine.get("name").asText());

        JsonNode types = racine.get("types");
        String typePrincipalAnglais = types.get(0).get("type").get("name").asText();
        pokemon.setTypePrincipal(TRADUCTION_TYPES.getOrDefault(typePrincipalAnglais, "NORMAL"));

        if (types.size() > 1) {
            String typeSecondaireAnglais = types.get(1).get("type").get("name").asText();
            pokemon.setTypeSecondaire(TRADUCTION_TYPES.getOrDefault(typeSecondaireAnglais, null));
        } else {
            pokemon.setTypeSecondaire(null);
        }

        JsonNode stats = racine.get("stats");
        for (JsonNode stat : stats) {
            String nomStat = stat.get("stat").get("name").asText();
            int valeur = stat.get("base_stat").asInt();

            switch (nomStat) {
                case "hp" -> pokemon.setHp(valeur);
                case "attack" -> pokemon.setAttaque(valeur);
                case "defense" -> pokemon.setDefense(valeur);
                case "special-attack" -> pokemon.setAttaqueSpeciale(valeur);
                case "special-defense" -> pokemon.setDefenseSpeciale(valeur);
                case "speed" -> pokemon.setVitesse(valeur);
            }
        }

        pokemon.setSpriteUrl(racine.get("sprites").get("front_default").asText());

        return pokemon;
    }
}