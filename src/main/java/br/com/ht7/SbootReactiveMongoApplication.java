package br.com.ht7;

import br.com.ht7.documents.Employee;
import br.com.ht7.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SbootReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbootReactiveMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(EmployeeRepository repository) {
		return args -> {
			repository.deleteAll()
					.subscribe(null, null, () -> {
						System.out.println("All data deleted");

						Stream.of(
								new Employee(UUID.randomUUID().toString(), "Tiago", new BigDecimal("1500.00")),
								new Employee(UUID.randomUUID().toString(), "Bill", new BigDecimal("2000.00")),
								new Employee(UUID.randomUUID().toString(), "Steve", new BigDecimal("3500.00")),
								new Employee(UUID.randomUUID().toString(), "Tesla", new BigDecimal("10500.00"))
						).forEach(employee -> {
							repository.save(employee).subscribe(System.out::println);
						});
					});
		};
	}
}
