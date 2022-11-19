package com.example.bangladeshrailway;

public class CoachViewModel {
    String tittle,url,broad,meter;

    public CoachViewModel(String tittle, String url, String broad, String meter) {
        this.tittle = tittle;
        this.url = url;
        this.broad = broad;
        this.meter = meter;
    }

    public String getBroad() {
        return broad;
    }

    public void setBroad(String broad) {
        this.broad = broad;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CoachViewModel() {
    }
}
