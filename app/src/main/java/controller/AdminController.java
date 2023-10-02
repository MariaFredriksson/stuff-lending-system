
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Member;
import model.AdminModel;

public class AdminController {

  private List<Member> members;
  private AdminModel admin; // Create an instance of model.Admin

  public AdminController() {
    members = new ArrayList<>();
    admin = new AdminModel(); // Initialize the admin instance
  }
  
  public Member createMember(String name, String email, int mobileNumber) {
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);
    // Create a new member using the admin instance
    Member member = admin.createMember(name, email, mobileNumber);
    addMember(member);
    return member;
  }
  
  private void addMember(Member member) {
    // Add the member to the list of members
    members.add(member);
  }

  public List<Member> getMembers() {
    // Return a copy of the list of members, so it is immutable
    return new ArrayList<Member>(members);
  }

  private void validateEmail(String email) {
    members.forEach(member -> {
      if (member.getEmail() == email) {
        throw new IllegalArgumentException("Email already exists");
      }
    });
  }

  private void validateMobileNumber(int mobileNumber) {
    members.forEach(member -> {
      if (member.getMobileNumber() == mobileNumber) {
        throw new IllegalArgumentException("Mobile number already exists");
      }
    });
  }



}

