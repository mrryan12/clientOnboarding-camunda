package com.realcoderz.encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;





@Component
public class EncryptDecryptUtils {
		
	private static String encryptionKey = "REALCODERZ123456";
	private static final String CHARACTERENCODING = "UTF-8";
	private static final String CIPHERTRANSFORMATION = "AES/CBC/PKCS5PADDING";
	private static final String AESENCRYPTIONALGORITHEM = "AES";
	
	
	public static String encrypt(String data) throws Exception {

		String encryptedText = "";
		try
		{
    		Cipher c = Cipher.getInstance(CIPHERTRANSFORMATION);
			byte[] key = encryptionKey.getBytes(CHARACTERENCODING);
			SecretKeySpec secretKey = new SecretKeySpec(key,AESENCRYPTIONALGORITHEM);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);		 
			c.init(Cipher.ENCRYPT_MODE, secretKey,ivparameterspec);	 
			byte[] cipherText = c.doFinal(data.getBytes("UTF8"));
			Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);
		}catch(Exception e) {
			System.err.println("Encrypt Exception : " + e.getMessage());
		}
		return encryptedText;
		
	}
	
	
	
	public static String Decrypt(String encryptedText) throws Exception {
		
		 String decryptedText = "";
	        try {
	            Cipher cipher = Cipher.getInstance(CIPHERTRANSFORMATION);
	            byte[] key = encryptionKey.getBytes(CHARACTERENCODING);
	            SecretKeySpec secretKey = new SecretKeySpec(key, AESENCRYPTIONALGORITHEM);
	            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
	            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
	            Base64.Decoder decoder = Base64.getDecoder();
	            byte[] test = encryptedText.getBytes("UTF8");
	            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
	            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");
	        } catch (Exception E) {
	            System.err.println("decrypt Exception : " + E.getMessage());
	        }
	        return decryptedText;
	    



	}
	

}
