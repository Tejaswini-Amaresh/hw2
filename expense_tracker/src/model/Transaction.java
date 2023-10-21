package model;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {
  //Preventing external data modification by making variables private final
  private final double amount;
  private final String category;
  private final String timestamp;
  private Color color;

  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
    this.color = Color.WHITE;
  }

  public double getAmount() {
    return amount;
  }
  //excluding setting methods 
  // public void setAmount(double amount) {
  //   this.amount = amount;
  // }

  public String getCategory() {
    return category;
  }

  // public void setCategory(String category) {
  //   this.category = category; 
  // }
  
  public String getTimestamp() {
    return timestamp;
  }

  public void setColor(Color C) {
    this.color = C;
  }

  public Color getColor() {
    return this.color;
  }

  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}