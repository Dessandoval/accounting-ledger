package com.pluralsight;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    static String transactionFile = ("transactions.csv");
    static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String rowFormat = "%-10s  %-8s  %-30s  %-22s  %10s%n";
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
                case "L" -> runLedgerScreen();
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

        double amount = promptForAmount();

        if (saveTransaction(new Transaction(date, time, description, vendor, amount))) {
            System.out.println("Payment recorded successfully!");
        }
        pressEnterToContinue();
    }
    private static double promptForAmount() {
        while (true) {
            System.out.print("Enter amount: ");
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number, (like 15.25).");
            }
        }
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

        if (saveTransaction(new Transaction(date, time, description, vendor, amount))) {
            System.out.println("Payment recorded successfully!");
        }
        pressEnterToContinue();

    }

    public static void runLedgerScreen() {
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
                case "1" -> displayMonthToDateReport();
                case "2" -> displayPreviousMonthReport();
                case "3" -> displayYearToDateReport();
                case "4" -> displayPreviousYearReport();
                case "5" -> searchByVendor();
                case "0" -> System.out.println("Back to Ledger");
                default -> System.out.println("That isn't a command. Try again.");
            }
        } while (!command.equals("0"));
    }
    public static void pressEnterToContinue() {
        System.out.println();
        System.out.print("Press Enter to go back to the menu...");
        scanner.nextLine();
    }
    public static boolean saveTransaction(Transaction transaction){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(transactionFile, true))){
            transactions.add(transaction);
            bufferedWriter.write(transaction.toCsvRow() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Could not save the deposit: " + transactionFile  + ": " + e.getMessage());
            return false;
        }

    }
    public static void displayAll(){
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toCsvRow());
        }

    }
    public static void displayDeposits(){
       ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        System.out.println("--- Deposits ---");
        if (deposits.isEmpty()) {
            System.out.println("No deposits found.");
        } else {
            for (Transaction transaction : deposits) {
                System.out.println(transaction.toCsvRow());
            }
        }
        pressEnterToContinue();
    }
    public static void displayPayments() {
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }
        System.out.println("--- Payments ---");
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            for (Transaction transaction : payments) {
                System.out.println(transaction.toCsvRow());
            }
        }
        pressEnterToContinue();
    }
    public static void displayMonthToDateReport(){
        ArrayList<Transaction> monthToDate = new ArrayList<>();
        LocalDate date = LocalDate.now();
        int currentMonth = date.getMonthValue();
        int currentYear = date.getYear();

        for (Transaction transaction : transactions){
            int transactionMonth = transaction.getDate().getMonthValue();
            int transactionYear = transaction.getDate().getYear();

            if (transactionYear == currentYear && transactionMonth == currentMonth) {
                monthToDate.add(transaction);
            }
        }
        System.out.println("--- Month To Date Report ---");
        if (monthToDate.isEmpty()) {
            System.out.println("No transactions found for this month.");
        } else {
            for (Transaction transaction : monthToDate) {
                System.out.println(rowFormat);
            }
        }
        System.out.println();
        pressEnterToContinue();
    }
    public static void displayPreviousMonthReport(){
        System.out.println("Todo");
        pressEnterToContinue();
    }
    public static void displayYearToDateReport(){
        System.out.println("Todo");
        pressEnterToContinue();
    }
    public static void displayPreviousYearReport(){
        System.out.println("Todo");
        pressEnterToContinue();
    }
    public static void searchByVendor(){
        System.out.println("Todo");
        pressEnterToContinue();
    }
    public static void loadTransactions() {
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            bufferedReader.readLine();

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
