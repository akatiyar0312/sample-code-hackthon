package com.example.payments.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

public class TokenUtil {

    // Secret key used to sign the JWT (must be same as used during token creation)
    // In real apps, read this from environment variable or config file securely!
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // example only

    private static Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    }

    public static Long extractUserId(String token) {
        try {
            // Remove "Bearer " prefix if present
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            // Assuming userId is stored as a claim "userId" as a number
            return claims.get("userId", Long.class);

        } catch (Exception e) {
            // Log or handle invalid token cases here
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
