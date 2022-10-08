package dtustudent.dtupay.repository;

// Inheritance, inherits JpaRepository https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
import org.springframework.data.jpa.repository.JpaRepository;

// Required java class, to match object to the database table
import dtustudent.dtupay.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
