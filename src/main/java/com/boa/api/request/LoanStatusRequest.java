package com.boa.api.request;

public class LoanStatusRequest {
    
    private String reference;
    private String status;

    public LoanStatusRequest() {
    }

    public LoanStatusRequest(String reference, String status) {
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

    public LoanStatusRequest reference(String reference) {
        this.reference = reference;
        return this;
    }

    public LoanStatusRequest status(String status) {
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
