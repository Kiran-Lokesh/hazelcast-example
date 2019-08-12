package solutions.egen.kiran.hazelcastexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HazelcastExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastExampleApplication.class, args);
	}

}
