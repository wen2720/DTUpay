package dtustudent.dtupay.controller;
// Java SE
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

// HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

	// @GetMapping("/token/customer/{id}")
	// public List<Token> getTokenByForeignKey(@PathVariable String id) {
	// 	List<Token> tokens = tokenRepository.findAllByCustomerId(id);
	// 	return tokens;
	// }

	@GetMapping("/token/customer/{id}")
	public CollectionModel<EntityModel<Token>> getTokenByForeignKey(@PathVariable String id) {
		List<Token> tokens = tokenRepository.findAllByCustomerId(id);
		List<EntityModel<Token>> tokensEntityModel = tokens.stream()
		.map(tokenModelAssembler::toModel)
		.collect(Collectors.toList());
		return CollectionModel.of(tokensEntityModel, linkTo(methodOn(TokenController.class).getTokenByForeignKey(id)).withSelfRel());
	}

	// @GetMapping("/tokens")
	// public CollectionModel<EntityModel<Token>> all() {

	// List<EntityModel<Token>> tokens = tokenRepository.findAll().stream()
	// 	.map(token -> EntityModel.of(token,
	// 		linkTo(methodOn(TokenController.class).getToken(token.getId())).withSelfRel(),
	// 		linkTo(methodOn(TokenController.class).all()).withRel("tokens")))
	// 	.collect(Collectors.toList());

	// return CollectionModel.of(tokens, linkTo(methodOn(TokenController.class).all()).withSelfRel());
	// }

	@GetMapping("/tokens")
	public CollectionModel<EntityModel<Token>> all() {

	List<EntityModel<Token>> tokens = tokenRepository.findAll().stream()
		.map(tokenModelAssembler::toModel)
		.collect(Collectors.toList());

	return CollectionModel.of(tokens, linkTo(methodOn(TokenController.class).all()).withSelfRel());
	}
}

