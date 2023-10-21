package controller;

import view.ExpenseTrackerView;

import java.util.List;

import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private TransactionFilter transactionFilter;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
    view.createTransactionTable(model);
    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * Reset filter
   */
  public void reset() {
    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.resetFilter(transactions);

  }

  /**
   * Setting the strategy pattern for filtering the transactions
   * @param filter
   */
  public void setFilterStrategy(TransactionFilter filter) {
      this.transactionFilter = filter;
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
  
  /**
   * 
   * @return boolean to indicate successful application 
   * of the filter
   */
  public boolean applyFilter() {
    if (!this.transactionFilter.isValid()) {
      return false;
    }

    reset();

    List<Transaction> allTransactions = model.getTransactions();
    List<Transaction> filteredTransactions = this.transactionFilter.filter(allTransactions);
    
    if (filteredTransactions.isEmpty()) {
      return false;
    }

    view.getTableModel().fireTableDataChanged();
    return true;

  }
  // Other controller methods
}