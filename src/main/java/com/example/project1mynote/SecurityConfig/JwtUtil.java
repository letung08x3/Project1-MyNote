package com.example.project1mynote.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey jwtSecretKey; // Khóa bí mật đủ an toàn
    private long jwtExpirationMs = 60000; // Thời gian hết hạn token (1 phút)

    // Inject giá trị từ file cấu hình
    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration.ms}") long jwtExpirationMs) {
        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes()); // Tạo khóa bí mật từ chuỗi cấu hình
        this.jwtExpirationMs = jwtExpirationMs;
    }

    // Tạo JWT từ username
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    // Lấy username từ JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Xác thực JWT
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
