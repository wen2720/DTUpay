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
    @Override
    public String fGetName() {
        return customerName;
    }
    // get name by id
    @Override
    public String fGetUniqueId() {
        return uniqueId;
    }


}

    