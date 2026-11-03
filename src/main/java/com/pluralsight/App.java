package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        runHomeScreen();
        runLegerScreen();
        runReportsScreen();

    }
    public static void runHomeScreen() {
        String command;
        do {
            System.out.println();
            System.out.println("=== Home ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger ");
            System.out.println("X) Exit");
            System.out.print("Enter a command: ");
            command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> runLegerScreen();
                case "X" -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (!command.equals("X"));
    }
    private static void addDeposit() {
        System.out.println("--- Add Deposit ---");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = promptForString("Enter description: ");
        String vendor = promptForString("Enter vendor: ");
        double amount = 0;
        while (amount <= 0) {
            System.out.println("Enter amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (like $89.00): ");
            }
        }

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(deposit.toCsvRow() + "\n");
            System.out.println("Deposit saved: ");
        }  catch (IOException e) {
            System.out.println("ERROR: Could not save the deposit: " + e.getMessage());
        }
        pressEnterToContinue();



    }
    private static void makePayment() {
        System.out.println("--- Make Payment ---");
        LocalDate date = LocalDate.now();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        double amount = 0;

    }
    public static void runLegerScreen() {
        String command;
        do {
            System.out.println();
            System.out.println("=== Ledger Screen ===");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter a command: ");
            command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "A" -> displayAllTransactions();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> runReportsScreen();
                case "H" -> System.out.println("Back to Home");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (!command.equals("H"));
    }
    public static void runReportsScreen() {
        String command;
        do {
            System.out.println();
            System.out.println("=== Reports ===");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter a command: ");
            command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "1" -> runMonthToDateReport();
                case "2" -> runPreviousMonthReport();
                case "3" -> runYearToDateReport();
                case "4" -> runPreviousYearReport();
                case "5" -> searchVendor();
                case "0" -> System.out.println("Back to Leger");
                default -> System.out.println("That isn't a command. Try again.");
            }
        } while (!command.equals("0"));
    }
    private static ArrayList<Transaction> getTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // Split it into individual fields using the | character as the delimiter
                String[] fields = line.split(Pattern.quote("|"));

                // Fill data into product object and add to list
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                double price = Double.parseDouble(fields[2]);
//                Transaction transaction1 = new Transaction(descriptions, amount, vendor, date, time);
//                transactions.add(transactions);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("There was an error reading the transaction file. Check the location of the file.");
        }
        return transactions;
    }
    public static void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press Enter to go back to the menu...");
        scanner.nextLine();
    }
    public static void promptForString(){


    }
    public static void displayAllTransactions(){

    }
    public static void displayDeposits(){

    }
    public static void displayPayments() {

    }
    public static void runMonthToDateReport(){

    }
    public static void runPreviousMonthReport(){

    }
    public static void runYearToDateReport(){

    }
    public static void runPreviousYearReport(){

    }
    public static void searchVendor(){

    }
}
