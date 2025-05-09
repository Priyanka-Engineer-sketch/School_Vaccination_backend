package com.schoolvaccination.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretBase64;

	@Value("${jwt.expiration:86400000}") // 1 day default
	private long expiration;

	private Key signingKey;

	@PostConstruct
	public void init() {
		byte[] keyBytes = Base64.getDecoder().decode(secretBase64);
		this.signingKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username, String role) {
		return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(signingKey, SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return parseToken(token).getBody().getSubject();
	}

	public String extractRole(String token) {
		return parseToken(token).getBody().get("role", String.class);
	}

	public boolean validateToken(String token) {
		try {
			parseToken(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	private Jws<Claims> parseToken(String token) {
		return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
	}
}