package model;

public class Time {
  private int todaysDate;

  public Time() {
  todaysDate = 0; 
  }

  public int getTodaysDate() {
    return todaysDate;
  }

  public void setTodaysDate(int todaysDate) {
    this.todaysDate = todaysDate;
  }

  public void advanceDayCounter() {
    int day = getTodaysDate();
    setTodaysDate(day + 1);
  }
}