package mx.bidgroup.tec.tni.nomibanco.configs;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


import lombok.RequiredArgsConstructor;
import mx.bidgroup.tec.tni.nomibanco.dtos.RoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.RoleMenuEntity;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IUserRepository;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

     private final IUserRepository usuarioRespository;
   
    @Value("${jwt.public.key}")
	public RSAPublicKey key;
    @Value("${jwt.private.key}")
	RSAPrivateKey priv;
    
    // @Bean
    // public ModelMapper modelMapper() {
    //     return new ModelMapper();
    // }
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.createTypeMap(RoleMenuEntity.class, RoleMenuDto.class)
        .addMappings(mapper -> {
            mapper.map(src -> src.getId().getRoleId(), (dest, v) -> dest.getId().setId_rol((Long) v));
            mapper.map(src -> src.getId().getMenuId(), (dest, v) -> dest.getId().setId_menu((Long) v));
        });
		return modelMapper;
	}
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

    
	@Bean
	public UserDetailsService userDetailService() {
		return username -> usuarioRespository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not fournd"));
	}


	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.key).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	// @Bean
    // public OpenAPI customOpenAPI() {
    //     return new OpenAPI()
    //             .components(new Components().addSecuritySchemes("bearerAuth",
    //                     new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    // }

}
