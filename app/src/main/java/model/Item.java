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
    return this.contractList;
  }

  public Date getCreationDate() {
    return this.creationDate;
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

  // TODO: checkCredits

  // TODO: addContract
}
