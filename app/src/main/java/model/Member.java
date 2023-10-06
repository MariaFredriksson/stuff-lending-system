package model;

import java.util.ArrayList;
import model.Item.ItemCategory;

/**
 * The Member class represents a member.
 */
public class Member {
  private String name;
  private String email;
  private String mobileNumber;
  private String memberId;
  private int credits;
  private ArrayList<Item> ownedItems;
  // private ArrayList<Item> borrowedItems;
  private int creationDate;

  /**
   * Creates a new member.
   *
   * @param name The name of the member.
   * @param email The email of the member.
   * @param mobileNumber The mobile number of the member.
   * @param memberId The ID of the member.
   * @param creationDate The date when the member was created.
   */
  public Member(String name, String email, String mobileNumber, String memberId, int creationDate) {
    setName(name);
    setEmail(email);
    setMobileNumber(mobileNumber);
    this.memberId = memberId;

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

  /**
   * Returns the name of the member.
   *
   * @return The name of the member.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the email of the member.
   *
   * @return The email of the member.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Returns the mobile number of the member.
   *
   * @return The mobile number of the member.
   */
  public String getMobileNumber() {
    return this.mobileNumber;
  }

  /**
   * Returns the ID of the member.
   *
   * @return The ID of the member.
   */
  public String getMemberId() {
    return this.memberId;
  }

  /**
   * Returns the credits of the member.
   *
   * @return The credits of the member.
   */
  public int getCredits() {
    return this.credits;
  }

  /**
   * Returns the list of items owned by the member.
   *
   * @return The list of items owned by the member.
   */
  public ArrayList<Item> getOwnedItems() {
    // Return a copy of the list of items, so it is immutable
    return new ArrayList<Item>(this.ownedItems);
  }

  /**
   * Returns the date when the member was created.
   *
   * @return The date when the member was created.
   */
  public int getCreationDate() {
    return this.creationDate;
  }

  /**
   * Sets the name of the member.
   *
   * @param name The name of the member.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the email of the member.
   *
   * @param email The email of the member.
   */
  public void setEmail(String email) {
    if (email.contains("@") && email.contains(".")) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }
  }

  /**
   * Sets the mobile number of the member.
   *
   * @param mobileNumber The mobile number of the member.
   */
  public void setMobileNumber(String mobileNumber) {
    if (String.valueOf(mobileNumber).length() == 10) {
      this.mobileNumber = mobileNumber;
    } else {
      throw new IllegalArgumentException("Invalid mobile number");
    }
  }

  /**
   * Updates the members credits.
   *
   * @param credits The credits to be added or subtracted.
   */
  public void changeCredits(int credits) {
    this.credits += credits;
  }

  /**
   * Adds an item.
   */
  public void addItem(ItemCategory category, String name, String description, int costPerDay, int todaysDate) {
    // Create a new item object
    Item newItem = new Item(category, name, description, costPerDay, this, todaysDate);

    // Add the item to the list of items
    this.ownedItems.add(newItem);

    // Add 100 credits to the member's account
    this.changeCredits(100);
  }

  /**
   * Edits an item.
   *
   * @param item The item to be edited.
   */
  public void editItem(Item item, ItemCategory category, String name, String description, int costPerDay) {
    // Edit the item
    item.setCategory(category);
    item.setName(name);
    item.setDescription(description);
    item.setCostPerDay(costPerDay);
  }

  /**
   * Deletes an item.
   *
   * @param item The item to be deleted.
   */
  public void deleteItem(Item item) {
    // Delete the item from the list of items
    this.ownedItems.remove(item);
  }

  //^^ borrowedItems metod?
}
