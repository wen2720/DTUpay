package model;
import java.util.*;
import contract.IUser;
import type.User;

public class NewCustomer extends User {
    private String uniqueId;
    private String customerName;
    // Dependency Injection, Constructor Injection 
    public NewCustomer(String uid, String name) {
        if (uid == "" && customerName != "") {
            System.out.println("Invalid Customer Identifier.");
        } else {
            uniqueId = uid; 
            customerName = name;
        }
    }
    // Setters for simulating front end user has been logged in if the credential is verified, if not those would just be set as empty strings. 
    // A valid order placement by using REST PUT is either set empty string to the user credential as new customer first time placing the order, if the token list is less than 5
    @Override
    public String fSetName() {
        return customerName;
    }
    // get name by id
    @Override
    public String fSetUniqueId() {
        return uniqueId;
    }


}

    