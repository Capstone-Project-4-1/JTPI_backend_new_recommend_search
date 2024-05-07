package com.example.demo.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CrawledPassData")
public class CrawledPassData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer crawledID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteID", nullable = false)
    private SiteList site;

    @Column(length = 20)
    private String title;

    @Column(length = 100)
    private String description;

    @Column
    private Integer price;

    @Column
    private Integer period;

    @Column(length = 100)
    private String transportType;

    @Column(length = 50)
    private String cityNames;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(length = 100)
    private String imageURL;

    // Constructors
    public CrawledPassData() {}

    public CrawledPassData(SiteList site, String title, String description, Integer price, Integer period, String transportType, String cityNames, LocalDateTime createAt, String imageURL) {
        this.site = site;
        this.title = title;
        this.description = description;
        this.price = price;
        this.period = period;
        this.transportType = transportType;
        this.cityNames = cityNames;
        this.createAt = createAt;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public Integer getCrawledId() {
        return crawledId;
    }

    public void setCrawledId(Integer crawledId) {
        this.crawledId = crawledId;
    }

    public SiteList getSite() {
        return site;
    }

    public void setSite(SiteList site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
}

