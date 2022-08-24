package dtustudent.dtupay.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity
//@Table(name = "tokens"), if table is not defined, the Entity class name would be the table name
public class Tokens {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
    private @Id @GeneratedValue Long id;
    @Column(name = "customer_id")
    private String customerId;
	@Column(name = "tokens")
	private String tokens;
	public Tokens(){}
	public Tokens(String id, String tokens){
		this.customerId = id;
		this.tokens = tokens;
	}
    public long getId(){
        return id;
    }
    public String getCustomerId(){
        return customerId;
    }
    public String getTokens(){
        return tokens;
    }
    public void setCustomerId(String customerId){
        this.customerId= customerId;
    }
    public void setTokens(String tokens){
        this.tokens= tokens;
    }
//     @Override
// 	public String toString() {
// 		return "Orders [id=" + id + ", customer=" + customerId + ", desc=" + tokens + "]";
// 	}
}

