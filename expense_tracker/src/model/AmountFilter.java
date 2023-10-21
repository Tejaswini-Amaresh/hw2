package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.InputValidation;

public class AmountFilter implements TransactionFilter {
    
    private double filterAmount;
    private List<Transaction> filteredTransactions;

    public AmountFilter(double amount) {
        this.filterAmount = amount;
        this.filteredTransactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> filter(List<Transaction> allTransactions) {
        for (Transaction transaction: allTransactions) {
            if(transaction.getAmount() == this.filterAmount) {
                transaction.setColor(new Color(173, 255, 168));
                this.filteredTransactions.add(transaction);
            }
        }
        return this.filteredTransactions;
    }

    @Override
    public boolean isValid() {
        return InputValidation.isValidAmount(this.filterAmount);
    }
}