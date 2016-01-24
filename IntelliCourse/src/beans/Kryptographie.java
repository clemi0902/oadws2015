/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author marco
 */
public class Kryptographie {
    public SecretKeySpec defineKey() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        // Key definieren
        String keyStr = "oad";
        // byte-Array erzeugen
        byte[] key = (keyStr).getBytes("UTF-8");
        //Mit MD5 einen Hash-Wert erzeugen
        MessageDigest sha = MessageDigest.getInstance("MD5");
        key = sha.digest(key);
        //ersten 128 bit benutzten
        key = Arrays.copyOf(key, 16); 
        // Schlüssel erzeugen und returnen
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        
        return secretKeySpec;
    }
    public String encrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException
    {
        SecretKeySpec spec = defineKey();
        // Verschluesseln
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, spec);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        BASE64Encoder myEncoder = new BASE64Encoder();
        String geheim = myEncoder.encode(encrypted);
        return geheim;
    }
}
