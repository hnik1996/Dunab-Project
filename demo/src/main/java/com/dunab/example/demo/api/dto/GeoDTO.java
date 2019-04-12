package com.dunab.example.demo.api.dto;

public class GeoDTO {

    private Long id;

    private Double latitude;

    private Double longitude;

    private Double radios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadios() {
        return radios;
    }

    public void setRadios(Double radios) {
        this.radios = radios;
    }
}
