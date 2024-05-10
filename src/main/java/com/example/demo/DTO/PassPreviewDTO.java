package com.example.demo.DTO;

public class PassPreviewDTO {
    private Integer passID;
    private String title;
    private String imageURL;

    public PassPreviewDTO() {
    }

    public PassPreviewDTO(Integer passID, String title, String imageURL) {
        this.passID = passID;
        this.title = title;
        this.imageURL = imageURL;
    }


    public Integer getpassID() {
        return passID;
    }
    public void setpassID(Integer passID) {
        this.passID = passID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    @Override
    public String toString() {
        return "PassPreviewDTO{" +
                "passid=" + passID +
                ", title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
