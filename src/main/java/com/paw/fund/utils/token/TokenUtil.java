package com.paw.fund.utils.token;

import com.paw.fund.configuration.handler.exceptions.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenUtil {

    @Value("${app.access-token.secret-key}")
    String secretKey;

    @Value("${app.access-token.expired-duration}")
    Integer expiredDuration;

    @Value("${app.refresh-token.expired-duration}")
    Integer refreshDuration;

    public String bearerToken(String token) {
        return "Bearer %s".formatted(token);
    }

    public String generateAccessToken(String identification) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .subject(identification)
                .expiration(accessTokenExpiredAt())
                .issuedAt(new Date())
                .compact();

    }

    public Date accessTokenExpiredAt() {
        return Date.from(Instant.now().plus(expiredDuration, ChronoUnit.MINUTES));
    }

    public Date refreshTokenExpiredAt() {
        return Date.from(Instant.now().plus(refreshDuration, ChronoUnit.MINUTES));
    }

    public String getIdentityFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")) {

            return getUserIdentifyFromToken(token.substring(7));
        }

        return null;
    }

    public String getUserIdentifyFromToken(String token) {
        return Jwts.parser()
                .verifyWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException(e.getMessage());
        }

    }
}
