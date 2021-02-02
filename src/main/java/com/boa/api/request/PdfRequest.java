package com.boa.api.request;

import java.util.List;

public class PdfRequest {
    private String titre1, titre2, titre3, prenom, nom;
    private List<Family> families;

    public PdfRequest() {
    }

    public PdfRequest(String titre1, String titre2, String titre3, String prenom, String nom, List<Family> families) {
        this.titre1 = titre1;
        this.titre2 = titre2;
        this.titre3 = titre3;
        this.prenom = prenom;
        this.nom = nom;
        this.families = families;
    }

    public String getTitre1() {
        return this.titre1;
    }

    public void setTitre1(String titre1) {
        this.titre1 = titre1;
    }

    public String getTitre2() {
        return this.titre2;
    }

    public void setTitre2(String titre2) {
        this.titre2 = titre2;
    }

    public String getTitre3() {
        return this.titre3;
    }

    public void setTitre3(String titre3) {
        this.titre3 = titre3;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Family> getFamilies() {
        return this.families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }

    public PdfRequest titre1(String titre1) {
        this.titre1 = titre1;
        return this;
    }

    public PdfRequest titre2(String titre2) {
        this.titre2 = titre2;
        return this;
    }

    public PdfRequest titre3(String titre3) {
        this.titre3 = titre3;
        return this;
    }

    public PdfRequest prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public PdfRequest nom(String nom) {
        this.nom = nom;
        return this;
    }

    public PdfRequest families(List<Family> families) {
        this.families = families;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " titre1='" + getTitre1() + "'" +
            ", titre2='" + getTitre2() + "'" +
            ", titre3='" + getTitre3() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", families='" + getFamilies() + "'" +
            "}";
    }
}