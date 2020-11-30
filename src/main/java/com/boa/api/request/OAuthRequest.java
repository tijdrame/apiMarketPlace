package com.boa.api.request;

public class OAuthRequest {
    private String login;
    private String password;
    private String country;
    private String langue;

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

    public OAuthRequest(String login, String password, String country, String langue) {
        this.login = login;
        this.password = password;
        this.country = country;
        this.langue = langue;
    }

    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public OAuthRequest langue(String langue) {
        this.langue = langue;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", country='" + getCountry() + "'" +
            ", langue='" + getLangue() + "'" +
            "}";
    }

}
