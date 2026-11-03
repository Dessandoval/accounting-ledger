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
        loadTransactions(); // load first the run menu
        runHomeScreen();
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

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        transactions.add(new Transaction(date, time, description, vendor, amount));
        saveTransactions();
        System.out.println("Deposit added successfully!");
        pressEnterToContinue();

    }

    private static void makePayment() {
        System.out.println("--- Make Payment ---");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount (positive number, will be saved as a debit): ");
        double amount = -Math.abs(Double.parseDouble(scanner.nextLine()));

        transactions.add(new Transaction(date, time, description, vendor, amount));
        saveTransactions();
        System.out.println("Payment recorded successfully!");
        pressEnterToContinue();

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
                case "A" -> displayAll();
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
    public static void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press Enter to go back to the menu...");
        scanner.nextLine();
    }
    public static void saveTransactions(){
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"))){
//            writer.write(deposit.toCsvRow() + "\n");
//            System.out.println("Deposit saved: "); {
//            } catch (IOException e) {
//                System.out.println("ERROR: Could not save the deposit: ");
//            }
//        }

    }
    public static void displayAll(){
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(transactions);
        }

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
    public static void loadTransactions() {
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(Pattern.quote("|"));
                if (fields.length == 5) {
                    LocalDate date = LocalDate.parse(fields[0]);
                    LocalTime time = LocalTime.parse(fields[1]);
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]);
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("There was an error loading the transaction file.");
        }
    }

}
