package com.example.autostore.config;

import com.example.autostore.model.AppUser;
import com.example.autostore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class WebConfig {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // ✅ CORS cho API
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")   // ✅ Cho phép mọi header
                        .allowCredentials(false);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/uploads/**")
                        .addResourceLocations("file:uploads/");
            }

        };
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                var user = AppUser.builder()
                        .userName("admin")
                        .userPassword(passwordEncoder.encode("admin"))
                        .userEmail("admin@example.com")
                        .role("ADMIN") // dùng hằng số hoặc hardcode
                        .build();
                userRepository.save(user);
                log.info("Admin user created with username: {}", user.getUserName());
            }
        };
    }
}
