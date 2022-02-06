package com.wondacabinetinc.wondacabinetinc.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {
    private static final Logger LOG = LoggerFactory.getLogger(TokenUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(UserDetailsImpl userPrincipal){

       return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameForJWTToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch(SignatureException e){
            LOG.error("Invalid Signature: {}", e.getMessage());
        }
        catch(MalformedJwtException e){
            LOG.error("Malformed JWT Token: {}", e.getMessage());
        }
        catch(ExpiredJwtException e){
            LOG.error("Token expired: {}", e.getMessage());
        }
        catch(UnsupportedJwtException e){
            LOG.error("Unsupported JWT Token: {}", e.getMessage());
        }
        catch(IllegalArgumentException e){
            LOG.error("JWT Token empty: {}", e.getMessage());
        }
        return false;
    }
}
