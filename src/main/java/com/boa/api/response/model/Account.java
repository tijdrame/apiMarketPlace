package com.boa.api.response.model;

public class Account {
    
    private String accountNum;
    private String accountname;
    private String branchName;

    public Account() {
    }

    public String getAccountNum() {
        return this.accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Account(String accountNum, String accountname, String branchName) {
        this.accountNum = accountNum;
        this.accountname = accountname;
        this.branchName = branchName;
    }

    public String getAccountname() {
        return this.accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public Account accountname(String accountname) {
        this.accountname = accountname;
        return this;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Account accountNum(String accountNum) {
        this.accountNum = accountNum;
        return this;
    }

    

    public Account branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            ", accountNum='" + getAccountNum() + "'" +
            ", accountname='" + getAccountname() + "'" +
            ", branchName='" + getBranchName() + "'" +
            "}";
    }
}
