main

└── runHomeScreen()        
  
| D) Add Deposit → addDeposit()
 
| P) Make Payment → makePayment()

│ L) Ledger → runLedgerScreen()

│ X) Exit

│
└── runLedgerScreen() 

| A) All → displayAllTransactions ()
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