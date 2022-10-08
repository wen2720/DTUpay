package dtustudent.dtupay.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {
    @Id 
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    
    @Column(name="name")
    private String name;
    
    public Customer(){}
    public Customer(String customerName) {
        this.name = customerName;
    }

    public String getId() {
        return this.id;
    }
    public String getCustomerName() {
        return this.name;
    }
}