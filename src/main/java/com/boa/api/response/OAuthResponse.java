package com.boa.api.response;

public class OAuthResponse extends GenericResponse {
    private String userCode;
    private String rCode;


    public OAuthResponse() {
    }

    public OAuthResponse(String userCode, String rCode) {
        this.userCode = userCode;
        this.rCode = rCode;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRCode() {
        return this.rCode;
    }

    public void setRCode(String rCode) {
        this.rCode = rCode;
    }

    public OAuthResponse userCode(String userCode) {
        setUserCode(userCode);
        return this;
    }

    public OAuthResponse rCode(String rCode) {
        setRCode(rCode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " userCode='" + getUserCode() + "'" +
            ", rCode='" + getRCode() + "'" +
            "}";
    }
   

}
