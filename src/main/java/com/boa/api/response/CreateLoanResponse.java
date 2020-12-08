package com.boa.api.response;

public class CreateLoanResponse extends GenericResponse{
    private String refIngec;

    public CreateLoanResponse() {
    }

    public CreateLoanResponse(String refIngec) {
        this.refIngec = refIngec;
    }

    public String getRefIngec() {
        return this.refIngec;
    }

    public void setRefIngec(String refIngec) {
        this.refIngec = refIngec;
    }

    public CreateLoanResponse refIngec(String refIngec) {
        this.refIngec = refIngec;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " refIngec='" + getRefIngec() + "'" +
            "}";
    }

}
