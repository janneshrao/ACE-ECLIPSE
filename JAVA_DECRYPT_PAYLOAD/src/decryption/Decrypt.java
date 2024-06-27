package decryption;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

public class Decrypt {

	public static void main(String[] args) {
		String encryptedData = ".R��#j��,��!��_8GvJ�C\f�\b<5�+���}�>���-h\b\"���4����υ��NJ�&��B�=$<�u;o� ��XƆ����L,<J�x�$1?9һ�ę�l3*�J3�(��}�V�gQ���w݃\bU7֬gU'� zF�=FcK��2Y��(߉�PVϸOH$`5�,%Na������\r\n" + 
				"tU�V��ڗ�̉�T�?�Ϝ�\"FUi������ahM|]��&f�����b?�`!���?�;6r1/�r�\b��F-�";
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
