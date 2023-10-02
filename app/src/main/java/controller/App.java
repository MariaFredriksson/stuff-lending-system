package controller;

import java.util.Date;

// import java.util.Date;

import model.Member;
import model.Item.ItemCategory;
import controller.AdminController;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   * 
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    try {
      // adapt to start the application in your way
      // model.Simple m = new model.Simple();
      // Simple c = new Simple();
      // view.Simple v = new view.Simple();

      // c.doSomethingSimple(m, v);

      // Create a new Member object
      Member johnDoe = new Member("John Doe", "john.doe@example", 12345678, "12345678");

      // Print the member's name
      // System.out.println(johnDoe.getName());

      // // Print the date the member was created
      // System.out.println(johnDoe.getCreationDate());

      // Create another member
      Member janeDoe = new Member("Jane Doe", "jane.doe@example", 87654321, "87654321");

      // // Print the member's name
      // System.out.println(janeDoe.getName());

      // // Print the date the member was created
      // System.out.println(janeDoe.getCreationDate());

      // // Add 10 credits to John Doe's account
      // johnDoe.changeCredits(10);

      // // Print John Doe's credits
      // System.out.println(johnDoe.getCredits());

      // // Deduct 5 credits from John Doe's account
      // johnDoe.changeCredits(-5);

      // // Print John Doe's credits
      // System.out.println(johnDoe.getCredits());

      // Add an item to John Doe's account
      johnDoe.addItem(ItemCategory.Tool, "Hammer", "A hammer", 17);

      // Print the item's name, cost per day, and owner's name
      // System.out.println(johnDoe.getOwnedItems().get(0).getName());
      // System.out.println(johnDoe.getOwnedItems().get(0).getCostPerDay());
      // System.out.println(johnDoe.getOwnedItems().get(0).getOwner().getName());

      // // Print John Doe's credits
      // System.out.println(johnDoe.getCredits());

      // Add another item to John Doe's account
      johnDoe.addItem(ItemCategory.Tool, "Screwdriver", "A screwdriver", 12);

      // Print the name of all items owned by John Doe
      for (int i = 0; i < johnDoe.getOwnedItems().size(); i++) {
        System.out.println(johnDoe.getOwnedItems().get(i).getName());
      }

      // // Try to delete the first item from John Doe's account - in the wrong way
      // johnDoe.getOwnedItems().remove(0);

      // // Print the name of all items owned by John Doe
      // for (int i = 0; i < johnDoe.getOwnedItems().size(); i++) {
      // System.out.println(johnDoe.getOwnedItems().get(i).getName());
      // }

      // // Delete the first item from John Doe's account - in the right way
      // johnDoe.deleteItem(johnDoe.getOwnedItems().get(0));

      // // Print the name of all items owned by John Doe
      // for (int i = 0; i < johnDoe.getOwnedItems().size(); i++) {
      // System.out.println(johnDoe.getOwnedItems().get(i).getName());
      // }

      // ? Trying to change the date John Doe was created to test if it is immutable
      // // Print the date John Doe was created
      // System.out.println(johnDoe.getCreationDate());

      // // Pause the program for 5 seconds
      // try {
      // Thread.sleep(5000);
      // } catch (InterruptedException e) {
      // e.printStackTrace();
      // }

      // // Change the date John Doe was created to tomorrow
      // Date johnDoeCreationDate = johnDoe.getCreationDate();
      // johnDoeCreationDate = new Date();

      // // Print the date John Doe was created
      // System.out.println(johnDoe.getCreationDate());
      // System.out.println(johnDoeCreationDate);
      // ?
      // --------------------------------------------------------------------------------

      // Give Jane Doe 100 credits
      janeDoe.changeCredits(100);

      // Jane Doe tries to rent John Doe's hammer for 2 days from now - i.e. in 3 days
      johnDoe.getOwnedItems().get(0).addContract(janeDoe, new Date(), new Date(new Date().getTime() + 2 * 24 * 60 * 60 * 1000));

      // Print Jane's and John's credits
      System.out.println(janeDoe.getCredits());
      System.out.println(johnDoe.getCredits());

      // Print the name of the item Jane rented
      System.out.println(johnDoe.getOwnedItems().get(0).getContractList().get(0).getItem().getName());

      // Print the end date of the contract
      System.out.println(johnDoe.getOwnedItems().get(0).getContractList().get(0).getEndDate());

      // Print the category of the item 
      System.out.println(johnDoe.getOwnedItems().get(0).getCategory());

      // Add a new member to the members list
      AdminController admin = new AdminController();
      Member newMember = admin.createMember("John Doe", "john.doe@example", 12345678);
      System.out.println(newMember.getName());

      // Add a new member with the same email
      Member newMember2 = admin.createMember("Jane Doe", "john.doe@example", 87654321);
      System.out.println(newMember2.getEmail());
      System.out.println(newMember);
      System.out.println(admin.getMembers());


      // TODO: Fix a generation of a uniqe alphanumeric memberID

      // TODO: Add method setEmail() to Member class with validation
      // TODO: Add method setMobileNumber() to Member class with validation

      // ! Hur ska få Member att prata med Admin eller App? Hur får vi ut en lista med alla medlemmar som är nåbar i setEmail mm metoden i en Member? Så att vi kan kolla så att email och mobilnummer inte redan finns i systemet.
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
