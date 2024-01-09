package mx.bidgroup.tec.tni.nomibanco.configs.security;
 
import java.util.Arrays;
import java.util.Collections;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.authentication.AuthenticationProvider; 
import org.springframework.security.config.Customizer; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; 
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;



import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mx.bidgroup.tec.tni.nomibanco.configs.security.jwt.JwtAuthenticationFilter;
 

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    
    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

		http
			 .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/auth/**", "/prueba/admin")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/prueba/admin").hasAuthority("admin")
						.anyRequest().authenticated())
				// .csrf((csrf) -> csrf.ignoringRequestMatchers("/auth/**"))
				.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration corsConfiguration = new CorsConfiguration();
						corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
						corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
						corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
						corsConfiguration.setAllowCredentials(true);
						corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
						corsConfiguration.setMaxAge(3600L);
						return corsConfiguration;
					}
				}))
				.httpBasic(Customizer.withDefaults())
				// .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				 .addFilterBefore(jwtAuthenticationFilter,
				 UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
						.accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
 

		return http.build();

	}
 
}
