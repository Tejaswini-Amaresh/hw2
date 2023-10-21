import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import model.TransactionFilter;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView(model);
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getFilterTransactionBtn().addActionListener(e -> {

      // Get filter transaction data from view
      String filterField = view.getSelectedFilterField();
    
      TransactionFilter filter = null;
      if (filterField.equals("amount")) {
        double filterAmount = 0;
        if(view.getFilterField().isEmpty()) {
          filterAmount = 0;
        } else {
        filterAmount = Double.parseDouble(view.getFilterField());
        filter = new AmountFilter(filterAmount);
        }
      }
      else if (filterField.equals("category")) {
        String category = view.getFilterField();
        filter = new CategoryFilter(category);
      }
      
      // Call controller to filter transaction
      boolean filtered = controller.applyFilter(filter);
      
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid filter amount or category entered");
        view.toFront();
      }
    });

  }

}