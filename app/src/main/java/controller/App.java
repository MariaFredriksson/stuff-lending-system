package controller;

// import java.util.Date;

import model.Member;
import model.Item.ItemCategory;
import view.AdminView;
import view.AdminView.MainMenuAction;

import java.util.ArrayList;
import java.util.List;

import controller.AdminController;
import model.Time;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   * 
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    try {

      Time time = new Time();

      // Add a new member to the members list
      AdminController adminController = new AdminController();
      Member newMember = adminController.createMember("John Doe", "john.doe@example", "1234567891", time.getTodaysDate());
      System.out.println(newMember.getName());
      System.out.println(newMember.getCreationDate());

      // Edit the member
      adminController.editMember(newMember, "Jane Doe", "jane.doe@example", "0701234569");
      System.out.println(newMember.getName());

      // Delete the member
      // adminController.deleteMember(newMember);
      // System.out.println(adminController.getMembers());

        adminController.createMember("Lasse", "lasse.doe@mail.com", "1234567896", time.getTodaysDate());
      
      // Create an admin view
      AdminView adminView = new AdminView();

      // Print a welcome message when the application first starts
      adminView.print("Welcome to the Stuff Lending System!");

      // Set the result to something other than EXIT so the while-loop starts
      MainMenuAction result = MainMenuAction.NONE;

      // Show the main menu until the user exits
      while (result != MainMenuAction.EXIT) {
        adminView.mainMenu();
        result = adminView.getMainMenuAction(adminView.readLine());
        System.out.println(result);

        switch (result) {
          case CREATE_MEMBER:
            // ^^ Make this into its own method maybe? But where should it be placed?
            adminView.print("Create new member");
            String name = adminView.prompt("Enter name:");
            String email = adminView.prompt("Enter email:");
            String mobileNumber = adminView.prompt("Enter mobile number:");
            adminController.createMember(name, email, mobileNumber, time.getTodaysDate());
            adminView.print("Member created!");
            break;
          case EDIT_MEMBER:
            // ^^ Also its own method maybe?
            adminView.print("Edit member");
            adminView.print("Select member to edit:");
            List <Member> memberList = adminController.getMembers();
            for (int i = 0; i < memberList.size(); i++) {
              adminView.print(i + 1 + ". " + memberList.get(i).getName());
            }

            // ^^ Is this needed?
            // adminView.editMemberMenu();

            int memberIndex = Integer.parseInt(adminView.readLine());
            Member member = memberList.get(memberIndex - 1);
            String newName = adminView.prompt("Enter name:");
            String newEmail = adminView.prompt("Enter email:");
            String newMobileNumber = adminView.prompt("Enter mobile number:");
            adminController.editMember(member, newName, newEmail, newMobileNumber);
            break;
          case DELETE_MEMBER:
            adminController.deleteMemberPrompt();
            break;
          case VIEW_ALL_MEMBERS:
            adminController.viewAllMembers();
            break;
        }

        // ^^ Just for testing
        ArrayList <Member> members =  adminController.getMembers();
        adminView.printMemberList(members);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
