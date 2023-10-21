package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.ExpenseTrackerModel;
import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton filterTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JComboBox<String> filterFieldList;
  private JTextField filterValue;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    filterTransactionBtn = new JButton("Filter Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    JLabel filterLabel = new JLabel("Filter:");

    JLabel filterField = new JLabel("Field");
    String[] fieldList = {"amount", "category"};
    filterFieldList = new JComboBox<String>(fieldList);

    JLabel filterValueLabel= new JLabel("Value");
    filterValue = new JTextField(10);
    
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel filterPanel = new JPanel();
    filterPanel.add(filterLabel);
    filterPanel.add(filterField);
    filterPanel.add(filterFieldList);
    filterPanel.add(filterValueLabel);
    filterPanel.add(filterValue);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(filterTransactionBtn);
  
    // Add panels to frame
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(2,1));
    mainPanel.add(inputPanel);
    mainPanel.add(filterPanel);

    add(mainPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void createTransactionTable(ExpenseTrackerModel trackerModel) {
    // Create table
    transactionsTable = new JTable(model) 
      {
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
        {
          Component c = super.prepareRenderer(renderer, row, column);
          Color color = trackerModel.getRowTransaction(row).getColor();
          c.setBackground(color);
          return c;
        }
      };
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  

  public void resetFilter(List<Transaction> transactions) {
    for(Transaction t : transactions) {
        t.setColor(Color.WHITE);
    }
    transactionsTable.updateUI();
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getFilterTransactionBtn() {
    return filterTransactionBtn;
  }
  public String getSelectedFilterField() {
    String selectedField = filterFieldList.getSelectedItem().toString();
    return selectedField;
  }

  public String getFilterField() {
    return filterValue.getText();
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
