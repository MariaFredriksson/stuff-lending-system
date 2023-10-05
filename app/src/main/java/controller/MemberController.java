package controller;

import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Item.ItemCategory;
import model.Member;
import view.AdminView;


/**
 * The MemberController class is responsible for controlling interactions between the
 * MemberModel and MemberView. It manages the creation, editing, and deletion of items,
 * as well as displaying item information.
 */
public class MemberController {
  private AdminView adminView;

  /**
   * Constructor to initialize the MemberController with an instance of AdminView.
   */
  public MemberController() {
    adminView = new AdminView();
  }

  /**
   * Prompts the user to create a new item and adds the item to the list of items.
   *
   * @param members The list of members.
   * @param todaysDate The current date for item creation.
   */
  public void addItemPrompt(ArrayList<Member> members, int todaysDate) {
    // ^^ Can we use the admin view here? Or should we create a new member view?

    // Ask for which member to add the item to
    int memberIndex = adminView.addItemPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    // Get cathegory options from enum
    ItemCategory[] categories = ItemCategory.values();

    // Display category options
    System.out.println("Category options:");
    for (int i = 0; i < categories.length; i++) {
      System.out.println(categories[i]);
    }

    // Ask for the category of the item, name, description, and cost per day
    String categoryString = adminView.prompt("Enter category:");
    String name = adminView.prompt("Enter name:");
    String description = adminView.prompt("Enter description:");
    int costPerDay = Integer.parseInt(adminView.prompt("Enter cost per day:"));

    // Convert the category string to an enum
    ItemCategory category = ItemCategory.valueOf(categoryString);

    // Add the item to the member
    member.addItem(category, name, description, costPerDay, todaysDate);

    // Print a success message
    adminView.print("Item added!");
  }

  /**
   * Prompts the user to edit an item and edits the item.
   *
   * @param members The list of members.
   */
  public void editItemPrompt(ArrayList<Member> members) {
    // Ask the user for which member that owns the item that should be edited
    int memberIndex = adminView.editItemSelectMemberPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    // Get the list of items from the member
    ArrayList<Item> items = member.getOwnedItems();

    // Ask the user for which item that should be edited
    int itemIndex = adminView.editItemSelectItemPrompt(items);

    // Get the item from the list of items
    Item item = items.get(itemIndex);

    // Ask the user for the category, name, description and cost per day
    String categoryString = adminView.prompt("Enter category:");
    String name = adminView.prompt("Enter name:");
    String description = adminView.prompt("Enter description:");
    int costPerDay = Integer.parseInt(adminView.prompt("Enter cost per day:"));

    // Convert the category string to an enum
    ItemCategory category = ItemCategory.valueOf(categoryString);

    // Edit the item
    member.editItem(item, category, name, description, costPerDay);

    // Print a success message
    adminView.print("Item edited!");
  }

  /**
   * Prompts the user to delete an item and deletes the item.
   *
   * @param members The list of members.
   */
  public void deleteItemPrompt(ArrayList<Member> members) {
    // Ask the user for which member that owns the item that should be deleted
    int memberIndex = adminView.deleteItemSelectMemberPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    // Get the list of items from the member
    ArrayList<Item> items = member.getOwnedItems();

    // Ask the user for which item that should be deleted
    int itemIndex = adminView.deleteItemSelectItemPrompt(items);

    // Get the item from the list of items
    Item item = items.get(itemIndex);

    // Delete the item
    member.deleteItem(item);

    // Print a success message
    adminView.print("Item deleted!");
  }

  /**
   * Prompts the user to view all items and displays all items.
   *
   * @param members The list of members.
   */
  public void viewAllItemsName(ArrayList<Member> members) {
    ArrayList<Item> items = new ArrayList<Item>();
    // Loop through all the members
    for (int i = 0; i < members.size(); i++) {
      // Get the member from the list of members
      Member member = members.get(i);

      // Get the list of items from the member
      ArrayList<Item> itemsToAdd = member.getOwnedItems();

      // Add the itemsToAdd to the list of items
      items.addAll(itemsToAdd);
    }
    // Print the list of items
    adminView.printItemList(items);
  }

