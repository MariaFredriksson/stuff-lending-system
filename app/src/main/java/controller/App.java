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

    // Add 10 credits to John Doe's account
    johnDoe.changeCredits(10);

    // Print John Doe's credits
    System.out.println(johnDoe.getCredits());

    // Deduct 5 credits from John Doe's account
    johnDoe.changeCredits(-5);

    // Print John Doe's credits
    System.out.println(johnDoe.getCredits());

    // Add an item to John Doe's account
    johnDoe.addItem("Tool", "Hammer", "A hammer", 17);

    // Print the item's name, cost per day, and owner's name
    System.out.println(johnDoe.getOwnedItems().get(0).getName());
    System.out.println(johnDoe.getOwnedItems().get(0).getCostPerDay());
    System.out.println(johnDoe.getOwnedItems().get(0).getOwner().getName());

    // Print John Doe's credits
    System.out.println(johnDoe.getCredits());
  }
}
