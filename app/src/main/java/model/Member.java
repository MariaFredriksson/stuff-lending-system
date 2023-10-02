package model;

import java.util.ArrayList;
import java.util.Date;

import model.Item.ItemCategory;

public class Member {
  private String name;
  private String email;
  private int mobileNumber;
  private String memberID;
  private int credits;
  private ArrayList<Item> ownedItems;
  // private ArrayList<Item> borrowedItems;
  private Date creationDate;

  public Member(String name, String email, int mobileNumber, String memberID) {
    this.name = name;
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
    this.creationDate = new Date();
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public int getMobileNumber() {
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

  public Date getCreationDate() {
    // Return a defensive copy of the creationDate, so it is immutable
    return new Date(this.creationDate.getTime());
  }

  public void setName(String name) {
    this.name = name;
  }

  // TODO: Add setters for email and mobileNumber with validation

  public void setEmail(String email) {
    if (email.contains("@") && email.contains(".")) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }

  }

  public void setMobileNumber(int mobileNumber) {
    
  }

  public void changeCredits(int credits) {
    this.credits += credits;
  }

  public void addItem(ItemCategory category, String name, String description, int costPerDay) {
    // Create a new item object
    Item newItem = new Item(category, name, description, costPerDay, this);

    // Add the item to the list of items
    this.ownedItems.add(newItem);

    // Add 100 credits to the member's account
    this.changeCredits(100);
  }

  public void deleteItem(Item item) {
    // Delete the item from the list of items
    this.ownedItems.remove(item);
  }
}
