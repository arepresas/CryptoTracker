package stream.arepresas.cryptotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptotrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CryptotrackerApplication.class, args);
  }
}
