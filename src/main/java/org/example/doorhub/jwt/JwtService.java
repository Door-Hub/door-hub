package org.example.doorhub.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class
JwtService {

    @Value("${security.jwt.key}")
    private String SECRET_KEY;

    public String generateToken(String phone, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(phone)
                .signWith(signKey())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)))
                .setIssuer("www.door-hub.com")
                .compact();
    }

    public String generateToken(String phone) {
        return generateToken(phone, Collections.emptyMap());
    }

    public Claims claims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
