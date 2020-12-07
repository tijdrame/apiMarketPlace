package com.boa.api.request;

public class SearchClientRequest {
    
    private String client;
    private String userCode;
    private String country;
    private String langue;

    public SearchClientRequest() {
    }

    public SearchClientRequest(String client, String userCode, String country, String langue) {
        this.client = client;
        this.userCode = userCode;
        this.country = country;
        this.langue = langue;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public SearchClientRequest client(String client) {
        this.client = client;
        return this;
    }

    public SearchClientRequest userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public SearchClientRequest country(String country) {
        this.country = country;
        return this;
    }

    public SearchClientRequest langue(String langue) {
        this.langue = langue;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " client='" + getClient() + "'" +
            ", userCode='" + getUserCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", langue='" + getLangue() + "'" +
            "}";
    }

}
