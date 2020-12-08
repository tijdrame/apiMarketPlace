package com.boa.api.request;

public class CreateLoanRequest {
    
    private String userCode;
    private Integer duration;
    private String client;
    private String accountNum ;
    private Double amount;
    private String docRef;
    private String cliEmployer;
    private String supplierCode;
    private String supplierName;
    private Double fees;
    private String country;
    private String langue;
    private String assureur;
    private Double assurAmount;


    public CreateLoanRequest() {
    }

    public CreateLoanRequest(String userCode, Integer duration, String client, String accountNum, Double amount, String docRef, String cliEmployer, String supplierCode, String supplierName, Double fees, String country, String langue, String assureur, Double assurAmount) {
        this.userCode = userCode;
        this.duration = duration;
        this.client = client;
        this.accountNum = accountNum;
        this.amount = amount;
        this.docRef = docRef;
        this.cliEmployer = cliEmployer;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.fees = fees;
        this.country = country;
        this.langue = langue;
        this.assureur = assureur;
        this.assurAmount = assurAmount;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAccountNum() {
        return this.accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDocRef() {
        return this.docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getCliEmployer() {
        return this.cliEmployer;
    }

    public void setCliEmployer(String cliEmployer) {
        this.cliEmployer = cliEmployer;
    }

    public String getSupplierCode() {
        return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getFees() {
        return this.fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getAssureur() {
        return this.assureur;
    }

    public void setAssureur(String assureur) {
        this.assureur = assureur;
    }

    public Double getAssurAmount() {
        return this.assurAmount;
    }

    public void setAssurAmount(Double assurAmount) {
        this.assurAmount = assurAmount;
    }

    public CreateLoanRequest userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public CreateLoanRequest duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public CreateLoanRequest client(String client) {
        this.client = client;
        return this;
    }

    public CreateLoanRequest accountNum(String accountNum) {
        this.accountNum = accountNum;
        return this;
    }

    public CreateLoanRequest amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public CreateLoanRequest docRef(String docRef) {
        this.docRef = docRef;
        return this;
    }

    public CreateLoanRequest cliEmployer(String cliEmployer) {
        this.cliEmployer = cliEmployer;
        return this;
    }

    public CreateLoanRequest supplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
        return this;
    }

    public CreateLoanRequest supplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public CreateLoanRequest fees(Double fees) {
        this.fees = fees;
        return this;
    }

    public CreateLoanRequest country(String country) {
        this.country = country;
        return this;
    }

    public CreateLoanRequest langue(String langue) {
        this.langue = langue;
        return this;
    }

    public CreateLoanRequest assureur(String assureur) {
        this.assureur = assureur;
        return this;
    }

    public CreateLoanRequest assurAmount(Double assurAmount) {
        this.assurAmount = assurAmount;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " userCode='" + getUserCode() + "'" +
            ", duration='" + getDuration() + "'" +
            ", client='" + getClient() + "'" +
            ", accountNum='" + getAccountNum() + "'" +
            ", amount='" + getAmount() + "'" +
            ", docRef='" + getDocRef() + "'" +
            ", cliEmployer='" + getCliEmployer() + "'" +
            ", supplierCode='" + getSupplierCode() + "'" +
            ", supplierName='" + getSupplierName() + "'" +
            ", fees='" + getFees() + "'" +
            ", country='" + getCountry() + "'" +
            ", langue='" + getLangue() + "'" +
            ", assureur='" + getAssureur() + "'" +
            ", assurAmount='" + getAssurAmount() + "'" +
            "}";
    }

    
    

}
