package com.acme.utils;

public class MyDate {
    public int month;
    public int day;
    public int year;

    public MyDate() {
        this.month = 1;
        this.day = 1;
        this.year = 2000;
    }

    public MyDate(int month,int day,int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public void setDate(int m,int d,int y) {
        this.month = m;
        this.day = d;
        this.year = y;
    }
}