package com.example.authenticationmicroservice.utils;

import com.example.authenticationmicroservice.exceptions.JWTExpiredException;
import com.example.authenticationmicroservice.model.AuthenticationRequest;
import com.example.authenticationmicroservice.model.JWTValidateResponse;
import com.example.authenticationmicroservice.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtils {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    private String SECRET_KEY = "secretkeythatnobodywillknowandiwillonlyknowthisisgettingabitannoyingtbh";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(AuthenticationRequest request) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, request.getUserName());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

    }

    public JWTValidateResponse validateToken(String token) {
        String username = "";
        try {
            username = extractUsername(token);
        }catch (SignatureException e){
            throw new SignatureException("JWT token not valid");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(isTokenExpired(token)){
            throw new JWTExpiredException("your token expired "+username);
        }
        boolean isvalid =  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        return new JWTValidateResponse(username,isvalid,"token is valid");
    }
}
