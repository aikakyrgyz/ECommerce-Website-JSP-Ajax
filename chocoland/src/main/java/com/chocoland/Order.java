package com.chocoland;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order {
    public static int orderID = 1000;
    
    
    private int id;
    private String firstName;
    private String lastName;
    String phoneNumber;
    String email;
    String address;
    String country;
    String city;
    String state;
    String zip;
    String ccName;
    String ccNumber;
    String ccExpiration;
    String ccCVV;

    String orderDate;
    int totalQuantity;
    String taxes;
    String totalPaid;
        
    Order(String firstName, String lastName, String phoneNumber,
    String email, String address, String country, String state, String city, String zip, 
    String ccName, String ccNumber, String ccExpiration, String ccCVV, 
    int totalQuantity, String taxes, String totalPaid)
        {
            id = orderID + 1;
            orderID++;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.country = country;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.ccName = ccName;
            this.ccNumber = ccNumber;
            this.ccExpiration = ccExpiration;
            this.ccCVV = ccCVV;
            this.totalQuantity = totalQuantity;
            this.totalPaid = totalPaid;
            this.taxes = taxes;
            // this.totalPaid = BigDecimal.valueOf(totalPaid)
            // .setScale(2, RoundingMode.HALF_UP)
            // .doubleValue();
            // this.taxes = BigDecimal.valueOf(taxes)
            // .setScale(3, RoundingMode.HALF_UP)
            // .doubleValue();

        }

    int getID(){return id;}

    String getfirstName() {return firstName;}

    String getLastName() {return lastName;}

    String getTotalPaid() {return totalPaid;}

    int getTotalQuantity() {return totalQuantity;}

    String getTaxes(){return taxes;} 

    void setORderDate(String orderDate)
    {
        this.orderDate = orderDate;
}
}