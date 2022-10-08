// package dtustudent.dtupay.config;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import dtustudent.dtupay.repository.CustomerRepository;
// import dtustudent.dtupay.model.Customer;

// import dtustudent.dtupay.repository.TokenRepository;
// import dtustudent.dtupay.model.Token;

// @Configuration
// public class PsqlConfig {

//   private static final Logger log = LoggerFactory.getLogger(PsqlConfig.class);

//   @Bean
//   CommandLineRunner initDatabase(CustomerRepository customerRepo, TokenRepository tokenRepo) {

//     return args -> {
//       log.info("Preloading " + customerRepo.save(new Customer("Wenhao")));
//       log.info("Preloading " + tokenRepo.save(new Token("f8b580a7-c7ec-4773-83c4-0d5cb4cb93aa")));
//     };
//   }
// }