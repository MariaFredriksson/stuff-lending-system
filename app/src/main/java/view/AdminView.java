package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Item;
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

  public int editMemberPrompt(ArrayList <Member> memberList) {
    print("Edit member");
    print("Select a member to edit:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
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

  public int addItemPrompt(ArrayList<Member> memberList) {
    print("Add item");
    print("Select a member to add the item to:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  public int editItemSelectMemberPrompt(ArrayList <Member> memberList) {
    print("Edit item");
    print("Select the member that owns the item you want to edit:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  public int editItemSelectItemPrompt(ArrayList <Item> itemList) {
    print("Select the item you want to edit:");
    printItemList(itemList);
    int itemIndex = Integer.parseInt(readLine()) - 1;
    return itemIndex;
  }

  public int deleteItemSelectMemberPrompt(ArrayList <Member> memberList) {
    print("Delete item");
    print("Select the member that owns the item you want to delete:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  public int deleteItemSelectItemPrompt(ArrayList <Item> itemList) {
    print("Select the item you want to delete:");
    printItemList(itemList);
    int itemIndex = Integer.parseInt(readLine()) - 1;
    return itemIndex;
  }

  public void printItemList (ArrayList<Item> itemList) {
    for (int i = 0; i < itemList.size(); i++) {
      print(i + 1 + ". " + itemList.get(i).getName());
    }
  }
}
