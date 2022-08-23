package dtupay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dtupay.CustomerTokenRepository;

@Configuration
public class PreLoad {

  private static final Logger log = LoggerFactory.getLogger(PreLoad.class);

  @Bean
  CommandLineRunner initDatabase(CustomerTokenRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Tokens("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repository.save(new Tokens("Frodo Baggins", "thief")));
    };
  }
}