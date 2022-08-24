package dtustudent.dtupay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dtustudent.dtupay.model.Tokens;

public interface TokenRepository extends JpaRepository<Tokens, Long> {
  List<Tokens> findByCustomerId(String customerId); //findBy<Field>

  //List<Tokens> findByTitleContaining(String title);
}
