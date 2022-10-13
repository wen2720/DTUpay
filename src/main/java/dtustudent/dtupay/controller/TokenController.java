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
import dtustudent.dtupay.model.Token;
import dtustudent.dtupay.repository.TokenRepository;
import dtustudent.dtupay.assembler.TokenModelAssembler;
import dtustudent.dtupay.exception.TokenNotFoundException;


import org.springframework.hateoas.EntityModel;
@RestController
//@RequestMapping("/api")
public class TokenController {

	private final TokenModelAssembler tokenModelAssembler;
	private final TokenRepository tokenRepository;

	public TokenController(TokenRepository repository, TokenModelAssembler assembler){
		this.tokenModelAssembler = assembler;
		this.tokenRepository = repository;
	}

    @GetMapping("/token/{id}")
    public EntityModel<Token> getToken(@PathVariable String id) { 
		Token token = tokenRepository.findById(id).orElseThrow(() -> new TokenNotFoundException(id));
		return tokenModelAssembler.toModel(token);
	}
}
