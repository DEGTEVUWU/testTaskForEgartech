package testtask.testtaskforegartech;

import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestTaskForEgartechApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestTaskForEgartechApplication.class, args);
	}

}
