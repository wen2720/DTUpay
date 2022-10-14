// package dtustudent.dtupay.assembler;

// import java.lang.Iterable;

// // Application Static Type
// import dtustudent.dtupay.model.Token;
// import dtustudent.dtupay.controller.TokenController;

// // Spring Framework
// import org.springframework.stereotype.Component;
// import org.springframework.hateoas.EntityModel;
// import org.springframework.hateoas.CollectionModel;
// import org.springframework.hateoas.Link;
// import org.springframework.hateoas.server.RepresentationModelAssembler;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// // TokenModelAssembler -> Token -> EntityModel<Token>
// @Component
// public class TokenModelAssembler implements RepresentationModelAssembler<Token,EntityModel<Token>> {
    
//     @Override 
//     public EntityModel<Token> toModel(Token token) {
//         // Link link = linkTo(methodOn(TokenController.class).getToken(id)).withSelfRel();
//         // return EntityModel.of(token,link);
//         // return EntityModel.of(token,linkTo(methodOn(TokenController.class).getToken(token.getId())).withSelfRel());
//         return EntityModel.of(token,
//             linkTo(methodOn(TokenController.class).getToken(token.getId())).withSelfRel());
//     }
//     // Failed at overriding the method.
//     // @Override 
//     // public CollectionModel<EntityModel<Token>> toCollectionModel(Iterable<? extends Token> tokens) {
//     //     return CollectionModel.of(tokens, linkTo(methodOn(TokenController.class).all()).withSelfRel());
//     // }

// }