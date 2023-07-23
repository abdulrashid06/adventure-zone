//package com.adventure.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//import org.springframework.security.config.Customizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfiguration;
//
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.security.Key;
//
//@Configuration
//@EnableWebSecurity
//public class AppConfig {
//// public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//
//
//	@Bean
//	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
//	    http.sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	        .cors(cors -> {
//	            cors.configurationSource(new org.springframework.web.cors.CorsConfigurationSource() {
//	                @Override
//	                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//	                    CorsConfiguration configuration = new CorsConfiguration();
//	                    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//	                    configuration.setAllowedMethods(Collections.singletonList("*"));
//	                    configuration.setAllowCredentials(true);
//	                    configuration.setAllowedHeaders(Collections.singletonList("*"));
//	                    configuration.setExposedHeaders(Arrays.asList("Authorization"));
//	                    return configuration;
//	                }
//	            });
//	        });
//	        // ... (other configurations)
////	        .authorizeHttpRequests(auth -> auth
////	            // ... (other route configurations)
////	            .requestMatchers(HttpMethod.GET, "/admins/customers_list").permitAll()
////	            .anyRequest().authenticated())
////	        .csrf(csrf -> csrf.disable())
////	        .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
////	        .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
////	        .logout()
////	        // ... (other logout configurations)
////	        .permitAll()
////	        .and()
////	        .httpBasic(Customizer.withDefaults());
//
//	    return http.build();
//	}
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//   
//}
