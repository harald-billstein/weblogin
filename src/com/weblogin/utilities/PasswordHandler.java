package com.weblogin.utilities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PasswordHandler implements Serializable {

  private static final long serialVersionUID = -4005481840121816932L;

  private String alphabetString = "ABCDEFGHILJKLMNOPQRSTUVXYZabcdefghiljklmnopqrstuvxyz";

  public PasswordHandler() {}

  public List<String> getPossibleMatches(String password, String salt)
      throws NoSuchAlgorithmException {
    shuffle();
    List<String> posMatches = new ArrayList<>();

    int index = 0;
    for (int i = 0; i < alphabetString.length(); i++) {
      String buildingPosHash = addSalt(salt, password);
      buildingPosHash += alphabetString.substring(index, (index + 1));
      buildingPosHash = getHasched(buildingPosHash);
      posMatches.add(buildingPosHash);
      index++;
    }
    return posMatches;
  }

  private String addSalt(String salt, String password) {
    return password + salt;
  }

  private String getHasched(String pepper) throws NoSuchAlgorithmException {

    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(pepper.getBytes());

    byte byteData[] = md.digest();
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }

    return sb.toString();
  }

  public String createHashedPassword(String password, String salt) {
    String saltedPassword = addSalt(salt, password);
    String pepperdPassword = addPepper(saltedPassword);
    String hashedPassword = null;

    try {
      hashedPassword = getHasched(pepperdPassword);
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return hashedPassword;
  }

  private String addPepper(String salted) {
    shuffle();
    return salted + alphabetString.substring(0, 1);
  }

  private void shuffle() {

    List<Character> characters = new ArrayList<Character>();

    for (char c : alphabetString.toCharArray()) {
      characters.add(c);
    }

    StringBuilder shuffledString = new StringBuilder(alphabetString.length());

    while (characters.size() != 0) {
      int randPicker = (int) (Math.random() * characters.size());
      shuffledString.append(characters.remove(randPicker));
    }

    alphabetString = shuffledString.toString();
  }
}