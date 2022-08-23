package dtupay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import dtupay.Tokens;
@Repository
public interface CustomerTokenRepository extends JpaRepository<Tokens, Long> {
}