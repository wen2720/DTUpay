package dtustudent.dtupay.assembler;

// Application Static Type
import dtustudent.dtupay.model.Customer;
import dtustudent.dtupay.controller.CustomerController;

// Spring Framework
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// The class contains the business logic of the application.
@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer,EntityModel<Customer>> {
    
    @Override 
    public EntityModel<Customer> toModel(Customer customer) {
        // Link link = linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel();
        // return EntityModel.of(customer,link);
        return EntityModel.of(customer,linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel());
    }

}