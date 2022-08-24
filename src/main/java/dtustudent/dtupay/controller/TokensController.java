package dtustudent.dtupay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Autowired
	TokenRepository tokenRepo;

	@GetMapping("/tokens")
	public ResponseEntity<List<Tokens>> getAllTokens() {//@RequestParam(required = false) String title
		// try {
		 	List<Tokens> tokens = new ArrayList<Tokens>();
			tokenRepo.findAll().forEach(tokens::add);	//need to cache database data
			// if (title == null)
			// 	tokenRepo.findAll().forEach(Tokens::add);
			// else
			// 	tokenRepo.findByTitleContaining(title).forEach(tokens::add);

			// if (tokens.isEmpty()) {
			// 	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// }

			return new ResponseEntity<>(tokens, HttpStatus.OK);
		// } catch (Exception e) {
		// 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		// }
	}

	// @GetMapping("/tokens/{id}")
	// public ResponseEntity<Tokens> getTokensById(@PathVariable("id") long id) {
	// 	Optional<Tokens> TokensData = tokenRepo.findById(id);

	// 	if (TokensData.isPresent()) {
	// 		return new ResponseEntity<>(TokensData.get(), HttpStatus.OK);
	// 	} else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// }

	// @PostMapping("/tokens")
	// public ResponseEntity<Tokens> createTokens(@RequestBody Tokens Tokens) {
	// 	try {
	// 		Tokens _Tokens = tokenRepo
	// 				.save(new Tokens(Tokens.getTitle(), Tokens.getDescription(), false));
	// 		return new ResponseEntity<>(_Tokens, HttpStatus.CREATED);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

	// @PutMapping("/tokens/{id}")
	// public ResponseEntity<Tokens> updateTokens(@PathVariable("id") long id, @RequestBody Tokens Tokens) {
	// 	Optional<Tokens> TokensData = tokenRepo.findById(id);

	// 	if (TokensData.isPresent()) {
	// 		Tokens _Tokens = TokensData.get();
	// 		_Tokens.setTitle(Tokens.getTitle());
	// 		_Tokens.setDescription(Tokens.getDescription());
	// 		_Tokens.setPublished(Tokens.isPublished());
	// 		return new ResponseEntity<>(tokenRepo.save(_Tokens), HttpStatus.OK);
	// 	} else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// }

	// @DeleteMapping("/tokens/{id}")
	// public ResponseEntity<HttpStatus> deleteTokens(@PathVariable("id") long id) {
	// 	try {
	// 		tokenRepo.deleteById(id);
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

	// @DeleteMapping("/tokens")
	// public ResponseEntity<HttpStatus> deleteAlltokens() {
	// 	try {
	// 		tokenRepo.deleteAll();
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}

	// }

	// @GetMapping("/tokens/published")
	// public ResponseEntity<List<Tokens>> findByPublished() {
	// 	try {
	// 		List<Tokens> tokens = tokenRepo.findByPublished(true);

	// 		if (tokens.isEmpty()) {
	// 			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 		}
	// 		return new ResponseEntity<>(tokens, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

}
