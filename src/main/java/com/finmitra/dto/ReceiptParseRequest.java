package com.finmitra.dto;

public class ReceiptParseRequest {
    private String base64Image;
    private String mimeType;

    public ReceiptParseRequest() {
    }

    public ReceiptParseRequest(String base64Image, String mimeType) {
        this.base64Image = base64Image;
        this.mimeType = mimeType;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
