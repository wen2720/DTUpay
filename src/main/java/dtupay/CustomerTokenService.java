// package dtupay;

// import java.util.UUID;  

// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;

// import dtupay.CustomerTokenRepository;
// import dtupay.CustomerOrder;
// import dtupay.Tokens;

// @Service
// public class CustomerTokenService {
//   // @Autowired
// 	//private CustomerTokenRepository repository;
//   private CustomerTokenRepository repository;

// 	public void saveTokens(CustomerOrder customerOrder) {	
// 		Tokens newTokens = new Tokens(customerOrder.getCustomerId(),customerOrder.getNoToken());
// 		repository.save(newTokens);
// 	}
// }
