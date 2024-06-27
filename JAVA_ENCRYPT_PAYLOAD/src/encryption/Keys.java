package encryption;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Keys {
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	Keys(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public PublicKey getPublicKey() {
		return this.publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}
}
