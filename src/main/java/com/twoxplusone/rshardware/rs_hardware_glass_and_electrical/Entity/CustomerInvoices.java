package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class CustomerInvoices {
    @Id
    private String id;
    private String invoiceId;
    private String name;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String gstIn;
    private String placeOfSupply;
    private String dueDate;
    private String deliveryNote;
    private String referenceNumber;
    private String buyerOrderNumber;
    private String dispatchDocNumber;
    private String dispatchedThrough;
    private String termsOfDelivery;
    private String paymentTerms;
    private String otherReferences;
    private String dated;
    private String deliveryNoteDate;
    private String destination;
    private List<Items> items = new ArrayList<>();

    public CustomerInvoices(String id, String invoiceId, String name, String email, String phoneNumber, String billingAddress, String gstIn, String placeOfSupply, String dueDate, String deliveryNote, String referenceNumber, String buyerOrderNumber, String dispatchDocNumber, String dispatchedThrough, String termsOfDelivery, String paymentTerms, String otherReferences, String dated, String deliveryNoteDate, String destination, List<Items> items) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.gstIn = gstIn;
        this.placeOfSupply = placeOfSupply;
        this.dueDate = dueDate;
        this.deliveryNote = deliveryNote;
        this.referenceNumber = referenceNumber;
        this.buyerOrderNumber = buyerOrderNumber;
        this.dispatchDocNumber = dispatchDocNumber;
        this.dispatchedThrough = dispatchedThrough;
        this.termsOfDelivery = termsOfDelivery;
        this.paymentTerms = paymentTerms;
        this.otherReferences = otherReferences;
        this.dated = dated;
        this.deliveryNoteDate = deliveryNoteDate;
        this.destination = destination;
        this.items = items;
    }

    public CustomerInvoices() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public String getPlaceOfSupply() {
        return placeOfSupply;
    }

    public void setPlaceOfSupply(String placeOfSupply) {
        this.placeOfSupply = placeOfSupply;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getBuyerOrderNumber() {
        return buyerOrderNumber;
    }

    public void setBuyerOrderNumber(String buyerOrderNumber) {
        this.buyerOrderNumber = buyerOrderNumber;
    }

    public String getDispatchDocNumber() {
        return dispatchDocNumber;
    }

    public void setDispatchDocNumber(String dispatchDocNumber) {
        this.dispatchDocNumber = dispatchDocNumber;
    }

    public String getDispatchedThrough() {
        return dispatchedThrough;
    }

    public void setDispatchedThrough(String dispatchedThrough) {
        this.dispatchedThrough = dispatchedThrough;
    }

    public String getTermsOfDelivery() {
        return termsOfDelivery;
    }

    public void setTermsOfDelivery(String termsOfDelivery) {
        this.termsOfDelivery = termsOfDelivery;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getOtherReferences() {
        return otherReferences;
    }

    public void setOtherReferences(String otherReferences) {
        this.otherReferences = otherReferences;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDeliveryNoteDate() {
        return deliveryNoteDate;
    }

    public void setDeliveryNoteDate(String deliveryNoteDate) {
        this.deliveryNoteDate = deliveryNoteDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
