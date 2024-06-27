package encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Test {
	public static void main(String args[]) {
		
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
		
			System.out.println("RSA Public Key : " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
			System.out.println("RSA Private Key : " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			e.printStackTrace();
		}
	}

}
