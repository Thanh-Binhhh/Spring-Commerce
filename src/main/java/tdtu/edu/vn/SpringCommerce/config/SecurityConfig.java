package tdtu.edu.vn.SpringCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())    // // Use CORS configuration declared in WebConfig
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(   // Allow free access without login.
                                "/plants/**",
                                "/carts/**",
                                "/categories",
                                "/sizes",
                                "/characteristics")
                        .permitAll()
                        .anyRequest().denyAll() // Other APIs will be completely rejected
                );
        return http.build();
    }
}