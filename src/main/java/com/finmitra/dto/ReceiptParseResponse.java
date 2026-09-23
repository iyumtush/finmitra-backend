package com.finmitra.dto;

import java.math.BigDecimal;

public class ReceiptParseResponse {
    private String merchant;
    private String note;
    private BigDecimal amount;
    private String category;
    private String date;
    private String type;

    public ReceiptParseResponse() {
    }

    public ReceiptParseResponse(String merchant, String note, BigDecimal amount, String category, String date, String type) {
        this.merchant = merchant;
        this.note = note;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
