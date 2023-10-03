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
      
      // Show the main menu
      AdminView adminView = new AdminView();
      MainMenuAction result = MainMenuAction.NONE;
      while (result != MainMenuAction.EXIT) {
        adminView.mainMenu();
        result = adminView.getMainMenuAction(adminView.readLine());
        System.out.println(result);

        switch (result) {
          case CREATE_MEMBER:
            adminView.print("Create new member");
            String name = adminView.prompt("Enter name:");
            String email = adminView.prompt("Enter email:");
            String mobileNumber = adminView.prompt("Enter mobile number:");
            adminController.createMember(name, email, mobileNumber, time.getTodaysDate());
            adminView.print("Member created!");
            break;
          case EDIT_MEMBER:
            adminView.print("Edit member");
            List <Member> memberList = adminController.getMembers();
            for (int i = 0; i < memberList.size(); i++) {
              adminView.print(i + 1 + ". " + memberList.get(i).getName());
            }
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
