package model;

import java.util.Random;

public class AdminModel {
  
  public AdminModel() { 
    
  }

  public Member createMember(String name, String email, int mobileNumber) {

    String memberID = generateRandomString(10);

    Member member = new Member(name, email, mobileNumber, memberID);

    return member;

  }
  
  private String generateRandomString(int length) {
      String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
      Random random = new Random();
      StringBuilder stringBuilder = new StringBuilder(length);

      for (int i = 0; i < length; i++) {
          int randomIndex = random.nextInt(CHARACTERS.length());
          char randomChar = CHARACTERS.charAt(randomIndex);
          stringBuilder.append(randomChar);
      }

      return stringBuilder.toString();
  }
}

