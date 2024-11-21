package com.example.project1mynote.SecurityConfig;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512); // Tạo khóa bí mật an toàn
        String base64Key = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Khóa bí mật (Base64): " + base64Key);
    }
}
