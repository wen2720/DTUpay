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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Application structure 
import dtustudent.dtupay.model.Customer;
import dtustudent.dtupay.model.Token;
import dtustudent.dtupay.repository.CustomerRepository;
import dtustudent.dtupay.repository.TokenRepository;
// import dtustudent.dtupay.assembler.CustomerModelAssembler;
// import dtustudent.dtupay.assembler.TokenModelAssembler;
import dtustudent.dtupay.exception.CustomerNotFoundException;
import dtustudent.dtupay.exception.TokenNotFoundException;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.IanaLinkRelations;


@RestController
@RequestMapping("/customerservice")
public class CustomerController {

	//private final CustomerModelAssembler customerModelAssembler;
	private final CustomerRepository customerRepository;
	private final TokenRepository tokenRepository;
	// , CustomerModelAssembler customerModelAssembler
	public CustomerController(CustomerRepository customerRepo, TokenRepository tokenRepo){
		this.customerRepository = customerRepo;
		//this.customerModelAssembler = customerModelAssembler;
		this.tokenRepository = tokenRepo;
	}

    @GetMapping("/customer/{id}")
    public EntityModel<Customer> getCustomer(@PathVariable String id) { 
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		if (tokenRepository.existsByCustomerId(customer.getId())) {
			return EntityModel.of(customer, 
				linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel());
		} else {
			return EntityModel.of(customer, 
				linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel(),
				linkTo(methodOn(CustomerController.class).getCustomerToken(customer.getId())).withRel("tokens"));
		}
		
	}

	// insert entry to the token table
	@PostMapping("/customer/{id}/token")
    public ResponseEntity<?> getCustomerToken(@PathVariable String id) {  
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		if (tokenRepository.existsByCustomerId(customer.getId())) { 
			EntityModel<Customer> customerEntityModel = 
				EntityModel.of(customer, 
					linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel());
			return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
		} else {
			List<Link> tokenLinks= new ArrayList<Link>();
				for (int i = 0; i<4; i++) {
				Token token = new Token(id);
				tokenRepository.save(token);
				tokenLinks.add(linkTo(methodOn(CustomerController.class).getToken(token.getId())).withSelfRel());
			}
			// List<Token> tokens = tokenRepository.findAllByCustomerId(id);
			EntityModel<Customer> customerEntityModel = 
				EntityModel.of(customer, 
					tokenLinks
			);
			return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
		}
	}

	// token link
	@GetMapping("/token/{id}")
    public EntityModel<Token> getToken(@PathVariable String id) {  
		Token token = tokenRepository.findById(id).orElseThrow(() -> new TokenNotFoundException(id));
		EntityModel<Token> tokenrEntityModel = 
			EntityModel.of(token, 
				linkTo(methodOn(CustomerController.class).getCustomer(token.getId())).withSelfRel()
		);
 		return tokenrEntityModel;
	}
}
