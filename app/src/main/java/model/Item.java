package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Item class represents an item.
 */
public class Item {
  /**
   * The ItemCategory enum represents the category of an item.
   */
  public enum ItemCategory {
    Tool,
    Vehicle,
    Game,
    Toy,
    Sport,
    Other
  }

  private ItemCategory category;
  private String name;
  private String description;
  private int costPerDay;
  private Member owner;
  private boolean isAvailable;
  private ArrayList<Contract> contractList;
  private int creationDate;

  /**
   * Creates an item.
   *
   * @param category The category of the item.
   * @param name The name of the item.
   * @param description The description of the item.
   * @param costPerDay The cost per day of the item.
   * @param owner The member that owns the item.
   * @param creationDate The date when the item was created.
   */
  public Item(ItemCategory category, String name, String description, int costPerDay, Member owner, int creationDate) {
    setCategory(category);
    setName(name);
    setDescription(description);
    setCostPerDay(costPerDay);
    this.owner = owner;
    this.creationDate = creationDate;

    // Set the item to available for a new item
    this.isAvailable = true;

    // Create an empty list of contracts
    this.contractList = new ArrayList<Contract>();

  }

  /**
   * Returns the category of the item.
   *
   * @return The category of the item.
   */
  public ItemCategory getCategory() {
    return this.category;
  }

  /**
   * Returns the name of the item.
   *
   * @return The name of the item.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the description of the item.
   *
   * @return The description of the item.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns the cost per day of the item.
   *
   * @return The cost per day of the item.
   */
  public int getCostPerDay() {
    return this.costPerDay;
  }

  // ^^ Is this needed....?
  /**
   * Returns the owner of the item.
   *
   * @return The owner of the item.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a getter method.")
  public Member getOwner() {
    return this.owner;
  }

  /**
   * Returns true if the item is available, otherwise false.
   *
   * @return True if the item is available, otherwise false.
   */
  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  /**
   * Returns the list of contracts.
   *
   * @return The list of contracts.
   */
  public ArrayList<Contract> getContractList() {
    // Return a copy of the list of contracts
    return new ArrayList<Contract>(this.contractList);
  }

  /**
   * Returns the date when the item was created.
   *
   * @return The date when the item was created.
   */
  public int getCreationDate() {
    return this.creationDate;
  }

  /**
   * Sets the category of the item.
   *
   * @param category The category of the item.
   */
  public void setCategory(ItemCategory category) {
  if (Arrays.stream(ItemCategory.values()).anyMatch(c -> c == category)) {
      this.category = category;
  } else {
      throw new IllegalArgumentException("Invalid category: " + category);
    }
  }

  /**
   * Sets the name of the item.
   *
   * @param name The name of the item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of the item.
   *
   * @param description The description of the item.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the cost per day of the item.
   *
   * @param costPerDay The cost per day of the item.
   */
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  public void setIsAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  /**
   * Checks if the borrower has enough credits to rent the item for the specified number of days.
   *
   * @param borrower The borrower.
   * @param days The number of days.
   * @return True if the borrower has enough credits, otherwise false.
   *
   * @throws Exception If the number of days is negative or 0.
   */
  private boolean checkCredits(Member borrower, int days) throws Exception {
    checkValidDays(days);

    // If the borrower has enough credits, true is returned, otherwise false is returned
    return borrower.getCredits() >= this.costPerDay * days;
  }

  /**
   * Checks if the number of days is valid.
   *
   * @param days The number of days.
   *
   * @throws Exception If the number of days is negative or 0.
   */
  private void checkValidDays(int days) throws Exception {
    // Check if days is negative or 0
    if (days <= 0) {
      // Throw an exception if days is negative or 0
      throw new IllegalArgumentException("Invalid number of days. Must be positive.");
    }
  }

  /**
   * Adds a contract.
   *
   * @param borrower The member that is borrowing the item.
   * @param startDate The date when the contract starts.
   * @param endDate The date when the contract ends.
   *
   * @throws Exception If the number of days is negative or 0, the item is not available, or the
    borrower does not have enough credits.
   */
  public void addContract(Member borrower, int startDate, int endDate) throws Exception {
    // Count the number of days between the start and end date
    int days = endDate - startDate;

    // Add 1 to the number of days, because the start date is included
    days++;

    // Check if the days are valid
    this.checkValidDays(days);

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

    // Calculate the cost of renting the item for the whole duration of the contract
    int rentingCost = this.costPerDay * days;

    // Create a new contract object
    Contract contract = new Contract(this, this.owner, borrower, startDate, endDate, rentingCost);

    // Add the contract to the list of contracts
    this.contractList.add(contract);

    // Set the item to unavailable
    this.isAvailable = false;

    // Transfer credits from borrower to owner for the whole duration of the contract
    contract.transferCredits();
  }
}
