package com.example.bangladeshrailway;

import java.util.Objects;

public class Model {
    String From,Name,To,arraivalTime,counterAC_B,counterSHOVON,counterSNIGDHA,counterS_CHAIR,departureTime,onlineAC_B,
            onlineSHOVON,onlineSNIGDHA,onlineS_CHAIR,priceAC_B,priceSHOVON,priceSNIGDHA,priceS_CHAIR;

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getArraivalTime() {
        return arraivalTime;
    }

    public void setArraivalTime(String arraivalTime) {
        this.arraivalTime = arraivalTime;
    }

    public String getCounterAC_B() {
        return counterAC_B;
    }

    public void setCounterAC_B(String counterAC_B) {
        this.counterAC_B = counterAC_B;
    }

    public String getCounterSHOVON() {
        return counterSHOVON;
    }

    public void setCounterSHOVON(String counterSHOVON) {
        this.counterSHOVON = counterSHOVON;
    }

    public String getCounterSNIGDHA() {
        return counterSNIGDHA;
    }

    public void setCounterSNIGDHA(String counterSNIGDHA) {
        this.counterSNIGDHA = counterSNIGDHA;
    }

    public String getCounterS_CHAIR() {
        return counterS_CHAIR;
    }

    public void setCounterS_CHAIR(String counterS_CHAIR) {
        this.counterS_CHAIR = counterS_CHAIR;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getOnlineAC_B() {
        return onlineAC_B;
    }

    public void setOnlineAC_B(String onlineAC_B) {
        this.onlineAC_B = onlineAC_B;
    }

    public String getOnlineSHOVON() {
        return onlineSHOVON;
    }

    public void setOnlineSHOVON(String onlineSHOVON) {
        this.onlineSHOVON = onlineSHOVON;
    }

    public String getOnlineSNIGDHA() {
        return onlineSNIGDHA;
    }

    public void setOnlineSNINGDHA(String onlineSNINGDHA) {
        this.onlineSNIGDHA = onlineSNINGDHA;
    }

    public String getOnlineS_CHAIR() {
        return onlineS_CHAIR;
    }

    public void setOnlineS_CHAIR(String onlineS_CHAIR) {
        this.onlineS_CHAIR = onlineS_CHAIR;
    }

    public String getPriceAC_B() {
        return priceAC_B;
    }

    public void setPriceAC_B(String priceAC_B) {
        this.priceAC_B = priceAC_B;
    }

    public String getPriceSHOVON() {
        return priceSHOVON;
    }

    public void setPriceSHOVON(String priceSHOVON) {
        this.priceSHOVON = priceSHOVON;
    }

    public String getPriceSNIGDHA() {
        return priceSNIGDHA;
    }

    public void setPriceSNINGDHA(String priceSNINGDHA) {
        this.priceSNIGDHA = priceSNIGDHA;
    }

    public String getPriceS_CHAIR() {
        return priceS_CHAIR;
    }

    public String getPrice(String type) {
        String price="";
        if(Objects.equals(type, "S_CHAIR")){
            price=priceS_CHAIR;
        }
        else if(Objects.equals(type, "SNIGDHA")){
            price=priceSNIGDHA;
        }
        return price;
    }

    public void setPriceS_CHAIR(String priceS_CHAIR) {
        this.priceS_CHAIR = priceS_CHAIR;
    }

    public Model(String from, String name, String to, String arraivalTime, String counterAC_B, String counterSHOVON, String counterSNIGDHA, String counterS_CHAIR, String departureTime, String onlineAC_B, String onlineSHOVON, String onlineSNIGDHA, String onlineS_CHAIR, String priceAC_B, String priceSHOVON, String priceSNINGDHA, String priceS_CHAIR) {
        From = from;
        Name = name;
        To = to;
        this.arraivalTime = arraivalTime;
        this.counterAC_B = counterAC_B;
        this.counterSHOVON = counterSHOVON;
        this.counterSNIGDHA = counterSNIGDHA;
        this.counterS_CHAIR = counterS_CHAIR;
        this.departureTime = departureTime;
        this.onlineAC_B = onlineAC_B;
        this.onlineSHOVON = onlineSHOVON;
        this.onlineSNIGDHA = onlineSNIGDHA;
        this.onlineS_CHAIR = onlineS_CHAIR;
        this.priceAC_B = priceAC_B;
        this.priceSHOVON = priceSHOVON;
        this.priceSNIGDHA = priceSNINGDHA;
        this.priceS_CHAIR = priceS_CHAIR;
    }

    public Model() {
    }
}
