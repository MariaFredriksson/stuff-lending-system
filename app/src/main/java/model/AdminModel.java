package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The AdminModel class is responsible for creating new members with a unique member ID.
 */
public class AdminModel {
  
  private ArrayList<Member> members;
  
  /**
   * Creates a new AdminModel.
   */
  public AdminModel() { 
    members = new ArrayList<>();
  }
  
  /**
   * Returns a copy of the list of members.
   *
   * @return A copy of the list of members.
   *
   */
  public ArrayList<Member> getMembers() {
    // Return a copy of the list of members, so it is immutable
    return new ArrayList<Member>(members);
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
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);

    // Generate a random member ID
    String memberId = generateMemberId(10);

    Member member = new Member(name, email, mobileNumber, memberId, creationDate);
    addMemberToList(member);
    return member;
  }

  /**
   * Edits the given member with the given details.
   *
   * @param member The member to edit.
   * @param name The new name of the member.
   * @param email The new email of the member.
   * @param mobileNumber The new mobile number of the member.
   */
  public void editMember(Member member, String name, String email, String mobileNumber) {
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);
    // Edit the member
    member.setName(name);
    member.setEmail(email);
    member.setMobileNumber(mobileNumber);
  }
  
  /**
   * Deletes the given member.
   *
   * @param member The member to delete.
   */
  public void deleteMember(Member member) {
    members.remove(member);
  }

  public void checkForExpiredContracts(Time time, ArrayList<Item> items) {
    // Loop through all the items
    items.forEach(item -> {
      // Get all the contracts for the item
      ArrayList<Contract> contracts = item.getContractList();

      // Loop through all the contracts
      contracts.forEach(contract -> {
        // Check if the contract has expired and if the item is not set to available
        if (contract.getEndDate() < time.getTodaysDate() && !item.getIsAvailable()) {
          // Set the item to available
          item.setIsAvailable(true);
        }
      });
    });
  }
  
  /**
   * Generates a random member ID.
   *
   * @param length The length of the member ID.
   *
   * @return The random member ID.
   */
  private String generateMemberId(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(characters.length());
      char randomChar = characters.charAt(randomIndex);
      stringBuilder.append(randomChar);
    }

    return stringBuilder.toString();
  }

  private void validateEmail(String email) {
    members.forEach(member -> {
      if (member.getEmail().equals(email)) {
        throw new IllegalArgumentException("Email already exists");
      }
    });
  } 

  /**
   * Validates that the mobile number is unique.
   *
   * @param mobileNumber The mobile number to validate.
   *
   */
  private void validateMobileNumber(String mobileNumber) {
    members.forEach(member -> {
      if (member.getMobileNumber().equals(mobileNumber)) {
        throw new IllegalArgumentException("Mobile number already exists");
      }
    });
  }
    
  /**
   * Adds a member to the list of members.
   *
   * @param member The member to add.
   */
  private void addMemberToList(Member member) {
    // Add the member to the list of members
    members.add(member);
  }
}

