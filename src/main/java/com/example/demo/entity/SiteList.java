package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "SiteList")
public class SiteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer siteId;

    @Column(length = 20, nullable = false)
    private String siteName;

    @Column(length = 100, nullable = false)
    private String url;

    // Constructors
    public SiteList() {}

    public SiteList(String siteName, String url) {
        this.siteName = siteName;
        this.url = url;
    }

    // Getters and Setters
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
