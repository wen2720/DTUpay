package dtustudent.dtupay.repository;

// JAVA
import java.util.List;

// Inheritance, inherits JpaRepository https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
import org.springframework.data.jpa.repository.JpaRepository;

// HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

// Required java class, to match object to the database table
import dtustudent.dtupay.model.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
    List<Token> findAllByCustomerId(String customerId);
}
