// package dtupay;

// import javax.persistence.Entity;
// import javax.persistence.Table;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Column;
// import javax.persistence.GenerationType;

// @Entity
// @Table(name = "orders")
// public class Tokens {
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
//     @Column(name = "customer_id")
//     private String customerId;
// 	@Column(name = "tokens")
// 	private String tokens;
// 	public Tokens() {}
// 	public Tokens(String id, String tokens){
// 		this.customerId = id;
// 		this.tokens = tokens;
// 	}
// 	public Long getId() {
// 		return id;
// 	}
//     public String getCustomerId() {
// 		return customerId;
// 	}
// 	public String getTokens() {
// 		return tokens;
// 	}
//     public void setCustomerId(String customerId) {
// 		this.customerId = customerId;
// 	}
// 	public void setTokens(String tokens) {
// 		this.tokens = tokens;
// 	}
// }

