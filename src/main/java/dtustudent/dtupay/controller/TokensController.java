package dtustudent.dtupay.controller;
// Java SE
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dtustudent.dtupay.model.Tokens;
import dtustudent.dtupay.repository.TokenRepository;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
//@RequestMapping("/api")
public class TokensController {

	private TokenRepository tokenRepo;

	@Autowired
	public TokensController(TokenRepository repo) {
		this.tokenRepo = repo;
	}

	@GetMapping("/tokens")
	public ResponseEntity<List<Tokens>> getAllTokens() {
		// try {
		 	List<Tokens> tokens = new ArrayList<Tokens>();
			tokenRepo.findAll().forEach(tokens::add);	//need to cache database data		
			return new ResponseEntity<>(tokens, HttpStatus.OK);
		// } catch (Exception e) {
		// 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		// }
	}	
}
