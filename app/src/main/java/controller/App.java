package controller;

import java.util.ArrayList;
import model.AdminModel;
import model.Member;
import model.Time;
import model.persistence.DefaultData;
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
      AdminView adminView = new AdminView();
      AdminModel adminModel = new AdminModel();
      AdminController adminController = new AdminController(adminModel);
      MemberController memberController = new MemberController(adminModel);

      // Add some members, items, and contracts to start with
      DefaultData defaultData = new DefaultData(adminModel, time);
      defaultData.load();

      // Print a welcome message when the application first starts
      adminView.displayWelcomeMessage();

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
            ArrayList<Member> members =  adminModel.getMembers();

            memberController.addItemPrompt(members, time.getTodaysDate());
            break;
          case EDIT_ITEM:
            memberController.editItemPrompt(adminModel.getMembers());
            break;
          case DELETE_ITEM:
            memberController.deleteItemPrompt(adminModel.getMembers());
            break;
          case VIEW_ALL_ITEMS:
            memberController.viewAllItems();
            break;
          case ADD_CONTRACT:
            memberController.addContractPrompt(adminModel.getMembers(), time.getTodaysDate());
            break;
          case ADMIN_MENU:
            adminController.viewAdminMenu(time, memberController);
            break;
          case EXIT:
            adminView.displayGoodbyeMessage();
            break;
          default:
            adminView.printInvalidInput();
            break;
        }
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
