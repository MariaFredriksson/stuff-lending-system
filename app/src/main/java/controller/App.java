package controller;

import java.util.ArrayList;
import model.AdminModel;
import model.Item.ItemCategory;
import model.persistence.DefaultData;
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
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    try {
      // ^^ Open a scanner here, send it to adminView, and close it when the program exits?
      Time time = new Time();
      AdminController adminController = new AdminController();
      AdminModel adminModel = adminController.getAdminModel();
      AdminView adminView = new AdminView();
      MemberController memberController = new MemberController();

      // Add some members, items, and contracts to start with
      DefaultData defaultData = new DefaultData(adminModel, time);
      defaultData.load();

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
            // (We can't create a new adminController anywhere else,
            // since it won't have the list of members (much like the Time class only should be created once))
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
          case ADMIN_MENU:
            adminController.viewAdminMenu(time, memberController);
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
