package com.boa.api.response;


import com.boa.api.response.model.Client;

public class SearchClientResponse extends GenericResponse{

    private Client client;
    private String rCode;


    public SearchClientResponse() {
    }

    public SearchClientResponse(Client client, String rCode) {
        this.client = client;
        this.rCode = rCode;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getRCode() {
        return this.rCode;
    }

    public void setRCode(String rCode) {
        this.rCode = rCode;
    }

    public SearchClientResponse client(Client client) {
        setClient(client);
        return this;
    }

    public SearchClientResponse rCode(String rCode) {
        setRCode(rCode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " client='" + getClient() + "'" +
            ", rCode='" + getRCode() + "'" +
            "}";
    }
    
   
}