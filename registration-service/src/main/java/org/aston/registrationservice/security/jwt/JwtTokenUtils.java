package org.aston.registrationservice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;


@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
//        List<String> rolesList = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
        claims.put("role", userDetails.getAuthorities().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("User has no roles"))
                .getAuthority());

        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {

        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
       // return getAllClaimsFromToken(token).get("roles", List.class);
        return Collections.singletonList(getAllClaimsFromToken(token).get("role", String.class));

    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
