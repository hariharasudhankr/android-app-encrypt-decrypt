package learn.hhs.textlearn.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.SecretKeySpec;

/*
Generate message digest with pass phrase and encryption standard
 */
public class GenerateDigest
{
    public SecretKeySpec generateKey(String passKey, String encryptionStandard) throws
            NoSuchAlgorithmException,
            UnsupportedEncodingException
    {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = passKey.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKey = new SecretKeySpec(key, encryptionStandard);
        return secretKey;
    }
}
