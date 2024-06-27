package base64;
import java.util.Base64;

public class Decoder {

	public static void main(String[] args) {
		
		String str = "SSBhbSBmcm9tIEh5ZGVyYWJhZA==";
		System.out.println(decode(str));
	}

	public static byte[] decodeBase64(String encodedPayload) {
		// Decode Base64 encoded string to byte array
		return Base64.getDecoder().decode(encodedPayload);
	}

	public static String decode(String str) {
		String encodedPayload = str;
		try {
			byte[] decodedBytes = decodeBase64(encodedPayload);
			String decodedPayload = new String(decodedBytes);
			return decodedPayload;
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid Base64 input: " + e.getMessage());
		}
		return null;
	}
}
