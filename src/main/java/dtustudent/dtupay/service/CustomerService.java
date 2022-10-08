package dtustudent.dtupay.service;
 
import dtustudent.dtupay.model.Customer;
import dtustudent.dtupay.repository.CustomerRepository;
import dtustudent.dtupay.controller.CustomerController;
import dtustudent.dtupay.exception.CustomerNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerService {
    private CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public EntityModel<Customer> getCustomer(String id) {
        Customer customer = repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel();
        return EntityModel.of(customer,link);
        //return EntityModel.of(customer,linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel());
    }

}