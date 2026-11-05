package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String description;
    private double amount;
    private String vendor;
    private LocalDate date;
    private LocalTime time;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.description = description;
        this.amount = amount;
        this.vendor = vendor;
        this.date = date;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String toCsvRow() {
        return date + "|" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|"
                + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }
    public String toDisplayCard() {
        return "----------------------------------------\n" +
                " Date: " + date + " | Time: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" +
                " Description: " + description + "\n" +
                " Vendor: " + vendor + "\n" +
                " Amount: $" + String.format("%.2f", amount) + "\n" +
                "----------------------------------------";
    }
}
