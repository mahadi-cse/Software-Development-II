package com.example.bangladeshrailway;

public class ModelTicket {
    String from,to,trainname,email,seat,pnr,route,date,name,nid,phone,class_seat,coach,deptTime,currentTime,price;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ModelTicket(String from, String to, String trainname, String email, String seat, String pnr, String route, String date, String name, String nid, String phone, String class_seat, String coach, String deptTime, String currentTime, String price) {
        this.from = from;
        this.to = to;
        this.trainname = trainname;
        this.email = email;
        this.seat = seat;
        this.pnr = pnr;
        this.route = route;
        this.date = date;
        this.name = name;
        this.nid = nid;
        this.phone = phone;
        this.class_seat = class_seat;
        this.coach = coach;
        this.deptTime = deptTime;
        this.currentTime = currentTime;
        this.price = price;
    }

    public ModelTicket() {
    }
}
