package model.persistence;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.AdminModel;
import model.Item.ItemCategory;
import model.Member;
import model.Time;

/**
 * The DefaultData class is responsible for loading default data into the program.
 */
public class DefaultData implements PersistenceInterface {
  private AdminModel adminModel;
  private Time time;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Its ok")
  public DefaultData(AdminModel adminModel, Time time) {
    this.adminModel = adminModel;
    this.time = time;
  }

  @Override
  public void load() {
    try {
      addDefaultData();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
  }
  
  private void addDefaultData() throws Exception {
    // Add a new member to the members list
    Member jane = adminModel.createMember("Jane Doe", "jane.doe@example", "0701234569", time.getTodaysDate());
  
    // Add an item to the member jane
    jane.addItem(ItemCategory.Tool, "Hammer", "A tool for hammering", 50, time.getTodaysDate());
  
    jane.addItem(ItemCategory.Toy, "Ball", "A ball for playing", 10, time.getTodaysDate());
  
    jane.changeCredits(300);
  
    // Add a new member to the members list
    Member lasse = adminModel.createMember("Lasse", "lasse.doe@mail.com", "1234567896", time.getTodaysDate());
  
    lasse.changeCredits(100);
  
    // Add a new member to the members list
    Member alex = adminModel.createMember("Alex Doe", "alex.doe@example", "0701234575", time.getTodaysDate());
  
    alex.changeCredits(100);
  
    // Alex wants to borrow janes boll for 3 days (today included)
    jane.getOwnedItems().get(1).addContract(alex, time.getTodaysDate(), time.getTodaysDate() + 2);
  }
}
