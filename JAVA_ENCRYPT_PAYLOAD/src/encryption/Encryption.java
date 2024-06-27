package encryption;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Encryption {
	public static void main(String[] args) {
		
		String name = "Akhil";
		
		Keys key = getKeys();
		PublicKey publicKey = key.getPublicKey();
		PrivateKey privateKey = key.getPrivateKey();
		//storeKeys(key);
		
		byte[] encryptedData = encryptData(name.getBytes(),"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArZF+bmLFudosXCarBbJelkdiay8ZBHp5jKilOIk93zwgTz9/TOOX78guncqq6s7CzB5TDX0GJbUe+AnJGcyf0YWAMUUgdsr/nH5TrKZmLOuQVzWhxcxp5YFUg+5FiEY9uHrC3ahH2eLA6q7QDPitlRfu9bsyXtgMo6ISJSWKWXVGAlC7oOGmFnOmfiqtHkiF1gugpxqFWnPs6yRQt0Irbn9zOBtJgx/g6bsL+VGP/y93kbZ8zusW1450DWRJC50LHgL9o7+EWyNFjXUWmZeUTayHJrFD99Mqw+Rhgptbr9DxvBMldRQicHUGXSIu+uqWl4jNleTsbXM2LCCEkhXt5QIDAQAB");
		
		String encryptedDataString = new String(encryptedData);
		//System.out.println("Public Key : " + publicKey.toString());
		System.out.println("EncryptedData : " + encryptedDataString);
		
		System.out.println();
		
		byte[] decryptedData = decryptedData(encryptedData, privateKey);
		String decryptedDataString = new String(decryptedData);
		System.out.println("DecryptedData : " + decryptedDataString);
	}
	
	/*private static void storeKeys(Keys keys) {
		try {
			byte[] publicKeyEncoded = keys.getPublicKey().getEncoded();
			byte[] privateKeyEncoded = keys.getPrivateKey().getEncoded();			
			
			FileOutputStream fosPublic = new FileOutputStream("publicKey3.der");
			FileOutputStream fosPrivate = new FileOutputStream("privateKey3.der");
			
			fosPublic.write(publicKeyEncoded);
			fosPrivate.write(privateKeyEncoded);
			
			fosPrivate.close();
			fosPublic.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	static Keys getKeys() {
		try {
			FileInputStream fisPublic = new FileInputStream("C:/Users/Sreenivas Bandaru/IBM/ACET12/workspace/JAVA_ENCRYPT_PAYLOAD/publicKey.der");
			FileInputStream fisPrivate = new FileInputStream("C:/Users/Sreenivas Bandaru/IBM/ACET12/workspace/JAVA_ENCRYPT_PAYLOAD/privateKey.der");
			
			byte[] publicKeyEncoded = new byte[fisPublic.available()];
			fisPublic.read(publicKeyEncoded);
			
			byte[] privateKeyEncoded = new byte[fisPrivate.available()];
			fisPrivate.read(privateKeyEncoded);
			
			fisPublic.close();
			fisPrivate.close();
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyEncoded));
			PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyEncoded));
			
			return new Keys(publicKey,privateKey);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public static byte[] encryptData(byte[] input, String publicKeyString) {
		try {
			PublicKey publicKey = getPublicKeyFromRequest(publicKeyString);
			Cipher cipher  = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(input);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	static byte[] decryptedData(byte[] input, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	static PublicKey getPublicKeyFromRequest(String publicKey) {
		byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
		PublicKey actualPublicKey = null;
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			actualPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return actualPublicKey;
	}
}

