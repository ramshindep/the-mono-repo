package org.dnyanyog.utility;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator {

  private static final String CHARACTERS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final SecureRandom RANDOM = new SecureRandom();

  public static String generatePatientId() {
    return "APT" + generateRandomAlphaNumeric(8);
  }

  private static String generateRandomAlphaNumeric(int length) {
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
    }
    return builder.toString();
  }
}
