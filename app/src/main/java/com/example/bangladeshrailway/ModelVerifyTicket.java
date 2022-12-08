package com.example.bangladeshrailway;

public class ModelVerifyTicket {
    String class_seat,coach,currentTime,date,from,to,trainname,seat,deptTime,price;

    public String getClass_seat() {
        return class_seat;
    }

    public void setClass_seat(String class_seat) {
        this.class_seat = class_seat;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ModelVerifyTicket(String class_seat, String coach, String currentTime, String date, String from, String to, String trainname, String seat, String deptTime, String price) {
        this.class_seat = class_seat;
        this.coach = coach;
        this.currentTime = currentTime;
        this.date = date;
        this.from = from;
        this.to = to;
        this.trainname = trainname;
        this.seat = seat;
        this.deptTime = deptTime;
        this.price = price;
    }

    public ModelVerifyTicket() {
    }
}
