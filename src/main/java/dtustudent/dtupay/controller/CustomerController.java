package dtustudent.dtupay.controller;
// Java SE
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Spring Framework
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import dtustudent.dtupay.model.Token;
// import dtustudent.dtupay.repository.TokenRepository;
import dtustudent.dtupay.model.Customer;
import dtustudent.dtupay.service.CustomerService;

import org.springframework.hateoas.EntityModel;
@RestController
//@RequestMapping("/api")
public class CustomerController {

	// private TokenRepository tokenRepo;

	// public CustomerController(TokenRepository repo) {
	// 	this.tokenRepo = repo;
	// }
    // org.springframework.http.HttpStatus
    // org.springframework.http.ResponseEntity;
    // Use Extention of JpaRepository object to use the method of the super class and store explicitly List<TokenInfo> then return new ResponseEntity<>(tokens, HttpStatus.OK).
	// @GetMapping("/tokens")
	// public ResponseEntity<List<TokenInfo>> getAllTokens() {
	// 	// try {
	// 	 	List<TokenInfo> tokens = new ArrayList<TokenInfo>();
	// 		tokenRepo.findAll().forEach(tokens::add);	//need to cache database data		
	// 		return new ResponseEntity<>(tokens, HttpStatus.OK);
	// 	// } catch (Exception e) {
	// 	// 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	// 	// }
	// }
	private CustomerService customerService;

	public CustomerController(CustomerService service){
		this.customerService = service;
	}

    @GetMapping("/customer/{id}")
    public EntityModel<Customer> getCustomer(@PathVariable String id) { 
		return customerService.getCustomer(id);
	}

}
