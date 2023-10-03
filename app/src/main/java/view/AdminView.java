package view;

import java.util.ArrayList;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.A;

import model.Member;

public class AdminView {
  
  public void mainMenu () {
    print("Please select an option:");
    print("1. Create a new member");
    print("2. Edit a member");
    print("3. Delete a member");
    print("4. View all members");
    print("5. Add item");
    print("6. Edit item");
    print("7. Delete item");
    print("8. View all items");
    print("9. View all contracts");
    print("Q. Exit");
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


  public void print(String text) {
    System.out.println(text);
  }

  public String prompt(String prompt) {
    print(prompt);
    return readLine();
  }

  // ^^ Is this needed?
  public void editMemberMenu() {
  print("Edit member");
  print("Select a member to edit:");
  }

  // ^^ Is this needed?
  public MainMenuAction editMemberMenuAction(String input) {
    if (input.length() != 1) {
      return MainMenuAction.NONE;
    }
    switch (input) {
      case "1":
        
        break;
    
      default:
        break;
    }
    return null;
  }

  public int deleteMemberPrompt(ArrayList<Member> memberList) {
    print("Delete member");
    print("Select member to delete:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  public void printMemberList(ArrayList<Member> memberList) {
    for (int i = 0; i < memberList.size(); i++) {
      print(i + 1 + ". " + memberList.get(i).getName());
    }
  }

}
