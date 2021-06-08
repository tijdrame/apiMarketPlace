package com.boa.api.response;

public class LoanStatusResponse extends GenericResponse{
    private String reference;
    private String status;
    private String rCode;


   

    public LoanStatusResponse() {
    }

    public LoanStatusResponse(String reference, String status, String rCode) {
        this.reference = reference;
        this.status = status;
        this.rCode = rCode;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRCode() {
        return this.rCode;
    }

    public void setRCode(String rCode) {
        this.rCode = rCode;
    }

    public LoanStatusResponse reference(String reference) {
        setReference(reference);
        return this;
    }

    public LoanStatusResponse status(String status) {
        setStatus(status);
        return this;
    }

    public LoanStatusResponse rCode(String rCode) {
        setRCode(rCode);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " reference='" + getReference() + "'" +
            ", status='" + getStatus() + "'" +
            ", rCode='" + getRCode() + "'" +
            "}";
    }

}
