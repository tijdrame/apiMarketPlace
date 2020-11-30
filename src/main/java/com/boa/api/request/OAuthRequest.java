package com.boa.api.request;

public class OAuthRequest {
    private String login;
    private String password;
    private String country;

    public OAuthRequest() {
    }

    public OAuthRequest(String login, String password, String country) {
        this.login = login;
        this.password = password;
        this.country = country;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public OAuthRequest login(String login) {
        this.login = login;
        return this;
    }

    public OAuthRequest password(String password) {
        this.password = password;
        return this;
    }

    public OAuthRequest country(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }

}
