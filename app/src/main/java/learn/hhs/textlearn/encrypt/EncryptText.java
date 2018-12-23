package learn.hhs.textlearn.encrypt;

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


/*
Get plain text and pass key and generate cipher text
Uses AES encryption
 */
public class EncryptText
{

    public String encryptData(String plainText, String passKey,String encryptionStandard) throws
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
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedFinal = cipher.doFinal(plainText.getBytes());
        String encryptedData = Base64.encodeToString(encryptedFinal, Base64.DEFAULT);
        return encryptedData;
    }

}
