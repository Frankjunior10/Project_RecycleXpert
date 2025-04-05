package backend.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    //Creation the filtrate
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String[] AUTH_WHITELIST = {
            // Rutas públicas (Swagger, login, registro)
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",

            // --login
            "/api/users/login/**",

            // --register
            "/api/users/register/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Añadir filtro JWT antes de UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Habilitar CORS y desactivar CSRF
        http.cors(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        // Configuración de autorización
        http.authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll()
                /*

                        .requestMatchers(AUTH_WHITELIST).permitAll() // Permitir las rutas de la lista blanca sin autenticación
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/volunteers/register").hasAnyAuthority("ORGANIZACION")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/createOrganization").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/registerevent").hasAnyAuthority("VOLUNTARIO")

                        .anyRequest().authenticated() //
                 */

        );

        // Política sin estado para las sesiones (JWT no usa sesiones)
        http.sessionManagement(
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
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

