package com.victor_tarnovski.banking.domain.vo;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public class Password implements Comparable<Password> {
  private String salt;
  private String hash;

  public Password(String input) {
    this(genSalt(), input);
  }

  public Password(String salt, String input) {
    Objects.requireNonNull(salt, "salt must not be null");
    this.salt = salt;

    Objects.requireNonNull(input, "input must not be null");
    if(input.isBlank())
      throw new IllegalArgumentException("input cannot be blank");
    this.hash = toHex(hash(salt, input));
  }

  public String salt() {
    return salt;
  }

  public String hash() {
    return hash;
  }

  @Override
  public int compareTo(Password other) {
    return hash.compareTo(other.hash);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || getClass() != other.getClass())
      return false;
    Password password = (Password) other;
    return compareTo(password) == 0;
  }

  public boolean equals(String input) {
    var password = from(input);
    return compareTo(password) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(salt, hash);
  }

  private static String genSalt() {
    var bytes = new byte[16];
    new SecureRandom().nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
  }

  private byte[] hash(String salt, String input) {
    try {
      var in = salt + input;
      var algorithm = MessageDigest.getInstance("SHA-256");
      var out = algorithm.digest(in.getBytes("UTF-8"));
      return out;
    } catch (Exception e) {
      throw new RuntimeException("cannot hash password", e);
    }
  }

  private String toHex(byte[] hash) {
    var builder = new StringBuilder();
    for (byte b : hash) {
      builder.append(String.format("%02X", 0xFF & b));
    }
    return builder.toString();
  }

  private Password from(String input) {
    return new Password(salt, input);
  }
}
