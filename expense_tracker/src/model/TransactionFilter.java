package model;

import java.util.List;

// TransactionFilter interface
public interface TransactionFilter {
    public List<Transaction> filter(List<Transaction> allTransactions);
    public boolean isValid();
}
