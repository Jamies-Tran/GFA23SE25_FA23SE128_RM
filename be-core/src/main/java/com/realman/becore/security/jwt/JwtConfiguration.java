package com.realman.becore.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.realman.becore.error_handlers.exceptions.InvalidJwtException;
import com.realman.becore.util.AppUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtConfiguration {
    @Value("${JwtSecretKey}")
    private String jwtSecretKey;
    @Value("${JwtExpiredTime}")
    private Long jwtExpiredTime;

    public String generateJwt(String phone) {
        Instant expiredTime = Instant.now().plusSeconds(jwtExpiredTime);
        return Jwts.builder().setIssuedAt(new Date()).setExpiration(Date.from(expiredTime)).setSubject(phone)
                .signWith(Keys.hmacShaKeyFor(jwtSecretKey.getBytes())).compact();
    }

    public LocalDateTime expireTime() {
        return LocalDateTime.ofInstant(Instant.now().plusSeconds(jwtExpiredTime), ZoneId.systemDefault());
    }

    private Claims getClaimsFromJwt(String jwt) {
        return (Claims) Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecretKey.getBytes())).build()
                .parse(jwt).getBody();
    }

    private String getUsernameFromClaim(Claims claims) {
        return claims.getSubject();
    }

    public String getJwtFromRequestHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        
        if (AppUtil.stringHasLength(authorization)) {
            if (authorization.startsWith("Bearer ")) {
                String jwt = authorization.substring(7);
                validateJwtToken(jwt);
                Claims claims = this.getClaimsFromJwt(jwt);
                return this.getUsernameFromClaim(claims);
            }
        }

        return null;
    }

    public void validateJwtToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecretKey.getBytes())).build().parse(jwt);
        } catch (MalformedJwtException | SignatureException | ExpiredJwtException | IllegalArgumentException exc) {
            throw new InvalidJwtException(exc.getMessage());
        }
    }
}
