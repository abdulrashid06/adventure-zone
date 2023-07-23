//package com.adventure.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.crypto.SecretKey;
//import java.io.IOException;
//
//public class JwtTokenValidatorFilter extends OncePerRequestFilter {
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//				
//		String jwt= request.getHeader(SecurityDetails.JWT_HEADER);
//		if(jwt != null && jwt.startsWith("Bearer")) {
//
//			try {
//				String token  = jwt.substring(7).trim();
//				SecretKey key= Keys.hmacShaKeyFor(SecurityDetails.JWT_KEY.getBytes());
//				Claims claims= Jwts.parserBuilder().setSigningKey(key).build().
//				parseClaimsJws(token).getBody();
//				String username= String.valueOf(claims.get("email"));
//				String authorities = (String) claims.get("authorities");
//				Authentication auth = new UsernamePasswordAuthenticationToken(username, token, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
//				SecurityContextHolder.getContext().setAuthentication(auth);
//				System.out.println("validation sucess");
//			} catch (JwtException e) {
//				throw new BadCredentialsException(e.getMessage());
//			}
//						
//		}
//		filterChain.doFilter(request, response);
//	}
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		return (request.getServletPath().equals("/adventureZone/customer/signIn") || 
//				request.getServletPath().equals("/adventureZone/admin/signIn") );
//	}
//
//
//	
//	
//	
//	
//}
