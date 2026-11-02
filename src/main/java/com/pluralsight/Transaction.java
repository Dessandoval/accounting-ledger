package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private String descriptions;
    private double amount;
    private String vendor;
    private LocalDate date;
    private LocalTime time;

    public Transaction(String descriptions, double amount, String vendor, LocalDate date, LocalTime time) {
        this.descriptions = descriptions;
        this.amount = amount;
        this.vendor = vendor;
        this.date = date;
        this.time = time;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
