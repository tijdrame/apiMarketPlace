package com.boa.api.response;

public class CreateLoanResponse extends GenericResponse{
    private String refIngec;
    private String rCode;


    public CreateLoanResponse() {
    }

    public CreateLoanResponse(String refIngec, String rCode) {
        this.refIngec = refIngec;
        this.rCode = rCode;
    }

    public String getRefIngec() {
        return this.refIngec;
    }

    public void setRefIngec(String refIngec) {
        this.refIngec = refIngec;
    }

    public String getRCode() {
        return this.rCode;
    }

    public void setRCode(String rCode) {
        this.rCode = rCode;
    }

    public CreateLoanResponse refIngec(String refIngec) {
        setRefIngec(refIngec);
        return this;
    }

    public CreateLoanResponse rCode(String rCode) {
        setRCode(rCode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " refIngec='" + getRefIngec() + "'" +
            ", rCode='" + getRCode() + "'" +
            "}";
    }
    

}
