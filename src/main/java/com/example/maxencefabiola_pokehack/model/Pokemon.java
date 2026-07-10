package com.example.maxencefabiola_pokehack.model;

public class Pokemon {

    private int numeroPokedex;
    private String nom;
    private String typePrincipal;
    private String typeSecondaire;
    private int hp;
    private int attaque;
    private int defense;
    private int attaqueSpeciale;
    private int defenseSpeciale;
    private int vitesse;
    private String spriteUrl;

    public Pokemon() {
    }

    public Pokemon(int numeroPokedex, String nom, String typePrincipal, String typeSecondaire,
                   int hp, int attaque, int defense, int attaqueSpeciale, int defenseSpeciale,
                   int vitesse, String spriteUrl) {
        this.numeroPokedex = numeroPokedex;
        this.nom = nom;
        this.typePrincipal = typePrincipal;
        this.typeSecondaire = typeSecondaire;
        this.hp = hp;
        this.attaque = attaque;
        this.defense = defense;
        this.attaqueSpeciale = attaqueSpeciale;
        this.defenseSpeciale = defenseSpeciale;
        this.vitesse = vitesse;
        this.spriteUrl = spriteUrl;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypePrincipal() {
        return typePrincipal;
    }

    public void setTypePrincipal(String typePrincipal) {
        this.typePrincipal = typePrincipal;
    }

    public String getTypeSecondaire() {
        return typeSecondaire;
    }

    public void setTypeSecondaire(String typeSecondaire) {
        this.typeSecondaire = typeSecondaire;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttaqueSpeciale() {
        return attaqueSpeciale;
    }

    public void setAttaqueSpeciale(int attaqueSpeciale) {
        this.attaqueSpeciale = attaqueSpeciale;
    }

    public int getDefenseSpeciale() {
        return defenseSpeciale;
    }

    public void setDefenseSpeciale(int defenseSpeciale) {
        this.defenseSpeciale = defenseSpeciale;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "numeroPokedex=" + numeroPokedex +
                ", nom='" + nom + '\'' +
                ", typePrincipal='" + typePrincipal + '\'' +
                ", typeSecondaire='" + typeSecondaire + '\'' +
                ", hp=" + hp +
                '}';
    }
}