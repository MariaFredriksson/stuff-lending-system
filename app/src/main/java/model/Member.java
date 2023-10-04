package model;

import java.util.ArrayList;

import model.Item.ItemCategory;

public class Member {
  private String name;
  private String email;
  private String mobileNumber;
  private String memberID;
  private int credits;
  private ArrayList<Item> ownedItems;
  // private ArrayList<Item> borrowedItems;
  private int creationDate;

  public Member(String name, String email, String mobileNumber, String memberID, int creationDate) {
    setName(name);
    setEmail(email);
    setMobileNumber(mobileNumber);
    this.memberID = memberID;

    // Set the credits to 0 for a new member
    this.credits = 0;

    // Create an empty list of items
    this.ownedItems = new ArrayList<Item>();

    // ^^ Do we need this...?
    // Create an empty list of borrowed items
    // this.borrowedItems = new ArrayList<Item>();

    // Set the creation date to the current date
    this.creationDate = creationDate;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getMobileNumber() {
    return this.mobileNumber;
  }

  public String getMemberID() {
    return this.memberID;
  }

  public int getCredits() {
    return this.credits;
  }

  public ArrayList<Item> getOwnedItems() {
    // Return a copy of the list of items, so it is immutable
    return new ArrayList<Item>(this.ownedItems);
  }

  public int getCreationDate() {
    return this.creationDate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    if (email.contains("@") && email.contains(".")) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }

  }

  public void setMobileNumber(String mobileNumber) {
    if (String.valueOf(mobileNumber).length() == 10) {
      this.mobileNumber = mobileNumber;
    } else {
      throw new IllegalArgumentException("Invalid mobile number");
    }
  }

  public void changeCredits(int credits) {
    this.credits += credits;
  }

  public void addItem(ItemCategory category, String name, String description, int costPerDay, int todaysDate) {
    // Create a new item object
    Item newItem = new Item(category, name, description, costPerDay, this, todaysDate);

    // Add the item to the list of items
    this.ownedItems.add(newItem);

    // Add 100 credits to the member's account
    this.changeCredits(100);
  }

  public void deleteItem(Item item) {
    // Delete the item from the list of items
    this.ownedItems.remove(item);
  }

  //^^ borrowedItems metod?
}
