package com.boa.api.request;

import java.time.LocalDate;

public class NotifyPickupRequest {

    private String docRef;
    private String receiptNum;
    private LocalDate deliveryDate;
    private String deliveryAddress;
    private String deliveryUser;
    private String status;
    private String motif;
    private String langue;
    private String country;
    private String typePiece;
    private String numeroPiece;
    private LocalDate dateExpiration;


    public NotifyPickupRequest() {
    }

    public NotifyPickupRequest(String docRef, String receiptNum, LocalDate deliveryDate, String deliveryAddress, String deliveryUser, String status, String motif, String langue, String country, String typePiece, String numeroPiece, LocalDate dateExpiration) {
        this.docRef = docRef;
        this.receiptNum = receiptNum;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryUser = deliveryUser;
        this.status = status;
        this.motif = motif;
        this.langue = langue;
        this.country = country;
        this.typePiece = typePiece;
        this.numeroPiece = numeroPiece;
        this.dateExpiration = dateExpiration;
    }

    public String getDocRef() {
        return this.docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getReceiptNum() {
        return this.receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryUser() {
        return this.deliveryUser;
    }

    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotif() {
        return this.motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTypePiece() {
        return this.typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumeroPiece() {
        return this.numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public LocalDate getDateExpiration() {
        return this.dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public NotifyPickupRequest docRef(String docRef) {
        this.docRef = docRef;
        return this;
    }

    public NotifyPickupRequest receiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
        return this;
    }

    public NotifyPickupRequest deliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public NotifyPickupRequest deliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public NotifyPickupRequest deliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
        return this;
    }

    public NotifyPickupRequest status(String status) {
        this.status = status;
        return this;
    }

    public NotifyPickupRequest motif(String motif) {
        this.motif = motif;
        return this;
    }

    public NotifyPickupRequest langue(String langue) {
        this.langue = langue;
        return this;
    }

    public NotifyPickupRequest country(String country) {
        this.country = country;
        return this;
    }

    public NotifyPickupRequest typePiece(String typePiece) {
        this.typePiece = typePiece;
        return this;
    }

    public NotifyPickupRequest numeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
        return this;
    }

    public NotifyPickupRequest dateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " docRef='" + getDocRef() + "'" +
            ", receiptNum='" + getReceiptNum() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", deliveryUser='" + getDeliveryUser() + "'" +
            ", status='" + getStatus() + "'" +
            ", motif='" + getMotif() + "'" +
            ", langue='" + getLangue() + "'" +
            ", country='" + getCountry() + "'" +
            ", typePiece='" + getTypePiece() + "'" +
            ", numeroPiece='" + getNumeroPiece() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            "}";
    }
    

}
