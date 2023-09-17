package me.ankurpaul.jwtspringtutorial.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@PropertySource("classpath:application.yaml")
public class JwtService {
    private static String SECRET_KEY = "secret";

    @Autowired
    public JwtService(@Value("${jwt.signing_key}") String SECRET_KEY) {
        JwtService.SECRET_KEY = SECRET_KEY;
        System.out.println("================== " + JwtService.SECRET_KEY + "================== ");
    }

    /**
     * Extracts the username from the given JWT token.
     *
     * @param jwtToken JWT token from which the username is to be extracted.
     * @return Username from the given JWT token.
     */
    public String getUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    /**
     * Extracts a claim from the given JWT token.
     *
     * @param token         JWT token from which the claim is to be extracted.
     * @param claimResolver Function that resolves the claim from the given JWT token.
     * @param <T>           Type of the claim to be extracted.
     * @return Claim from the given JWT token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param jwtToken JWT token from which the claims are to be extracted.
     * @return Claims from the given JWT token.
     */
    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * Checks if the given JWT token is valid.
     *
     * @param token       JWT token to be checked.
     * @param userDetails User details of the user to be checked.
     * @return True if the given JWT token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token JWT token to be checked.
     * @return True if the given JWT token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token JWT token from which the expiration date is to be extracted.
     * @return Expiration date from the given JWT token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generates a JWT token with the given user details.
     *
     * @param userDetails User details of the user to be added to the JWT token.
     * @return JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with the given extra claims and user details.
     *
     * @param extraClaims Extra claims to be added to the JWT token.
     * @param userDetails User details of the user to be added to the JWT token.
     * @return JWT token.
     * @see UserDetails
     * @see Map
     * @see Claims
     * @see Jwts
     * @see SignatureAlgorithm
     * @see Keys
     * @see Key
     * @see Decoders
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if the given JWT token is valid.
     *
     * @return Key used to sign the JWT token.
     */
    private Key getSigningKey() {
        byte[] secretBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
