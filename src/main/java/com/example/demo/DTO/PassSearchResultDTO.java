package com.example.demo.DTO;

public class PassSearchResultDTO {
    private Integer passID;
    private String imageUrl;
    private String title;
    private Integer price;

    public Integer getpassID() {
        return passID;
    }

    public void setpassID(Integer passiD) {
        this.passID = passiD;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}


