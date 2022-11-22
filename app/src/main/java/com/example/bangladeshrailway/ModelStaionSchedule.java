package com.example.bangladeshrailway;

public class ModelStaionSchedule {
    String tittle,trains;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTrains() {
        return trains;
    }

    public void setTrains(String trains) {
        this.trains = trains;
    }

    public ModelStaionSchedule(String tittle, String trains) {
        this.tittle = tittle;
        this.trains = trains;
    }

    public ModelStaionSchedule() {
    }
}
