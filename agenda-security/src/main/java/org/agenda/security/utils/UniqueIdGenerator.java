/**
 * @since 30 juin 2019
 */
package org.agenda.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author LE MIERE Romain
 *
 */
public class UniqueIdGenerator {
	
	private static final String DEFAULT_ALGORITHM = "sha-1";

	private String algorithm = DEFAULT_ALGORITHM;
	
	/**
	 * 
	 */
	public UniqueIdGenerator() {
		super();
	}

	public UniqueIdGenerator(String algorithm) {
		this.algorithm = algorithm;
		try {
			MessageDigest.getInstance(algorithm);
		}catch(NoSuchAlgorithmException e) {
			this.algorithm = DEFAULT_ALGORITHM;
		}
	}

	public String generateRandomId() {
		StringBuilder builder = new StringBuilder();
		builder.append(LocalDate.now().toEpochDay());

		int bounds = ThreadLocalRandom.current().nextInt(20, 40 + 1);
		for (int i = 0; i < bounds; i++)
			builder.append(Character.toString((char) ThreadLocalRandom.current().nextInt(32, 126 + 1)));

		return this.hashText(builder.toString(), this.algorithm);
	}

	private String hashText(String text, String hashAlgorithm) {

		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			md.update(text.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;

	}

}
