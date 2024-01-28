package com.gabrielgermano.bugtrackerbackend;

import com.gabrielgermano.bugtrackerbackend.model.Role;
import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
					.build();
			repository.save(admin);
		};
	}
}
