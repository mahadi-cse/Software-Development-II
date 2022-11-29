package com.example.bangladeshrailway;

public class ModelDownTrainFrag {
    String station,in,out;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public ModelDownTrainFrag() {
    }

    public ModelDownTrainFrag(String station, String in, String out) {
        this.station = station;
        this.in = in;
        this.out = out;
    }
}
