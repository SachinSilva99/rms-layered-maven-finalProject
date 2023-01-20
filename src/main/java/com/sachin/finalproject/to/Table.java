package com.sachin.finalproject.to;

public class Table {
    String id;
    int noOfChairs;

    public Table(String id, int noOfChairs) {
        this.id = id;
        this.noOfChairs = noOfChairs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNoOfChairs() {
        return noOfChairs;
    }

    public void setNoOfChairs(int noOfChairs) {
        this.noOfChairs = noOfChairs;
    }

    public Table() {
    }

    @Override
    public String toString() {
        return "Table{" +
                "id='" + id + '\'' +
                ", noOfChairs=" + noOfChairs +
                '}';
    }
}
