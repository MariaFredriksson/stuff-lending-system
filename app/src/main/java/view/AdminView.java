package view;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;
import model.Item.ItemCategory;

/**
 * The AdminView class is responsible for displaying the admin menu and getting input from the user.
 */
public class AdminView {
  
  // ^^ Should we have everything about main menu in its own view maybe?
  // ^^ If so; how should we handle print(), prompt(), readLine() to make the code DRY? Interface...? Inherit...?
  /**
   * Displays the main menu.
   */
  public void mainMenu() {
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
    print("0. Admin menu");
    print("Q. Exit");
  }

  /**
   * The MainMenuAction enum represents the actions that can be performed in the main menu.
   */
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
    ADMIN_MENU,
    EXIT
  }

  /**
   * Returns the main menu action based on the user input.
   *
   * @param input The user input.
   *
   * @return The main menu action.
   */
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
        return MainMenuAction.ADMIN_MENU;
      case "Q":
      case "q":
        return MainMenuAction.EXIT;
      default:
        return MainMenuAction.NONE;
    }
  }

  /**
   * Reads a line from the console.
   *
   * @return The line that was read.
   */
  public String readLine() {
    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    return scanner.nextLine();
  }

  /**
   * Prints a string to the console.
   *
   * @param text The string to print.
   */
  public void print(String text) {
    System.out.println(text);
  }

  /**
   * Prints a string to the console without a new line.
   *
   * @param prompt The string to print.
   *
   * @return The string that was printed.
   */
  public String prompt(String prompt) {
    print(prompt);
    return readLine();
  }

  public void printInvalidInput() {
    print("Invalid input");
  }

  public void displayCreateMemberPrompt() {
    print("Create member");
  }

  public String promptForName() {
    return prompt("Enter name:");
  }

  public String promptForEmail() {
    return prompt("Enter email:");
  }

  public String promptForMobileNumber() {
    return prompt("Enter mobile number:");
  }

  public void displayMemberCreatedSuccessfully() {
    print("Member created successfully!");
  }

  /**
   * Prompts the user to edit a member.
   *
   * @param memberList The list of members.
   *
   * @return The index of the member to edit.
   */
  public int editMemberPrompt(ArrayList<Member> memberList) {
    print("Edit member");
    print("Select a member to edit:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }
  
  public void displayMemberEditedSuccessfully() {
    print("Member edited successfully!");
  }

  /**
   * Prompts the user to delete a member.
   *
   * @param memberList The list of members.
   *
   * @return The index of the member to delete.
   */
  public int deleteMemberPrompt(ArrayList<Member> memberList) {
    print("Delete member");
    print("Select member to delete:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  public void displayCategoryOptions(ItemCategory[] categories) {
    print("Category options:");
    for (int i = 0; i < categories.length; i++) {
      print(categories[i].toString());
    }
  }

  public String promptForCategory() {
    return prompt("Enter category:");
  }

  public String promptForDescription() {
    return prompt("Enter description:");
  }

  public int promptForCostPerDay() {
    return Integer.parseInt(prompt("Enter cost per day:"));
  }

  public void displayItemAddedSuccessfully() {
    print("Item added successfully!");
  }

  public void displayItemEditedSuccessfully() {
    print("Item edited successfully!");
  }

  public void displayItemDeletedSuccessfully() {
    print("Item deleted successfully!");
  }

  /**
   * Prints the list of members.
   *
   * @param memberList The list of members.
   */
  public void printMemberList(ArrayList<Member> memberList) {
    for (int i = 0; i < memberList.size(); i++) {
      print(i + 1 + ". " + memberList.get(i).getName());
    }
  }

  public void printMemberListMenu() {
    print("View members in:");
    print("1. A simple way");
    print("2. A verbose way");
  }

  public enum MemberListAction {
    NONE,
    SIMPLE,
    VERBOSE
  }

  /**
   * Returns the member list action based on the user input.
   *
   * @param input The user input.
   *
   * @return The member list action.
   */
  public MemberListAction getMemberListAction(String input) {
    if (input.length() != 1) {
      return MemberListAction.NONE;
    }
    switch (input) {
      case "1":
        return MemberListAction.SIMPLE;
      case "2":
        return MemberListAction.VERBOSE;
      default:
        return MemberListAction.NONE;
    }
  }

  /**
   * Prompts the user to view all members in a simple way.
   *
   * @param memberList The list of members.
   */
  public void printMemberListSimple(ArrayList<Member> memberList) {
    for (int i = 0; i < memberList.size(); i++) {
      print(i + 1 + ". " + memberList.get(i).getName());
      print(" Email: " + memberList.get(i).getEmail());
      print(" Credits: " + memberList.get(i).getCredits());
      print(" Number of owned items: " + memberList.get(i).getOwnedItems().size());
    }
  }

  /**
   * Prompts the user to view all members in a verbose way.
   *
   * @param memberList The list of members.
   */
  public void printMemberListVerbose(ArrayList<Member> memberList) {
    // Loop through all the members and print their name, email, information about all owned items (including contracts)
    for (int i = 0; i < memberList.size(); i++) {
      // Get the member from the list of members
      Member member = memberList.get(i);

      // Print the name and email of the member
      print(i + 1 + ". Name: " + memberList.get(i).getName());
      print(" Email: " + memberList.get(i).getEmail());

      // Get the list of items from the member
      ArrayList<Item> items = member.getOwnedItems();

      // Loop through all the items and print their name, information about all contracts
      for (int j = 0; j < items.size(); j++) {
        // Get the item from the list of items
        Item item = items.get(j);

        printItemInfo(item);
      }
    }
  }

  private void printItemInfo(Item item) {
    // Print the name, category, description, cost of the item
    print("   Item: " + item.getName());
    print("   Category: " + item.getCategory().toString());
    print("   Description: " + item.getDescription());
    print("   Cost per day: " + String.valueOf(item.getCostPerDay()));

    // Get the list of contracts from the item
    ArrayList<Contract> contracts = item.getContractList();

    // Print the contracts (if there are any)
    if (contracts.size() > 0) {
      // Loop through all the contracts and print their information
      for (int k = 0; k < contracts.size(); k++) {
        // Get the contract from the list of contracts
        Contract contract = contracts.get(k);

        // Print the information about the contract
        print("     Contract time period: " 
            + String.valueOf(contract.getStartDate()) 
            + " - " + String.valueOf(contract.getEndDate()));
        print("     Borrower: " + contract.getBorrower().getName());
      }
    } else {
      print("No contracts");
    }
  }

  /**
   * Prompts the user to add an item.
   *
   * @param memberList The list of members.
   *
   * @return The index of the member to add the item to.
   */
  public int addItemPrompt(ArrayList<Member> memberList) {
    print("Add item");
    print("Select a member to add the item to:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  /**
   * Prompts the user to edit an item.
   *
   * @param memberList The list of members.
   *
   * @return The index of the member to edit the item of.
   */
  public int editItemSelectMemberPrompt(ArrayList<Member> memberList) {
    print("Edit item");
    print("Select the member that owns the item you want to edit:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  /**
   * Prompts the user to edit an item.
   *
   * @param itemList The list of items.
   *
   * @return The index of the item to edit.
   */
  public int editItemSelectItemPrompt(ArrayList<Item> itemList) {
    print("Select the item you want to edit:");
    printItemList(itemList);
    int itemIndex = Integer.parseInt(readLine()) - 1;
    return itemIndex;
  }

  /**
   * Prompts the user to delete an item.
   *
   * @param memberList The list of members.
   *
   * @return The index of the member to delete the item of.
   */
  public int deleteItemSelectMemberPrompt(ArrayList<Member> memberList) {
    print("Delete item");
    print("Select the member that owns the item you want to delete:");
    printMemberList(memberList);
    int memberIndex = Integer.parseInt(readLine());
    return memberIndex;
  }

  /**
   * Prompts the user to delete an item.
   *
   * @param itemList The list of items.
   *
   * @return The index of the item to delete.
   */
  public int deleteItemSelectItemPrompt(ArrayList<Item> itemList) {
    print("Select the item you want to delete:");
    printItemList(itemList);
    int itemIndex = Integer.parseInt(readLine()) - 1;
    return itemIndex;
  }

  /**
   * Prints the item list.
   *
   * @param itemList The list of items.
   */
  public void printItemList(ArrayList<Item> itemList) {
    for (int i = 0; i < itemList.size(); i++) {
      print(i + 1 + ". " + itemList.get(i).getName());
    }
  }

  public void printAdminMenu() {
    print("What do you want to do?");
    print("1. Increase day count with one");
  }

  public enum AdminMenuAction {
    NONE,
    INCREASE_DAY_COUNT
  }

  /**
   * Returns the admin menu action based on the user input.
   *
   * @param input The user input.
   *
   * @return The admin menu action.
   */
  public AdminMenuAction getAdminMenuAction(String input) {
    if (input.length() != 1) {
      return AdminMenuAction.NONE;
    }
    switch (input) {
      case "1":
        return AdminMenuAction.INCREASE_DAY_COUNT;
      default:
        return AdminMenuAction.NONE;
    }
  }

  public void printNewDate(int date) {
    print("The new date is: " + date);
  }
}
