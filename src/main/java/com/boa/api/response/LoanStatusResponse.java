package com.boa.api.response;

public class LoanStatusResponse extends GenericResponse{
    private String reference;
    private String status;


    public LoanStatusResponse() {
    }

    public LoanStatusResponse(String reference, String status) {
        this.reference = reference;
        this.status = status;
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

    public LoanStatusResponse reference(String reference) {
        this.reference = reference;
        return this;
    }

    public LoanStatusResponse status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " reference='" + getReference() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
