package com.example.bangladeshrailway;

public class ModelTrack {
    String code,tittle,route;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public ModelTrack(String code, String tittle, String route) {
        this.code = code;
        this.tittle = tittle;
        this.route = route;
    }

    public ModelTrack() {
    }
}
