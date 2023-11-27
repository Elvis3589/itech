package com.example.config;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class ClaveSecretaGenerator {

    private static final String SECRET_KEY_FILE = "secret.key";

    public static SecretKey getSecretKey() {
        SecretKey secretKey = loadSecretKey();
        if (secretKey == null) {
            secretKey = generateSecretKey();
            saveSecretKey(secretKey);
        }
        return secretKey;
    }

    private static SecretKey loadSecretKey() {
        try (InputStream inputStream = ClaveSecretaGenerator.class.getClassLoader().getResourceAsStream(SECRET_KEY_FILE)) {
            if (inputStream != null) {
                byte[] keyBytes = inputStream.readAllBytes();
                return new SecretKeySpec(Base64.getDecoder().decode(keyBytes), "AES");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveSecretKey(SecretKey secretKey) {
        try {
            byte[] keyBytes = secretKey.getEncoded();
            Files.write(Paths.get(SECRET_KEY_FILE), Base64.getEncoder().encode(keyBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SecretKey generateSecretKey() {
        return AESUtil.generarClave();
    }
}
