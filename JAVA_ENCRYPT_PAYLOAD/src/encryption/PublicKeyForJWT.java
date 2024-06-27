package encryption;

import java.security.PublicKey;

public class PublicKeyForJWT {
	
	public static byte[] getPublicKey() {
		Keys keys = Encryption.getKeys();
		
		PublicKey publicKey = keys.getPublicKey();
		
		return publicKey.getEncoded();
	}

}
