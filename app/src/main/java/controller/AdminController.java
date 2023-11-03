package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.AdminModel;
import model.Contract;
import model.Item;
import model.Member;
import model.Time;
import view.AdminView;
import view.AdminView.MemberListAction;

/**
 * The AdminController class is responsible for controlling interactions between the
 * AdminModel and AdminView. It manages the creation, editing, and deletion of members,
 * as well as displaying member information.
 */
public class AdminController {

  private AdminModel adminModel;
  private AdminView adminView;

  /**
   * Constructor to initialize the AdminController with an empty member list and instances
   * of AdminModel and AdminView.
   */
  public AdminController() {
    adminModel = new AdminModel();
    adminView = new AdminView();
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a getter method.")
  public AdminModel getAdminModel() {
    return adminModel;
  }

  /**
   * Returns the list of members.
   *
   * @return The list of members.
   */
  public ArrayList<Member> getMembers() {
    return adminModel.getMembers();
  }

  /**
   * Prompts the user to create a new member and adds the member to the list of members.
   *
   * @param todaysDate The current date for member creation.
   */
  public void createMemberPrompt(int todaysDate) {
    try {
      adminView.displayCreateMemberPrompt();

      String name = adminView.promptForName();
      String email = adminView.promptForEmail();
      String mobileNumber = adminView.promptForMobileNumber();

      adminModel.createMember(name, email, mobileNumber, todaysDate);
      
      adminView.displayMemberCreatedSuccessfully();
    } catch (Exception e) {
      adminView.print(e.getMessage());
    }
  }

  /**
   * Prompts the user to edit a member and edits the member.
   */
  public void editMemberPrompt() {
    try {
      ArrayList<Member> memberList = getMembers();
  
      // Ask the user which member to edit
      int memberIndex = adminView.editMemberPrompt(memberList);
  
      // Get the member from the list of members
      Member member = memberList.get(memberIndex - 1);
  
      // Ask the user for the new name, email and mobile number
      String newName = adminView.promptForName();
      String newEmail = adminView.promptForEmail();
      String newMobileNumber = adminView.promptForMobileNumber();
  
      adminModel.editMember(member, newName, newEmail, newMobileNumber);
  
      adminView.displayMemberEditedSuccessfully();
      
    } catch (Exception e) {
      adminView.print(e.getMessage());
    }
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

    adminModel.deleteMember(member);
  }

  /**
   * Prompts the user to view all members and displays the members.
   */
  public void viewAllMembers() {
    ArrayList<Member> memberList = getMembers();

    adminView.printMemberListMenu();

    MemberListAction action = adminView.getMemberListAction(adminView.readLine());

    switch (action) {
      case SIMPLE:
        adminView.printMemberListSimple(memberList);
        break;
      case VERBOSE:
        adminView.printMemberListVerbose(memberList);
        break;
      default:
        // ^^ Ska vi ha denna default?
        adminView.printInvalidInput();
        break;
    }
  }

  // ^^ Is it okay to get another controller as an input argument?
  /**
   * Prompts the user to view the admin menu and performs the selected action.
   *
   * @param time The time object.
   * @param memberController The member controller.
   */
  public void viewAdminMenu(Time time, MemberController memberController) {
    // Ask what the user wants to do
    int action = Integer.parseInt(adminView.prompt("What do you want to do?\n1. Increase day count with one"));
    if (action == 1) {
      increaseDayCount(time, memberController);
    }
  }

  /**
   * Increases the day count by one and checks for expired contracts.
   *
   * @param time The time object.
   * @param memberController The member controller.
   */
  public void increaseDayCount(Time time, MemberController memberController) {
    time.advanceDayCounter();

    // Print the new date
    adminView.print("The new date is: " + time.getTodaysDate());

    checkForExpiredContracts(time, memberController);
  }

  // ^^ Should this be here or in the member controller, or in a model?
  private void checkForExpiredContracts(Time time, MemberController memberController) {
    // Get all items that exist in the program
    ArrayList<Item> items = memberController.getAllItems(getMembers());

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
}
