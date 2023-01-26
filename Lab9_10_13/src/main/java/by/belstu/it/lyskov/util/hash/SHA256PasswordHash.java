package by.belstu.it.lyskov.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256PasswordHash {
    private final static String HASH_ALGORITHM = "SHA-256";
    private static final SHA256PasswordHash instance = new SHA256PasswordHash();

    private SHA256PasswordHash() {
    }

    public static SHA256PasswordHash getInstance() {
        return instance;
    }

    public byte[] computeHash(byte[] password) {
        byte[] passwordHash;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            passwordHash = messageDigest.digest(password);
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            throw new RuntimeException("Error during password hashing", e);
        }
        return passwordHash;
    }
}
