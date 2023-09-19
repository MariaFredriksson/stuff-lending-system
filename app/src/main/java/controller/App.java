package controller;

import model.Member;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // adapt to start the application in your way
    // model.Simple m = new model.Simple();
    // Simple c = new Simple();
    // view.Simple v = new view.Simple();

    // c.doSomethingSimple(m, v);

    // Create a new Member object
    Member johnDoe = new Member("John Doe", "john.doe@example", 12345678, "12345678");

    // Print the member's name
    System.out.println(johnDoe.getName());

    // Print the date the member was created
    System.out.println(johnDoe.getCreationDate());

    // Create another member
    Member janeDoe = new Member("Jane Doe", "jane.doe@example", 87654321, "87654321");

    // Print the member's name
    System.out.println(janeDoe.getName());

    // Print the date the member was created
    System.out.println(janeDoe.getCreationDate());
  }
}
