package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.InputValidation;

public class CategoryFilter implements TransactionFilter{
    
    private String filterCategory;
    private List<Transaction> filteredTransaction;

    public CategoryFilter(String category) {
        this.filterCategory = category;
        this.filteredTransaction = new ArrayList<>();
    }

    @Override
    public List<Transaction> filter(List<Transaction> allTransactions) {
        for (Transaction transaction: allTransactions) {
            if (transaction.getCategory().equals(this.filterCategory)) {
                transaction.setColor(new Color(173, 255, 168));
                this.filteredTransaction.add(transaction);
            }
        }
        return this.filteredTransaction;
    }

    @Override
    public boolean isValid() {
        return InputValidation.isValidCategory(this.filterCategory);
    }
}