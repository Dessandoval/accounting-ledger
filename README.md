main

└── runHomeScreen()        
  
| D) Add Deposit → addDeposit
 
| P) Make Payment → makePayment

│ L) Ledger → runLedgerScreen()

│ X) Exit

│
└── runLedgerScreen() 

| A) All → displayAllTransactions 
│ D) Deposits         → displayAllDeposits
│ P) Payments         → displayAllPayments
│ R) Reports          → runReportsScreen()
│ H) Home             → back to Home
│
└── runReportsScreen()
1) Month To Date    → ____________
2) Previous Month   → ____________
3) Year To Date     → ____________
4) Previous Year    → ____________
5) Search by Vendor → ____________
0) Back             → back to Ledger