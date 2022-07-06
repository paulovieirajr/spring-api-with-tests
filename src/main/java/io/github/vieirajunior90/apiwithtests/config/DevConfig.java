package io.github.vieirajunior90.apiwithtests.config;

import io.github.vieirajunior90.apiwithtests.domain.User;
import io.github.vieirajunior90.apiwithtests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
public class DevConfig {

    private final UserRepository userRepository;

    public DevConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public void startDB() {
        User u1 = new User(null, "Junior", "junior@email.com", "123");
        User u2 = new User(null, "Vieira", "vieira@email.com", "123");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
