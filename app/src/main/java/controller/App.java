package controller;

import java.util.ArrayList;
import model.Item.ItemCategory;
import model.AdminModel;
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

      Time time = new Time();
      AdminController adminController = new AdminController();
      AdminModel adminModel = adminController.getAdminModel();
      AdminView adminView = new AdminView();
      MemberController memberController = new MemberController();

      // Add some members, items, and contracts to start with
      addDefaultData(adminModel, time);

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

  private static void addDefaultData(AdminModel adminModel, Time time) throws Exception {
      // Add a new member to the members list
      Member jane = adminModel.createMember("Jane Doe", "jane.doe@example", "0701234569", time.getTodaysDate());

      // Add an item to the member jane
      jane.addItem(ItemCategory.Tool, "Hammer", "A tool for hammering", 50, time.getTodaysDate());

      jane.addItem(ItemCategory.Toy, "Ball", "A ball for playing", 10, time.getTodaysDate());

      jane.changeCredits(300);

      // Add a new member to the members list
      Member lasse = adminModel.createMember("Lasse", "lasse.doe@mail.com", "1234567896", time.getTodaysDate());

      lasse.changeCredits(100);

      // Add a new member to the members list
      Member alex = adminModel.createMember("Alex Doe", "alex.doe@example", "0701234575", time.getTodaysDate());

      alex.changeCredits(100);

      // Alex wants to borrow janes boll for 3 days (today included)
      jane.getOwnedItems().get(1).addContract(alex, time.getTodaysDate(), time.getTodaysDate() + 2);
  }
}
