package model;

import java.util.Date;

public class Contract {
  private Item item;
  private Member lender;
  private Member borrower;
  private Date startDate;
  private Date endDate;
  private int rentingCost;

  public Contract(Item item, Member lender, Member borrower, Date startDate, Date endDate, int rentingCost) {
    this.item = item;
    this.lender = lender;
    this.borrower = borrower;
    this.startDate = startDate;
    this.endDate = endDate;
    this.rentingCost = rentingCost;
  }

  // ^^ Are these immutable...?
  public Item getItem() {
    return this.item;
  }

  public Member getLender() {
    return this.lender;
  }

  public Member getBorrower() {
    return this.borrower;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public int getRentingCost() {
    return this.rentingCost;
  }

  // Transfer credits from borrower to lender
  public void transferCredits() {
    this.borrower.changeCredits(-this.rentingCost);
    this.lender.changeCredits(this.rentingCost);
  }
}
