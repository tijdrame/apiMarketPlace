package com.boa.api.response.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    
    
    private String civilite;
    private String lastName;
    private String firstName;
    private String telephone;
    private List<Account>accounts = new ArrayList<>();

    public Client() {
    }

    public Client(String civilite, String lastName, String firstName, String telephone, List<Account> accounts) {
        this.civilite = civilite;
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.accounts = accounts;
    }

    public String getCivilite() {
        return this.civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Client civilite(String civilite) {
        this.civilite = civilite;
        return this;
    }

    public Client lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Client firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Client accounts(List<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " civilite='" + getCivilite() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", accounts='" + getAccounts() + "'" +
            "}";
    }

}
