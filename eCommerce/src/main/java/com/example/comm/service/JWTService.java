package com.example.comm.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	public String generateToken(String userName) {
		Map<String, Object> c = new HashMap<>();
		
		return Jwts
				.builder()
				.subject(userName)
				.claims()
				.add(c)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
				.and()
				.signWith(getKey())
				.compact();
			
	}

	private SecretKey getKey() {
		byte[] keyByte = null;
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keygen.generateKey();
			//String secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			String secretKey = "57c4a4d8557a4201e509575bf6c238f9a2ad0eb35d68233e6181e0f196e95509";
			keyByte = Decoders.BASE64.decode(secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return Keys.hmacShaKeyFor(keyByte);
	}

	public String extractUsername(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		System.out.println("user name: "+userName);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
