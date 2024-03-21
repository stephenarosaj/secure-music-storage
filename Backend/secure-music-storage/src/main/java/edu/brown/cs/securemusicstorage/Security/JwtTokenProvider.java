package edu.brown.cs.securemusicstorage.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * JwtTokenProvider is a service class that provides methods to generate and validate JWT tokens.
 * It includes methods to generate a token for a given username, validate a token, and extract the username from a token.
 */
@Service
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    /**
     * Generates a JWT token for a given username.
     *
     * @param username The username for which the token is to be generated.
     * @return The generated JWT token.
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .claim("name", username)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    /**
     * Validates a JWT token.
     *
     * @param username  The username for which the token was generated.
     * @param authToken The JWT token to be validated.
     * @throws Exception if the token could not be verified.
     */
    public void validateToken(String username, String authToken) throws Exception {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build();
        try {
            jwtParser.parse(authToken);
            if (!username.equals(getUsernameFromJWT(authToken))) {
                throw new Exception("Token Invalid");
            }
        } catch (Exception e) {
            throw new Exception("Token Invalid", e);
        }
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token from which the username is to be extracted.
     * @return The extracted username.
     */
    public String getUsernameFromJWT(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token);
        return (String) claims.getBody().get("name");
    }
}
