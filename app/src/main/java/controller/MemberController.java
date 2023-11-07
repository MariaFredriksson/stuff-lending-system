package controller;

import java.util.ArrayList;
import model.Item;
import model.Item.ItemCategory;
import model.Member;
import view.AdminView;
import view.AdminView.ItemListAction;


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

    // Get category options from enum
    ItemCategory[] categories = ItemCategory.values();

    // Display category options
    adminView.displayCategoryOptions(categories);

    // Ask for the category of the item, name, description, and cost per day
    String categoryString = adminView.promptForCategory();
    String name = adminView.promptForName();
    String description = adminView.promptForDescription();
    int costPerDay = adminView.promptForCostPerDay();

    // Convert the category string to an enum
    ItemCategory category = ItemCategory.valueOf(categoryString);

    // Add the item to the member
    member.addItem(category, name, description, costPerDay, todaysDate);

    // Print a success message
    adminView.displayItemAddedSuccessfully();
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
    String categoryString = adminView.promptForCategory();
    String name = adminView.promptForName();
    String description = adminView.promptForDescription();
    int costPerDay = adminView.promptForCostPerDay();

    // Convert the category string to an enum
    ItemCategory category = ItemCategory.valueOf(categoryString);

    // Edit the item
    member.editItem(item, category, name, description, costPerDay);

    // Print a success message
    adminView.displayItemEditedSuccessfully();
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
    adminView.displayItemDeletedSuccessfully();
  }

  /**
   * Prompts the user to view all items and displays all items.
   *
   * @param members The list of members.
   */
  public void viewAllItemsName(ArrayList<Member> members) {
    ArrayList<Item> items = getAllItems(members);
    // Print the list of items
    adminView.printItemList(items);
  }

  /**
   * Prompts the user to view all items and displays all items.
   *
   * @param members The list of members.
   */
  public void viewAllItems(ArrayList<Member> members) {
    adminView.printItemListMenu();

    ItemListAction action = adminView.getItemListAction(adminView.readLine());

    switch (action) {
      case NAMES:
        viewAllItemsName(members);
        break;
      case ALL_INFORMATION:
        viewAllItemInformation(members);
        break;
      default:
        adminView.printInvalidInput();
    }
  }

  /**
   * Prompts the user to view all items of a specific category and displays all items of that
   * category.
   *
   * @param members The list of members.
   */
  public void addContractPrompt(ArrayList<Member> members, int todaysDate) throws Exception {
    try {
      // Ask the user which item they want to rent
      adminView.printSelectItemToRent();
      viewAllItemsName(members);
  
      // Get a list of all the items
      ArrayList<Item> items = getAllItems(members);
  
      int itemIndex = Integer.parseInt(adminView.readLine()) - 1;
  
      // Get the item from the list of items
      Item item = items.get(itemIndex);
  
      // Check that the item is available
      if (item.getIsAvailable()) {
        // Ask the user which member they are
        adminView.printWhoAreYou();
        adminView.printMemberList(members);
  
        int memberIndex = Integer.parseInt(adminView.readLine()) - 1;
  
        // Get the member from the list of members
        Member borrowingMember = members.get(memberIndex);
  
        // Ask the user for the number of days they want to rent the item
        int numberOfDays = adminView.promptForDaysToRent();
  
        // Calculate the end date
        int endDate = todaysDate + numberOfDays;
  
        // Add the contract
        item.addContract(borrowingMember, todaysDate, endDate);
  
        // Print a success message
        adminView.printContractAddedSuccessfully();
      } else {
        // Print an error message
        adminView.printItemNotAvailable();
      }
      
    } catch (Exception e) {
      adminView.print(e.getMessage());
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

      adminView.printAllItemInformation(item, i);
    }
  }

  /**
   * Prompts the user to view all items.
   *
   * @param members The list of members.
   */
  public ArrayList<Item> getAllItems(ArrayList<Member> members) {
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
