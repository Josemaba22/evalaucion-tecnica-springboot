package com.josemaba.springboot.client.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {
	
	@GetMapping("/cifrado")
	public String encrypted(@RequestParam(required = false) String str){
		
	    try {
	        
	        String key = "0123456789abcdef"; // Clave de 128 bits (16 bytes)
	        String iv = "fedcba9876543210"; // Vector de inicialización de 128 bits (16 bytes)
	        
	        String ciphertext = encrypt(str, key, iv);
	        System.out.println("Texto cifrado: " + ciphertext);
	        
	        String decryptedText = decrypt(ciphertext, key, iv);
	        System.out.println("Texto descifrado: " + decryptedText);
	        return "Texto cifrado: " + ciphertext + "\nTexto descifrado:" + decryptedText;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static String encrypt(String plaintext, String key, String iv) throws Exception {
	    // Convertir la clave y el vector de inicialización a bytes
	    byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	    byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
	    
	    // Crear un objeto SecretKeySpec utilizando la clave
	    SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
	    
	    // Crear un objeto Cipher y configurarlo para el cifrado
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
	    
	    // Cifrar el texto plano
	    byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
	    
	    // Codificar los bytes cifrados utilizando Base64
	    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
	    
	    return encryptedText;
	}
	
	public static String decrypt(String ciphertext, String key, String iv) throws Exception {
	    // Convertir la clave y el vector de inicialización a bytes
	    byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	    byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
	    
	    // Crear un objeto SecretKeySpec utilizando la clave
	    SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
	    
	    // Crear un objeto Cipher y configurarlo para el descifrado
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
	    
	    // Decodificar el texto cifrado utilizando Base64
	    byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
	    
	    // Descifrar los bytes cifrados
	    byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
	    
	    // Convertir los bytes descifrados a texto plano
	    String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
	    
	    return decryptedText;
	}
}