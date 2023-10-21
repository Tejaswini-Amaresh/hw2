import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

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
      String filterFieldValue = view.getFilterField();

      if (filterField.equals("amount")) {
        double filterAmount = 0;
        if(filterFieldValue.isEmpty()) {
          filterAmount = 0;
        } else {
        filterAmount = Double.parseDouble(filterFieldValue);
        controller.setFilterStrategy(new AmountFilter(filterAmount));
        }
      }
      else if (filterField.equals("category")) {
        controller.setFilterStrategy(new CategoryFilter(filterFieldValue));
      }
      
      // Call controller to filter transaction
      boolean filtered = controller.applyFilter();
      
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid filter amount or category entered");
        view.toFront();
      }
    });

  }

}