package learn.hhs.textlearn.decrypt;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import learn.hhs.textlearn.digest.GenerateDigest;

public class DecryptText
{
    public String decryptData(String cipherText, String passKey,String encryptionStandard) throws
            UnsupportedEncodingException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException
    {
        GenerateDigest generateDigest = new GenerateDigest();
        SecretKeySpec key = generateDigest.generateKey(passKey,encryptionStandard);
        Cipher cipher = Cipher.getInstance(encryptionStandard);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeValue = Base64.decode(cipherText, Base64.DEFAULT);
        byte[] decValue = cipher.doFinal(decodeValue);
        String decryptedData = new String(decValue);
        return decryptedData;
    }
}
