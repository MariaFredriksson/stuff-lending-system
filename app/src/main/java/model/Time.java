package model;

/**
 * The Time class represents the current date.
 */
public class Time {
  private int todaysDate;

  /**
   * Creates a new Time object.
   */
  public Time() {
    todaysDate = 0; 
  }

  /**
   * Creates a new Time object.
   */
  public int getTodaysDate() {
    return todaysDate;
  }

  /**
   * Sets the current date.
   *
   * @param todaysDate The current date.
   */
  public void setTodaysDate(int todaysDate) {
    this.todaysDate = todaysDate;
  }

  /**
   * Advances the current date by one day.
   */
  public void advanceDayCounter() {
    int day = getTodaysDate();
    setTodaysDate(day + 1);
  }
}