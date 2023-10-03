package view;

import java.util.Scanner;

public class AdminView {
  
  public void mainMenu () {
    System.out.println("Welcome to the Stuff Lending System!");
    System.out.println("Please select an option:");
    System.out.println("1. Create a new member");
    System.out.println("2. Edit a member");
    System.out.println("3. Delete a member");
    System.out.println("4. View all members");
    System.out.println("5. Add item");
    System.out.println("6. Edit item");
    System.out.println("7. Delete item");
    System.out.println("8. View all items");
    System.out.println("9. View all contracts");
    System.out.println("Q. Exit");
  }

  public enum MainMenuAction {
    NONE,
    CREATE_MEMBER,
    EDIT_MEMBER,
    DELETE_MEMBER,
    VIEW_ALL_MEMBERS,
    ADD_ITEM,
    EDIT_ITEM,
    DELETE_ITEM,
    VIEW_ALL_ITEMS,
    VIEW_ALL_CONTRACTS,
    EXIT
  }

  public MainMenuAction getMainMenuAction(String input) {
    if (input.length() != 1) {
      return MainMenuAction.NONE;
    }
    switch (input) {
      case "1":
        return MainMenuAction.CREATE_MEMBER;
      case "2":
        return MainMenuAction.EDIT_MEMBER;
      case "3":
        return MainMenuAction.DELETE_MEMBER;
      case "4":
        return MainMenuAction.VIEW_ALL_MEMBERS;
      case "5":
        return MainMenuAction.ADD_ITEM;
      case "6":
        return MainMenuAction.EDIT_ITEM;
      case "7":
        return MainMenuAction.DELETE_ITEM;
      case "8":
        return MainMenuAction.VIEW_ALL_ITEMS;
      case "9":
        return MainMenuAction.VIEW_ALL_CONTRACTS;
      case "Q":
      case "q":
        return MainMenuAction.EXIT;
      default:
        return MainMenuAction.NONE;
    }
  }

  public String readLine() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
