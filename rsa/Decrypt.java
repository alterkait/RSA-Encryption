package rsa;

import java.math.BigInteger;

public class Decrypt {
	
	public static BigInteger decrypt(BigInteger encryptedMessage, BigInteger d, BigInteger n) {
		return encryptedMessage.modPow(d, n);
	}

}
