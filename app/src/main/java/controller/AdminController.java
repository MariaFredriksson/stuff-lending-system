
package controller;

import java.util.ArrayList;
import model.Member;
import model.AdminModel;
import view.AdminView;

public class AdminController {

  private ArrayList<Member> members;
  private AdminModel adminModel; // Create an instance of model.Admin
  private AdminView adminView;

  public AdminController() {
    members = new ArrayList<>();
    adminModel = new AdminModel(); // Initialize the admin instance
    adminView = new AdminView();
  }

  public void createMemberPrompt(int todaysDate) {
    adminView.print("Create new member");
    String name = adminView.prompt("Enter name:");
    String email = adminView.prompt("Enter email:");
    String mobileNumber = adminView.prompt("Enter mobile number:");
    createMember(name, email, mobileNumber, todaysDate);
    adminView.print("Member created!");
  }
  
  public Member createMember(String name, String email, String mobileNumber, int creationDate) {
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);
    // Create a new member using the admin instance
    Member member = adminModel.createMember(name, email, mobileNumber, creationDate);
    addMemberToList(member);
    return member;
  }
  
  private void addMemberToList(Member member) {
    // Add the member to the list of members
    members.add(member);
  }

  public ArrayList<Member> getMembers() {
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

  private void validateMobileNumber(String mobileNumber) {
    members.forEach(member -> {
      if (member.getMobileNumber() == mobileNumber) {
        throw new IllegalArgumentException("Mobile number already exists");
      }
    });
  }

  public void editMemberPrompt() {
    ArrayList<Member> memberList = getMembers();

    // Ask the user which member to edit
    // ^^ Should we send the copy of the list of members to the view, or the original list?
    int memberIndex = adminView.editMemberPrompt(memberList);

    // Get the member from the list of members
    Member member = memberList.get(memberIndex - 1);
    // ^^ Or get the member from the original list of members?

    // Ask the user for the new name, email and mobile number
    String newName = adminView.prompt("Enter name:");
    String newEmail = adminView.prompt("Enter email:");
    String newMobileNumber = adminView.prompt("Enter mobile number:");

    editMember(member, newName, newEmail, newMobileNumber);
  }

  public void editMember(Member member, String name, String email, String mobileNumber) {
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);
    // Edit the member
    member.setName(name);
    member.setEmail(email);
    member.setMobileNumber(mobileNumber);
  }

  public void deleteMemberPrompt() {
    ArrayList<Member> memberList = getMembers();

    // Ask the user which member to delete
    // ^^ Should we send the copy of the list of members to the view, or the original list?
    int memberIndex = adminView.deleteMemberPrompt(memberList);

    // Get the member from the list of members
    Member member = memberList.get(memberIndex - 1);
    // ^^ Or get the member from the original list of members?

    deleteMember(member);
  }

  public void deleteMember(Member member) {
    // Delete the member
    members.remove(member);
  }

  public void viewAllMembers() {
    // ^^ Should we send the copy of the list of members to the view, or the original list?
    ArrayList<Member> memberList = getMembers();

    // Print the list of members
    adminView.printMemberList(memberList);}
}

