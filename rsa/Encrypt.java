package rsa;

import java.math.BigInteger;
import java.util.Random;

public class Encrypt {
	
	private static BigInteger p;
	private static BigInteger q;
	private static BigInteger n;
	private static BigInteger phi;
	private static BigInteger e;
	private static BigInteger d;
	
	public static void generateKeyPair(int bitLength) {
		Random random = new Random();
		
		p = BigInteger.probablePrime(bitLength / 2, random);
		q = BigInteger.probablePrime(bitLength / 2, random);
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		do {
			e = new BigInteger(bitLength, random);
		}while(e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE));
		
		d = e.modInverse(phi);
	}
	
	public static BigInteger encrypt(BigInteger message) {
		return message.modPow(e, n);
	}
	
	public static void main(String[] args) {
        generateKeyPair(2048);
        BigInteger message = new BigInteger("Hello, RSA encryption!".getBytes());
        BigInteger encryptedMessage = encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage.toString());

        // Pass the encrypted message and the private key (d, n) to the RSADecrypt class
        BigInteger decryptedMessage = Decrypt.decrypt(encryptedMessage, d, n);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));
    }

}
