package com.example.itech2.config;

import java.io.IOException;
import java.io.InputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    private static final byte[] INIT_VECTOR = {
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F
    };

    private static final String SECRET_KEY_FILE = loadSecretKeyFileLocation();

    public static String encriptar(String valor) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR);
            SecretKeySpec secretKeySpec = new SecretKeySpec(ClaveSecretaGenerator.getSecretKey().getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encrypted = cipher.doFinal(valor.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String desencriptar(String valorEncriptado) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR);
            SecretKeySpec secretKeySpec = new SecretKeySpec(ClaveSecretaGenerator.getSecretKey().getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(valorEncriptado));
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String loadSecretKeyFileLocation() {
        Properties prop = new Properties();
        try (InputStream input = AESUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }

            prop.load(input);
            return prop.getProperty("secret.key.file");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static SecretKey generarClave() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
