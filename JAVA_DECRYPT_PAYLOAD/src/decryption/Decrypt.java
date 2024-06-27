package decryption;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

public class Decrypt {

	public static void main(String[] args) {
		String encryptedData = ".R©š#jå‹ú,°Ø!ü·_8GvJˆC\fò\b<5¦+›•ó}‰>¨ÒÂ-h\b\"­¯¦4ÿ©øŠÏ…ôÙNJº&›¼Bü=$<ûu;oŒ £ï²XÆ†ŒšÕÉL,<JÑxÓ$1?9Ò»ÚÄ™šl3*ÓJ3ý(ˆÃ}°VøgQ“¬ÐwÝƒ\bU7Ö¬gU'À zF×=FcKÏü2YˆÕ(ß‰ôPVÏ¸OH$`5‹,%NaÆÇñÈúü\r\n" + 
				"tUè´V®ªÚ—ó­µÌ‰ùT¥?öÏœ‹\"FUi§§²ýÚáahM|]û€&fÛÑó‘Åýb?ÿ`!›’¦?¼;6r1/’rÓ\bÞ‰F-ö";
		byte[] input = encryptedData.getBytes();
		byte[] result = decryptData(input);
		
		System.out.println(result.toString());
	}

	public static byte[] decryptData(byte[] input) {
		try {
			PrivateKey privateKey = getPrivatekey();
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PrivateKey getPrivatekey() {
		PrivateKey privateKey = null;
		try {
			FileInputStream fisPrivate = new FileInputStream("C:/Users/Sreenivas Bandaru/IBM/ACET12/workspace/JAVA_ENCRYPT_PAYLOAD/privateKey.der");
			
			byte[] privateKeyEncoded = new byte[fisPrivate.available()];
			fisPrivate.read(privateKeyEncoded);
			fisPrivate.close();
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyEncoded));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return privateKey;
	}

}
