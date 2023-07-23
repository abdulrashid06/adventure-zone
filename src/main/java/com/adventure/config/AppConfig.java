package com.adventure.config;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class AppConfig {
// public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);



    @Bean
	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
		cors(cors -> cors.disable()
		// {
		// 	cors.configurationSource(new org.springframework.web.cors.CorsConfigurationSource(){
				
		// 		@Override
		// 		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		// 		CorsConfiguration configuration= new CorsConfiguration();
		// 			configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
		// 			configuration.setAllowedMethods(Collections.singletonList("*"));
		// 			configuration.setAllowCredentials(true);
		// 			configuration.setAllowedHeaders(Collections.singletonList("*"));
		// 			configuration.setExposedHeaders(Arrays.asList("Authorization"));
		// 			return configuration;
		// 		}
		// 	});
        // }
		) .authorizeHttpRequests(auth -> auth
        		.requestMatchers(HttpMethod.POST, "/adventureZone/**").permitAll()
        		 .requestMatchers(HttpMethod.GET, "/adventureZone/**").permitAll()
        		.requestMatchers(HttpMethod.DELETE, "/adventureZone/**").permitAll()
        		.requestMatchers(HttpMethod.PUT, "/adventureZone/**").permitAll()
       		// .requestMatchers("/customers/{customerId}","/admins/add").permitAll()
				.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				.requestMatchers("/adventureZone/customer/signIn").hasRole("USER")
				.requestMatchers("/adventureZone/admin/signIn").hasRole("ADMIN")
//				.requestMatchers("/hello").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/customers_list").hasRole("ADMIN")
				.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				 .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/adventureZone/logout"))
                .logoutSuccessUrl("www.google.com") // Redirect to home page after logout
                .invalidateHttpSession(true) // Invalidate the HttpSession
                .deleteCookies("JSESSIONID") // Remove JSESSIONID cookie
                .clearAuthentication(true) // Clear the authentication
                .logoutSuccessHandler((request, response, authentication) -> {
                    // Optional additional logout-related tasks if needed
                })
                .permitAll()
                .and()
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
}
