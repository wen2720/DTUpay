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

// Application structure 
import dtustudent.dtupay.model.Customer;
import dtustudent.dtupay.repository.CustomerRepository;
import dtustudent.dtupay.assembler.CustomerModelAssembler;
import dtustudent.dtupay.exception.CustomerNotFoundException;


import org.springframework.hateoas.EntityModel;
@RestController
//@RequestMapping("/api")
public class CustomerController {

	private final CustomerModelAssembler customerModelAssembler;
	private final CustomerRepository customerRepository;

	public CustomerController(CustomerRepository repository, CustomerModelAssembler assembler){
		this.customerModelAssembler = assembler;
		this.customerRepository = repository;
	}

    @GetMapping("/customer/{id}")
    public EntityModel<Customer> getCustomer(@PathVariable String id) { 
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		return customerModelAssembler.toModel(customer);
	}
}
