package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  /**
   * immutability incorporated
   * @return
   */
  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }
 

  /**
   * get a particular transaction, given the row number
   * @param row
   * @return
   */
  public Transaction getRowTransaction(int row) {
    if (row >= transactions.size() || transactions.isEmpty()) {
      return new Transaction(0, null);
    }
    return transactions.get(row);
  }

}