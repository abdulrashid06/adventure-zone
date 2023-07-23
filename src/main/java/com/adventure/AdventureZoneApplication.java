package com.adventure;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;

//import com.adventure.config.SecurityDetails;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;

@SpringBootApplication
public class AdventureZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdventureZoneApplication.class, args);
		// SecretKey key = Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes()) ;
		// 	String jwt = Jwts.builder()
		// 			.setIssuer("Raushan")
		// 			.setSubject("JWT_Token")
		// 			.claim("email", "ram@gmail.com") 
		// 			.claim("authorities", "USER")
		// 			.setIssuedAt(new Date())
		// 			.setExpiration(new Date( new Date().getTime()+20000000))
		// 			.signWith(key).compact() ;
		// 			System.out.println(jwt);
			// response.setHeader(SecurityDetails.JWT_HEADER, jwt);
		
		// jwt = jwt.substring(7);
				// key = Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes());
				// Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				// String username= String.valueOf(claims.get("email"));
				// String authorities = (String) claims.get("authorities");
				// Authentication auth = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
				// SecurityContextHolder.getContext().setAuthentication(auth);
				// System.out.println(claims);
	}

}
