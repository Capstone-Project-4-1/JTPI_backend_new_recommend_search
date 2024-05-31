package com.example.demo;
public class SearchParameters {
    private String searchQuery;
    private String departureCity;
    private String arrivalCity;
    private String transportType;
    private String cityNames;
    private Integer duration;


    public String getsearchQuery() {

        return searchQuery;
    }

    public void setsearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    // Getters and Setters
}
