package dev.matheuslf.security.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

    private final SecutiryFilter secutiryFilter;

    public SecutiryConfig(SecutiryFilter secutiryFilter) {
        this.secutiryFilter = secutiryFilter;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configure(http))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated())
//                .addFilterBefore(secutiryFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // Erros do servidor sempre liberados
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()

                        // Rotas públicas:
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Acesso admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Dashboard USER/ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")

                        // ---- ÁREA DAS SUAS ENTIDADES ----

                        // Biblioteca - somente USER ou ADMIN
                        .requestMatchers("/biblioteca/**").hasAnyRole("USER", "ADMIN")

                        // Jogos - apenas ADMIN cria / edita / remove
                        .requestMatchers(HttpMethod.POST, "/jogos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jogos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jogos/**").hasRole("ADMIN")

                        // Jogos GET liberado (qualquer autenticado)
                        .requestMatchers(HttpMethod.GET, "/jogos/**").hasAnyRole("USER", "ADMIN")

                        // Perfil - apenas o próprio USER (ou ADMIN)
                        .requestMatchers("/perfil/**").hasAnyRole("USER", "ADMIN")

                        // Inventário - somente USER / ADMIN
                        .requestMatchers("/inventario/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/compras/**").hasAnyRole("USER", "ADMIN")


                        // Todas as demais exigem autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(secutiryFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}










