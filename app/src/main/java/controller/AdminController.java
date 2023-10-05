package controller;

import java.util.ArrayList;
import model.AdminModel;
import model.Member;
import view.AdminView;

/**
 * The AdminController class is responsible for controlling interactions between the
 * AdminModel and AdminView. It manages the creation, editing, and deletion of members,
 * as well as displaying member information.
 */
public class AdminController {

  private ArrayList<Member> members;
  private AdminModel adminModel;
  private AdminView adminView;

  /**
   * Constructor to initialize the AdminController with an empty member list and instances
   * of AdminModel and AdminView.
   */
  public AdminController() {
    members = new ArrayList<>();
    adminModel = new AdminModel();
    adminView = new AdminView();
  }

  /**
   * Prompts the user to create a new member and adds the member to the list of members.
   *
   * @param todaysDate The current date for member creation.
   */
  public void createMemberPrompt(int todaysDate) {
    adminView.print("Create new member");
    String name = adminView.prompt("Enter name:");
    String email = adminView.prompt("Enter email:");
    String mobileNumber = adminView.prompt("Enter mobile number:");
    createMember(name, email, mobileNumber, todaysDate);
    adminView.print("Member created!");
  }
  
  /**
   * Creates a new member with the given details and adds it to the list of members.
   *
   * @param name The name of the member.
   * @param email The email of the member.
   * @param mobileNumber The mobile number of the member.
   * @param creationDate The date the member was created.
   *
   * @return The created member.
   */
  public Member createMember(String name, String email, String mobileNumber, int creationDate) {
    // Validate the email and mobile number
    validateEmail(email);
    validateMobileNumber(mobileNumber);
    // Create a new member using the admin instance
    Member member = adminModel.createMember(name, email, mobileNumber, creationDate);
    addMemberToList(member);
    return member;
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
   * Prompts the user to edit a member and edits the member.
   */
  public void editMemberPrompt() {
    ArrayList<Member> memberList = getMembers();

    // Ask the user which member to edit
    int memberIndex = adminView.editMemberPrompt(memberList);

    // Get the member from the list of members
    Member member = memberList.get(memberIndex - 1);

    // Ask the user for the new name, email and mobile number
    String newName = adminView.prompt("Enter name:");
    String newEmail = adminView.prompt("Enter email:");
    String newMobileNumber = adminView.prompt("Enter mobile number:");

    editMember(member, newName, newEmail, newMobileNumber);

    adminView.print("Member edited!");
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
   * Prompts the user to delete a member and deletes the member.
   */
  public void deleteMemberPrompt() {
    ArrayList<Member> memberList = getMembers();

    // Ask the user which member to delete
    int memberIndex = adminView.deleteMemberPrompt(memberList);

    // Get the member from the list of members
    Member member = memberList.get(memberIndex - 1);

    deleteMember(member);
  }

  /**
   * Deletes the given member.
   *
   * @param member The member to delete.
   */
  public void deleteMember(Member member) {
    members.remove(member);
  }

  /**
   * Prompts the user to view all members and displays the members.
   */
  public void viewAllMembers() {
    ArrayList<Member> memberList = getMembers();

    // Ask the user in which way they want to view the members
    int viewOption = Integer.parseInt(adminView.prompt("View members in:\n1. A simple way\n2. A verbose way"));

    if (viewOption == 1) {
      // Print the list of members
      adminView.printMemberListSimple(memberList);
    } else if (viewOption == 2) {
      // Print the list of members
      adminView.printMemberListVerbose(memberList);
    }
  }
}

