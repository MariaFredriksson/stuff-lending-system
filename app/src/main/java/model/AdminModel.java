package model;

import java.util.Random;

/**
 * The AdminModel class is responsible for creating new members with a unique member ID.
 */
public class AdminModel {
  
  /**
   * Creates a new AdminModel.
   */
  public AdminModel() { 
  }

  /**
   * Creates a new member with a unique member ID.
   *
   * @param name The name of the member.
   * @param email The email of the member.
   * @param mobileNumber The mobile number of the member.
   * @param creationDate The date when the member was created.
   *
   * @return The new member.
   */
  public Member createMember(String name, String email, String mobileNumber, int creationDate) {

    String memberId = generateMemberId(10);

    Member member = new Member(name, email, mobileNumber, memberId, creationDate);

    return member;

  }
  
  /**
   * Generates a random member ID.
   *
   * @param length The length of the member ID.
   *
   * @return The random member ID.
   */
  private String generateMemberId(int length) {
    String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(Characters.length());
      char randomChar = Characters.charAt(randomIndex);
      stringBuilder.append(randomChar);
    }

    return stringBuilder.toString();
  }
}

