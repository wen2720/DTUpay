package dtu;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;


// import dtupay.CustomerOrder;
// import dtupay.CustomerTokenService;
import dtupay.CustomerTokenRepository;
import dtupay.Tokens;

@RestController
@EnableAutoConfiguration
//@RequestMapping
public class AppController {
	// private static Map<String,ArrayList<String>> customerTokenMap = new HashMap<>();
	//@Autowired
	// private CustomerTokenService customerTokenService;
    //public AppController(CustomerTokenService service) {
    //    customerTokenService = service;
    //}

	//private CustomerTokenService theRepository;
	//@Autowired
	private CustomerTokenRepository repository;
	public AppController(CustomerTokenRepository repo) {
	 	repository = repo;
	}
	// @GetMapping("/tokens")
	// public ResponseEntity getAllTokens() {
    //     return ResponseEntity.ok(this.repository.findAll());
    // }
	// @GetMapping("/tokens")
	// Tokens getToken(@RequestParam Long id) {
    //     return repository.getById(id);
    // }
	@GetMapping("/tokens")
 	public String getTokenByID(@RequestParam Long id) {
		//System.out.println(repository.getReferenceById(id));
		return "HelloWorld";
    }
	// @PostMapping("/tokens")
	// void postTokens(@RequestBody CustomerOrder order) {
	// 	customerTokenService.saveTokens(order);
	// }

	// @GetMapping("/customer_token")
	// CustomerOrderResponse checkOrder(@RequestParam String newCustomerId) {
	// 	CustomerOrderResponse newCustomerOrderResposnse = null;
	// 	try {
	// 		if (customerTokenMap.containsKey(newCustomerId)) {
	// 			newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,customerTokenMap.get(newCustomerId));
	// 		} else {
	// 			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 		}
	// 	} catch (Exception error) {
	// 		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	// 	}
	// 	return newCustomerOrderResposnse;
	// }
	
	// e.g. /token?customerId={ID}&noToken={TOKEN}
	//@RequestMapping(value = "/token", method = RequestMethod.POST)
	// @PostMapping("/customer_token")
	// // @RequestParam is for HTTP GET method
	// CustomerOrderResponse makeOrder(@RequestBody CustomerOrder newCustomerOrder) {
	// 	CustomerOrderResponse newCustomerOrderResposnse = null; 
	// 	try {
	// 		int numberOfToken = newCustomerOrder.getNoToken();
	// 		String newCustomerId = newCustomerOrder.getCustomerId();		
	// 		if (numberOfToken < 6 && numberOfToken >=1) {
	// 			if ((customerTokenMap.containsKey(newCustomerId) && customerTokenMap.get(newCustomerId)==null) || !(customerTokenMap.containsKey(newCustomerId))) {
	// 				ArrayList<String> tokenList = new ArrayList<String>() {{
	// 					for (int i=0; i<numberOfToken; i++){  
	// 						add(UUID.randomUUID().toString());
	// 					}
	// 				}};
	// 				customerTokenMap.put(newCustomerId,tokenList);
	// 				newCustomerOrderResposnse = new CustomerOrderResponse(newCustomerId,tokenList);
	// 			} else {
	// 				// spring HttpStatus https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
	// 				throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 			}
	// 		} else {
	// 			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
	// 		}
	// 	} catch (Exception error) {
	// 		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	// 	}
	// 	return newCustomerOrderResposnse;
	// }
	
}
