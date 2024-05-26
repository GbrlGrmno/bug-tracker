package com.gabrielgermano.bugtrackerbackend;

import com.gabrielgermano.bugtrackerbackend.model.Role;
import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BugTrackerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository, PasswordEncoder passwordEncoder) {
		return runner -> {
			User admin = User.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					.email("admin@admin.com")
					.role(Role.ADMIN)
					.build();
			repository.save(admin);

			User developer1 = User.builder()
					.username("dev1")
					.password(passwordEncoder.encode("123"))
					.email("dev1@gmail.com")
					.role(Role.DEVELOPER)
					.build();
			repository.save(developer1);

			User developer2 = User.builder()
					.username("dev2")
					.password(passwordEncoder.encode("123"))
					.email("dev2@gmail.com")
					.role(Role.DEVELOPER)
					.build();
			repository.save(developer2);

			User developer3 = User.builder()
					.username("dev3")
					.password(passwordEncoder.encode("123"))
					.email("dev3@gmail.com")
					.role(Role.DEVELOPER)
					.build();
			repository.save(developer3);
		};
	}
}
