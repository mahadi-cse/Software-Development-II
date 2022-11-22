package com.example.bangladeshrailway;

public class ModelTrainRecycle {
    String tittle,url,route;

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public ModelTrainRecycle(String tittle, String url, String route) {
        this.tittle = tittle;
        this.url = url;
        this.route = route;
    }

    public ModelTrainRecycle() {
    }
}
