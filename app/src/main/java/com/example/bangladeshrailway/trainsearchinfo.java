package com.example.bangladeshrailway;

public class trainsearchinfo {
    String[] time = {};
    String [] counter ={};
    String [] online ={};
    String [] price ={};

    public trainsearchinfo() {
    }

    public trainsearchinfo(String[] time, String[] counter, String[] online, String[] price) {
        this.time = time;
        this.counter = counter;
        this.online = online;
        this.price = price;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String[] getCounter() {
        return counter;
    }

    public void setCounter(String[] counter) {
        this.counter = counter;
    }

    public String[] getOnline() {
        return online;
    }

    public void setOnline(String[] online) {
        this.online = online;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }
}
