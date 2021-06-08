package com.boa.api.response;

public class NotifyPickupResponse extends GenericResponse{
    private String rCode;

    public NotifyPickupResponse() {
    }

    public NotifyPickupResponse(String rCode) {
        this.rCode = rCode;
    }

    public String getRCode() {
        return this.rCode;
    }

    public void setRCode(String rCode) {
        this.rCode = rCode;
    }

    public NotifyPickupResponse rCode(String rCode) {
        setRCode(rCode);
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " rCode='" + getRCode() + "'" +
            "}";
    }
    
}
