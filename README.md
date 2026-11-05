# Accounting  Ledger Application

A command-line app Java application for tracking financial transactions. 
This application allows users to make deposits and payments, 
view transaction histories.

## Features
Display Welcome Banner 
- this is user start of application they will be greeted with this before the home screens

Home Menu

- Add Deposit: Prompt for description, vendor, and amount to record positive cash flow with the current date and time.
- Make Payment: Prompt for description, vendor, and positive amount that automatically saved as a negative debit
- Ledger Screen:  for viewing transactions and accessing reports.

Ledger Screen
- All Entries: Displays all recorded financial transactions.
- Deposits: Displays only positive financial transactions.
- Payments: Displays only negative financial transactions (debits).
- Reports Menu: Provides specialized filtering capabilities.

Reports Screen
- Month To Date: Displays transactions recorded within the current calendar month.
- Previous Month: Displays transactions from the prior calendar month.
- Year To Date: Displays transactions recorded within the current calendar year.
- Previous Year: Displays transactions from the prior calendar year.
- Search by Vendor: Case-insensitive search for transactions by vendor name.

- Methods
- All transactions are saved to and loaded from a local transactions.csv file using | delimiters.
- Automatic input validation prevents application crashes when parsing numeric inputs or blank text fields.

## How to run it

Launch the application to bring up the Home menu.
Enter D to add a deposit or P to make a payment.
Enter L to go to the Ledger screen.
From the Ledger, view all items, view deposits/payments only -
select R to run specific date or vendor searches.
Enter X from the Home menu to safely exit the application.

## Interesting code

```java  
public String toDisplayCard() {
    return "----------------------------------------\n" +
            " Date: " + date + " | Time: " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n" +
            " Description: " + description + "\n" +
            " Vendor: " + vendor + "\n" +
            " Amount: $" + String.format("%.2f", amount) + "\n" +
            "----------------------------------------";
}
```
## Screens and Menus

```text

main

└── runHomeScreen()         
| D) Add Deposit    → addDeposit()
| P) Make Payment   → makePayment()
│ L) Ledger         → runLedgerScreen()
│ X) Exit
│
└── runLedgerScreen() 
| A) All              → displayAllTransactions ()
│ D) Deposits         → displayDeposits()
│ P) Payments         → displayPayments()
│ R) Reports          → runReportsScreen()
│ H) Home             → back to Home
│
└── runReportsScreen()
1) Month To Date    → runMonthToDateReport()
2) Previous Month   → runPreviousMonthReport()
3) Year To Date     → runYearToDateReport()
4) Previous Year    → runPreviousYearReport()
5) Search by Vendor → searchVendor()
0) Back             → back to Ledger

```