// package main.java.controller;
// import main.java.model.role.User;
// import main.java.model.flight.Booking;
// import main.java.view.PaymentView;
// import java.awt.event.*;

package controller;
import model.role.User;
import model.flight.Booking;
import view.PaymentView;
import java.awt.event.*;

import javax.swing.Action;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentController implements ActionListener{
  private Booking booking;
  private Database db;
  private PaymentView view;
  private MainController mainController;
  private String SeatTypeVal;
  private double SeatPrice;
  private double totalPrice;
  private double cancellationFees;



  public PaymentController(Database db, MainController mc, Booking booking) {
    this.mainController = mc;
    this.booking = booking;
    this.view = new PaymentView();
    this.db = db;
    addListeners();
}


int seatId = booking.getSeatId();
double flight_price = booking.getPrice();





// public string seatType() {
//   String query = "SELECT class FROM seats WHERE seat_id = '" + booking.getSeatId() +  "'"; 
//   ResultSet rs = db.executeQuery(query);
//   try {
//       return rs.next();
//   } catch (Exception e) { }
// }

public void seatType() {
  String query = "SELECT class FROM seats WHERE seat_id = '" + booking.getSeatId() + "'";
  ResultSet rs = db.executeQuery(query);

  try {
      if (rs.next()) {
          SeatTypeVal = rs.getString("class");
      } 
  } catch (SQLException e) {
      e.printStackTrace(); // Handle or log the exception appropriately
      // Or throw an exception if needed
  } finally {
      // Close resources in a finally block
      try {
          if (rs != null) {
              rs.close();
          }
          // Close other resources if needed (e.g., preparedStatement)
      } catch (SQLException e) {
          e.printStackTrace(); // Handle or log the exception appropriately
      }
  }
}

public double seatPrice() {
  if ("business".equals(SeatTypeVal)) {
      SeatPrice = 50.00;
  }
  if ("comfort".equals(SeatTypeVal)) {
      SeatPrice = 35.00;
  }
  if ("ordinary".equals(SeatTypeVal)) {
      SeatPrice = 20.00;
  }
  return SeatPrice;
}


//Checks if button was clicked for cancellation fees, if clicked adds $50
@Override
public void actionPerformed(ActionEvent e) {
    // Check if the "Yes" button is the source of the event
    if (e.getSource() == view.getYesButton()) {
        // If "Yes" button is clicked, add 50 to totalPrice
        cancellationFees = 50.00;
    }
    else{
      cancellationFees = 0.00;
    }
  }




public double FinalPrice() {
  //includes cancellation fees, taxes, and discount
  double flight_price = booking.getPrice();
  double above_price = 300.00;
  double NewtotalPrice;
  totalPrice = flight_price + SeatPrice + cancellationFees;

  if (totalPrice >= above_price) {
      // Fetch a randomized discount value from the promotion table
      double discount = getRandomDiscount();
      
  // Apply the discount to the totalPrice
      NewtotalPrice = totalPrice - (totalPrice * discount);

      return (NewtotalPrice * 1.05);
  } else {
      // If the promotion condition is not met, return the original totalPrice
      return (totalPrice * 1.05);
  }
}


private double getRandomDiscount() {
  String query = "SELECT discount FROM promotion ORDER BY RAND() LIMIT 1";
  try {
      ResultSet rs = db.executeQuery(query);
      if (rs.next()) {
          return rs.getDouble("discount");
      }
  } catch (SQLException e) {
      e.printStackTrace(); // Handle or log the exception appropriately
  }

  // Return a default discount value if fetching from the table fails
  return 0.0;
}





public PaymentView getView() {
  return view;
}


private void addListeners() {
  view.addConfirmListener(this);
}


public void updateView() {
  view.display();
}



}