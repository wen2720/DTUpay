package dtustudent.dtupay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

@Entity
//@Table(name = "tokens"), if table is not defined, the Entity class name would be the table name
public class Token {
    @Id 
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "customer_id")
    private String customerId;
    
    public Token(){}
	public Token(String customerId){
		this.customerId = customerId;
	}

    public String getId() {
        return this.id;
    }
    public String getCustomerId(){
        return this.customerId;
    }
}


