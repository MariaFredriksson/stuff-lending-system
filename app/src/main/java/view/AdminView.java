package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Contract;
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
    print("9. Add contract");
    print("0. View all item information");
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
    ADD_CONTRACT,
    VIEW_ALL_ITEM_INFORMATION,
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
        return MainMenuAction.ADD_CONTRACT;
      case "0":
        return MainMenuAction.VIEW_ALL_ITEM_INFORMATION;
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

  public void printMemberListSimple(ArrayList<Member> memberList) {
    for (int i = 0; i < memberList.size(); i++) {
      print(i + 1 + ". " + memberList.get(i).getName());
      print(" Email: " + memberList.get(i).getEmail());
      print(" Credits: " + memberList.get(i).getCredits());
      print(" Number of owned items: " + memberList.get(i).getOwnedItems().size());
    }
  }

  // ^^ Can this method be this smart? (In a view)
  public void printMemberListVerbose(ArrayList<Member> memberList) {
    // Loop through all the members and print their name, email, information about all owned items (including contracts)
    for (int i = 0; i < memberList.size(); i++) {
      // Get the member from the list of members
      Member member = memberList.get(i);

      // Print the name and email of the member
      print(i + 1 + ". " + memberList.get(i).getName());
      print(" Email: " + memberList.get(i).getEmail());

      // Get the list of items from the member
      ArrayList <Item> items = member.getOwnedItems();

      // Loop through all the items and print their name, information about all contracts
      for (int j = 0; j < items.size(); j++) {
        // Get the item from the list of items
        Item item = items.get(j);

        // Print the name, category, description, cost of the item
        print("   Item: " + item.getName());
        print("   Category: " + item.getCategory().toString());
        print("   Description: " + item.getDescription());
        print("   Cost per day: " + String.valueOf(item.getCostPerDay()));

        // Get the list of contracts from the item
        ArrayList <Contract> contracts = item.getContractList();

        // Print the contracts (if there are any)
        if (contracts.size() > 0) {
          // Loop through all the contracts and print their information
          for (int k = 0; k < contracts.size(); k++) {
            // Get the contract from the list of contracts
            Contract contract = contracts.get(k);
  
            // Print the information about the contract
            print("     Contract time period: " + String.valueOf(contract.getStartDate()) + " - " + String.valueOf(contract.getEndDate()));
            print("     Borrower: " + contract.getBorrower().getName());
          }
        } else {
          print("No contracts");
        }
      }
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
