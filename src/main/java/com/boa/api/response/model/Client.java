package com.boa.api.response.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    
    
    private String civilite;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private List<Account>accounts = new ArrayList<>();


    public Client() {
    }

    public Client(String civilite, String lastName, String firstName, String phoneNumber, List<Account> accounts) {
        this.civilite = civilite;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Client phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", accounts='" + getAccounts() + "'" +
            "}";
    }
    

}
