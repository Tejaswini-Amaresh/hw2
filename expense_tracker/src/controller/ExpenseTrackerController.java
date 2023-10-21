package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public void reset() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.resetFilter(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  public boolean applyFilter(TransactionFilter filter) {
    if (!filter.isValid()) {
      return false;
    }

    reset();

    List<Transaction> allTransactions = model.getTransactions();
    List<Transaction> filteredTransactions = filter.filter(allTransactions);
    
    if (filteredTransactions.isEmpty()) {
      return false;
    }

    view.getTableModel().fireTableDataChanged();;
    return true;

  }
  // Other controller methods
}