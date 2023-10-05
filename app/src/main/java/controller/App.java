package controller;

import model.Item.ItemCategory;
import java.util.ArrayList;
import model.Member;
import model.Time;
import view.AdminView;
import view.AdminView.MainMenuAction;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   * @param args command line arguments.
   *
   */
  public static void main(String[] args) {
    try {

      Time time = new Time();

      // Add a new member to the members list
      AdminController adminController = new AdminController();
      Member newMember = adminController.createMember("John Doe", "john.doe@example", "1234567891", time.getTodaysDate());

      // Edit the member
      adminController.editMember(newMember, "Jane Doe", "jane.doe@example", "0701234569");

      // Add an item to the member
      newMember.addItem(ItemCategory.Tool, "Hammer", "A tool for hammering", 10, time.getTodaysDate());


      Member lasse = adminController.createMember("Lasse", "lasse.doe@mail.com", "1234567896", time.getTodaysDate());

      // Add an item to the member lasse
      lasse.addItem(ItemCategory.Tool, "Screwdriver", "A tool for screwing", 12, time.getTodaysDate());

      // Add a contract where Jane Doe borrows Lasse's screwdriver
      lasse.getOwnedItems().get(0).addContract(newMember, time.getTodaysDate(), time.getTodaysDate() + 7);

      // Add a contract where Lasse borrows Jane Doe's hammer
      newMember.getOwnedItems().get(0).addContract(lasse, time.getTodaysDate(), time.getTodaysDate() + 5);
      
      // Create an admin view
      AdminView adminView = new AdminView();

      // Create a member controller
      MemberController memberController = new MemberController();

      // Print a welcome message when the application first starts
      adminView.print("Welcome to the Stuff Lending System!");

      // Set the result to something other than EXIT so the while-loop starts
      MainMenuAction result = MainMenuAction.NONE;

      // Show the main menu until the user exits
      while (result != MainMenuAction.EXIT) {
        adminView.mainMenu();
        result = adminView.getMainMenuAction(adminView.readLine());
        // System.out.println(result);

        switch (result) {
          case CREATE_MEMBER:
            adminController.createMemberPrompt(time.getTodaysDate());
            break;
          case EDIT_MEMBER:
            adminController.editMemberPrompt();
            break;
          case DELETE_MEMBER:
            adminController.deleteMemberPrompt();
            break;
          case VIEW_ALL_MEMBERS:
            adminController.viewAllMembers();
            break;
          case ADD_ITEM:
            // Get a list of all the members that exist in this program 
            // (We can't create a new adminController anywhere else, since it won't have the list of members (much like the Time class only should be created once))
            ArrayList<Member> members =  adminController.getMembers();

            memberController.addItemPrompt(members, time.getTodaysDate());
            break;
          case EDIT_ITEM:
            memberController.editItemPrompt(adminController.getMembers());
            break;
          case DELETE_ITEM:
            memberController.deleteItemPrompt(adminController.getMembers());
            break;
          case VIEW_ALL_ITEMS:
            memberController.viewAllItems(adminController.getMembers());
            break;
          case ADD_CONTRACT:
            memberController.addContractPrompt(adminController.getMembers(), time.getTodaysDate());
            break;
          case VIEW_ALL_ITEM_INFORMATION:
            memberController.viewAllItemInformation(adminController.getMembers());
            break;
          case EXIT:
            adminView.print("Goodbye!");
            break;
          default:
            adminView.print("Invalid input");
            break;
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
