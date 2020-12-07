package com.boa.api.response;


import com.boa.api.response.model.Client;

public class SearchClientResponse extends GenericResponse{

    private Client client;

    public SearchClientResponse() {
    }

    public SearchClientResponse(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SearchClientResponse client(Client client) {
        this.client = client;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " client='" + getClient() + "'" +
            "}";
    }
   
}