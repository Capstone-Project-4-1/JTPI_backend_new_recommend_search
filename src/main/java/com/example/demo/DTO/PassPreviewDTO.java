package com.example.demo.DTO;

public class PassPreviewDTO {
    private Integer passid;
    private String title;
    private String imageURL;

    public PassPreviewDTO() {
    }

    public PassPreviewDTO(Integer passid, String title, String imageURL) {
        this.passid = passid;
        this.title = title;
        this.imageURL = imageURL;
    }


    public Integer getId() {
        return passid;
    }
    public void setpassId(Integer passid) {
        this.passid = passid;
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
                "passid=" + passid +
                ", title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
