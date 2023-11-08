package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The Contract class represents a contract between two members.
 */
public class Contract {
  private Item item;
  private Member lender;
  private Member borrower;
  private int startDate;
  private int endDate;
  private int rentingCost;

  /**
   * Creates a new contract.
   *
   * @param item The item that is being borrowed.
   * @param lender The member that is lending the item.
   * @param borrower The member that is borrowing the item.
   * @param startDate The date when the contract starts.
   * @param endDate The date when the contract ends.
   * @param rentingCost The cost of renting the item.
   */

  @SuppressFBWarnings(value = 
      "EI_EXPOSE_REP", justification = "If we want to know the item and the members the contract is connected to.")
  public Contract(Item item, Member lender, Member borrower, int startDate, int endDate, int rentingCost) {
    this.item = item;
    this.lender = lender;
    this.borrower = borrower;
    this.startDate = startDate;
    this.endDate = endDate;
    this.rentingCost = rentingCost;
  }

  // ^^ Are these immutable...?
  /**
   * Returns the item that is being borrowed.
   *
   * @return The item that is being borrowed.
   */
  
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a getter method.")
  public Item getItem() {
    return this.item;
  }

  /**
   * Returns the member that is lending the item.
   *
   * @return The member that is lending the item.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a getter method.")
  public Member getLender() {
    return this.lender;
  }

  /**
   * Returns the member that is borrowing the item.
   *
   * @return The member that is borrowing the item.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a getter method.")
  public Member getBorrower() {
    return this.borrower;
  }

  /**
   * Returns the date when the contract starts.
   *
   * @return The date when the contract starts.
   */
  public int getStartDate() {
    return this.startDate;
  }

  /**
   * Returns the date when the contract ends.
   *
   * @return The date when the contract ends.
   */
  public int getEndDate() {
    return this.endDate;
  }

  /**
   * Returns the cost of renting the item.
   *
   * @return The cost of renting the item.
   */
  public int getRentingCost() {
    return this.rentingCost;
  }

  /**
   * Transfers credits from the borrower to the lender.
   */
  public void transferCredits() {
    this.borrower.changeCredits(-this.rentingCost);
    this.lender.changeCredits(this.rentingCost);
  }
}
