package controller;

import java.util.ArrayList;

import model.Item;
import model.Member;
import model.Item.ItemCategory;
import view.AdminView;

public class MemberController {
  private AdminView adminView;

  public MemberController() {
    adminView = new AdminView();
  }

  public void addItemPrompt(ArrayList <Member> members, int todaysDate) {
    // ^^ Can we use the admin view here? Or should we create a new member view?

    // Ask for which member to add the item to
    int memberIndex = adminView.addItemPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    //^^ Added cathegory options to the user output, does it make sence or remove/change? Maybe the output is confusing? 
    // Get cathegory options from enum
    ItemCategory[] categories = ItemCategory.values();

    // Display category options
    System.out.println("Category options:");
    for (int i = 0; i < categories.length; i++) {
      System.out.println(categories[i]);
    }
    //^^ This is where the added code ends. 

    // Ask for the category of the item, name, description, and cost per day
    String categoryString = adminView.prompt("Enter category:");
    String name = adminView.prompt("Enter name:");
    String description = adminView.prompt("Enter description:");
    int costPerDay = Integer.parseInt(adminView.prompt("Enter cost per day:"));

    // ^^ Should we validate the category and give some user friendly feedback if it is invalid? Or should we just let the program crash? Or should we just let the user enter the category again? Or should we have the user to chose from a list of categories?

    // Convert the category string to an enum
    ItemCategory category = ItemCategory.valueOf(categoryString);

    // Add the item to the member
    member.addItem(category, name, description, costPerDay, todaysDate);

    // Print a success message
    adminView.print("Item added!");
  }

  public void editItemPrompt (ArrayList <Member> members) {
    // Ask the user for which member that owns the item that should be edited
    int memberIndex = adminView.editItemSelectMemberPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    // Get the list of items from the member
    ArrayList <Item> items = member.getOwnedItems();

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

  public void deleteItemPrompt (ArrayList <Member> members) {
    // Ask the user for which member that owns the item that should be deleted
    int memberIndex = adminView.deleteItemSelectMemberPrompt(members);

    // Get the member from the list of members
    Member member = members.get(memberIndex - 1);

    // Get the list of items from the member
    ArrayList <Item> items = member.getOwnedItems();

    // Ask the user for which item that should be deleted
    int itemIndex = adminView.deleteItemSelectItemPrompt(items);

    // Get the item from the list of items
    Item item = items.get(itemIndex);

    // Delete the item
    member.deleteItem(item);

    // Print a success message
    adminView.print("Item deleted!");
  }

  //^^ Added method to view all items, does it make sence or remove/change? 
  // Print a list of all the items that the members own
  public void viewAllItems(ArrayList <Member> members) {
    // Loop through all the members
    for (int i = 0; i < members.size(); i++) {
      // Get the member from the list of members
      Member member = members.get(i);

      // Get the list of items from the member
      ArrayList <Item> items = member.getOwnedItems();

      // Print the list of items
      adminView.printItemList(items);
    }
  }
}
