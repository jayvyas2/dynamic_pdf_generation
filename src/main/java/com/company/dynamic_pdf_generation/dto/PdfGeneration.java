package com.company.dynamic_pdf_generation.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PdfGeneration {

    private String seller;

    private String sellerAddress;

    @JsonProperty("sellerGstin")
    private String sellerGst;

    private String buyer;

    private String buyerAddress;

    @JsonProperty("buyerGstin")
    private String buyerGst;

    @JsonProperty("items")
    private List<Item> itemList;

    public PdfGeneration(String seller, String sellerAddress, String sellerGst, String buyer, String buyerAddress,
	    String buyerGst, List<Item> itemList) {
	super();
	this.seller = seller;
	this.sellerAddress = sellerAddress;
	this.sellerGst = sellerGst;
	this.buyer = buyer;
	this.buyerAddress = buyerAddress;
	this.buyerGst = buyerGst;
	this.itemList = itemList;
    }

    public PdfGeneration() {
	super();
    }

    public String getSeller() {
	return seller;
    }

    public void setSeller(String seller) {
	this.seller = seller;
    }

    public String getSellerAddress() {
	return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
	this.sellerAddress = sellerAddress;
    }

    public String getSellerGstin() {
	return sellerGst;
    }

    public void setSellerGstin(String sellerGst) {
	this.sellerGst = sellerGst;
    }

    public String getBuyer() {
	return buyer;
    }

    public void setBuyer(String buyer) {
	this.buyer = buyer;
    }

    public String getBuyerAddress() {
	return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
	this.buyerAddress = buyerAddress;
    }

    public String getBuyerGstin() {
	return buyerGst;
    }

    public void setBuyerGstin(String buyerGst) {
	this.buyerGst = buyerGst;
    }

    public List<Item> getItems() {
	return itemList;
    }

    public void setItems(List<Item> itemList) {
	this.itemList = itemList;
    }

}