  public void viweAllItems(ArrayList<Member> members) {
    // Ask user which way to view the items?
    int viewOption = Integer.parseInt(adminView.prompt("How do you want to view the items? \n 1. Only names \n 2. All information."));
    
    if(viewOption == 1) {
      viewAllItemsName(members);
    } else if(viewOption == 2) {
      viewAllItemInformation(members);
    } else {
      adminView.print("Invalid input");
    } {

    }
  }

  /**
   * Prompts the user to view all items of a specific category and displays all items of that
   * category.
   *
   * @param members The list of members.
   */
  public void addContractPrompt(ArrayList<Member> members, int todaysDate) throws Exception {
    // Ask the user which item they want to rent
    adminView.print("Select an item to rent:");
    viewAllItemsName(members);

    // Get a list of all the items
    ArrayList<Item> items = getAllItems(members);

    int itemIndex = Integer.parseInt(adminView.readLine()) - 1;

    // Get the item from the list of items
    Item item = items.get(itemIndex);

    // Check that the item is available
    if (item.getIsAvailable()) {
      // Ask the user which member they are
      adminView.print("Who are you?");
      adminView.printMemberList(members);

      int memberIndex = Integer.parseInt(adminView.readLine()) - 1;

      // Get the member from the list of members
      Member borrowingMember = members.get(memberIndex);

      // Ask the user for the number of days they want to rent the item
      int numberOfDays = Integer.parseInt(adminView.prompt("Enter number of days to borrow the item:"));

      // Calculate the end date
      int endDate = todaysDate + numberOfDays;

      // Add the contract
      item.addContract(borrowingMember, todaysDate, endDate);

      // Print a success message
      adminView.print("Contract added!");
    } else {
      // Print an error message
      adminView.print("Item is not available");
    }
  }

  /**
   * Prompts the user to view all items of a specific category and displays all items of that
   * category.
   *
   * @param members The list of members.
   */
  public void viewAllItemInformation(ArrayList<Member> members) {
    // Get all the items from all the members
    ArrayList<Item> items = getAllItems(members);

    // Loop through all the items and print all the information about the items
    for (int i = 0; i < items.size(); i++) {
      // Get the item from the list of items
      Item item = items.get(i);

      // Get the list of contracts from the item
      ArrayList<Contract> contracts = item.getContractList();

      // Print the name, category, description, cost per day, owner, and contracts
      adminView.print(i + 1 + ". Name: " + item.getName());
      adminView.print("Category: " + item.getCategory());
      adminView.print("Description: " + item.getDescription());
      adminView.print("Cost per day: " + item.getCostPerDay());
      adminView.print("Owner: " + item.getOwner().getName());

      // Print the contracts, if there are any
      if (contracts.size() > 0) {
        adminView.print("Contracts: ");

        // Loop through all the contracts
        for (int j = 0; j < contracts.size(); j++) {
          // Get the contract from the list of contracts
          Contract contract = contracts.get(j);

          // Print the contract
          adminView.print(" Borrowing member: " + contract.getBorrower().getName());
          adminView.print(" Start date: " + contract.getStartDate());
          adminView.print(" End date: " + contract.getEndDate());
        }
      } else {
        adminView.print("No contracts");
      }
    }
  }

  /**
   * Prompts the user to view all items.
   *
   * @param members The list of members.
   */
  private ArrayList<Item> getAllItems(ArrayList<Member> members) {
    ArrayList<Item> items = new ArrayList<Item>();

    // Loop through all the members
    for (int i = 0; i < members.size(); i++) {
      // Get the member from the list of members
      Member member = members.get(i);

      // Get the list of items from the member
      ArrayList<Item> memberItems = member.getOwnedItems();

      // Loop through all the items
      for (int j = 0; j < memberItems.size(); j++) {
        // Get the item from the list of items
        Item item = memberItems.get(j);

        // Add the item to the list of items
        items.add(item);
      }
    }
    return items;
  }
}
