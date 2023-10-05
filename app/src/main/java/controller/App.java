package controller;

import model.Contract;
import model.Item;

// import java.util.Date;

import model.Member;
import model.Item.ItemCategory;
import view.AdminView;
import view.AdminView.MainMenuAction;

import java.util.ArrayList;

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

      // Add an item to the member
      newMember.addItem(ItemCategory.Tool, "Hammer", "A tool for hammering", 10, time.getTodaysDate());

      // Delete the member
      // adminController.deleteMember(newMember);
      // System.out.println(adminController.getMembers());

        adminController.createMember("Lasse", "lasse.doe@mail.com", "1234567896", time.getTodaysDate());
      
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
        System.out.println(result);

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
            ArrayList <Member> members =  adminController.getMembers();

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

        }

        // ^^ Just for testing
        ArrayList <Member> members =  adminController.getMembers();
        adminView.printMemberList(members);

        // ^^ Test to print the items that member 0 owns
        ArrayList <Item> items = members.get(0).getOwnedItems();
        adminView.printItemList(items);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
