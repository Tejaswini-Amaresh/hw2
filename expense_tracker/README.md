# hw2 Design and Implementation

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 
The following additional features have been incorporated:
1. Filtering transactions via Amount or Category
2. The filtered transactions are highlighted in green

User Interface snapshots:
Amount filter:

![image](https://github.com/Tejaswini-Amaresh/hw2/assets/49989159/9b616bf0-69d1-414f-b455-3fea63ab7cdf)

Category filter:

![image](https://github.com/Tejaswini-Amaresh/hw2/assets/49989159/1af79d10-3b30-424f-aa52-f880bfc101f9)

Invalid Value filter:

![image](https://github.com/Tejaswini-Amaresh/hw2/assets/49989159/f5af8119-c516-4d0c-bef2-807b56d23629)



Additionally, encapsulation and immutability have been applied on the list of Transactions, adhering to the Open-Close principle.
STrategy pattern has been applied to ensure extensibility of the filters.
## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.
