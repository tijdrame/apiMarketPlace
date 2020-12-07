package com.boa.api.response;

public class OAuthResponse extends GenericResponse {
    private String userCode;

    public OAuthResponse() {
    }

    public OAuthResponse(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public OAuthResponse userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " userCode='" + getUserCode() + "'" +
            "}";
    }

}
