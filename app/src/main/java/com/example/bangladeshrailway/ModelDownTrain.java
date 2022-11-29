package com.example.bangladeshrailway;

public class ModelDownTrain {
    String in,out,name,start,destination;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ModelDownTrain(String in, String out, String name, String start, String destination) {
        this.in = in;
        this.out = out;
        this.name = name;
        this.start = start;
        this.destination = destination;
    }

    public ModelDownTrain() {
    }
}
