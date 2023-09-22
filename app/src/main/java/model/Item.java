package model;

import java.util.ArrayList;
import java.util.Date;

public class Item {
  private String category;
  private String name;
  private String description;
  private int costPerDay;
  private Member owner;
  private boolean isAvailable;
  private ArrayList<Contract> contractList;
  private Date creationDate;

  public Item(String category, String name, String description, int costPerDay, Member owner) {
    // ^^ Use the setters instead...?
    this.category = category;
    this.name = name;
    this.description = description;
    this.costPerDay = costPerDay;
    this.owner = owner;

    // Set the item to available for a new item
    this.isAvailable = true;

    // Create an empty list of contracts
    this.contractList = new ArrayList<Contract>();

    // Set the creation date to the current date
    this.creationDate = new Date();
  }

  public String getCategory() {
    return this.category;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public int getCostPerDay() {
    return this.costPerDay;
  }

  // ^^ Is this needed....?
  public Member getOwner() {
    return this.owner;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  public ArrayList<Contract> getContractList() {
    // Return a copy of the list of contracts
    return new ArrayList<Contract>(this.contractList);
  }

  public Date getCreationDate() {
    // Return a defensive copy of the creationDate, so it is immutable
    return new Date(this.creationDate.getTime());
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  // Check if the borrowing member has enough credits to rent the item for the specified number of days
  private boolean checkCredits(Member borrower, int days) throws Exception {
    checkValidDays(days);

    // If the borrower has enough credits, true is returned, otherwise false is returned
    return borrower.getCredits() >= this.costPerDay * days;
  }

  private void checkValidDays(int days) throws Exception {
    // Check if days is negative or 0
    if (days <= 0) {
      // Throw an exception if days is negative or 0
      throw new IllegalArgumentException("Invalid number of days. Must be positive.");
    }
  }

  public void addContract(Member borrower, Date startDate, Date endDate) throws Exception {
    // Count the number of days between the start and end date
    int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

    // Check if the days are valid
    this.checkValidDays(days);

    // ^^ Delete this later
    System.out.println(days);

    // Check if the item is available
    if (!this.isAvailable) {
      // Throw an exception if the item is not available
      throw new IllegalArgumentException("Item is not available.");
    }

    // Check if the borrower has enough credits to rent the item for the specified number of days
    if (!this.checkCredits(borrower, days)) {
      // Throw an exception if the borrower does not have enough credits
      throw new IllegalArgumentException("Not enough credits to rent the item for " + days + " days.");
    }

    int rentingCost = this.costPerDay * days;

    // Create a new contract object
    Contract contract = new Contract(this, this.owner, borrower, startDate, endDate, rentingCost);

    // Add the contract to the list of contracts
    this.contractList.add(contract);

    // Set the item to unavailable
    this.isAvailable = false;

    // Transfer credits from borrower to owner
    contract.transferCredits();
  }
}
