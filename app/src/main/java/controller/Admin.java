// package controller;

// import java.util.ArrayList;
// import java.util.List;
// import model.Member;
// import model.Admin;
// public class Admin {

//   public void createMember(String name, String email, int mobileNumber) {
//     Member member = model.Admin.createMember(name, email, mobileNumber);
//   }

//   public void addMember(member);{
//     // Add the member to the list of members
//   members.add(member);
//   }
  
// }

package controller;

import java.util.ArrayList;
import java.util.List;
import model.Member;

public class Admin {

  private List<Member> members;
  private Admin admin; // Create an instance of model.Admin

  public Admin() {
    members = new ArrayList<>();
    admin = new Admin(); // Initialize the admin instance
  }
  
  private Member createMember(String name, String email, int mobileNumber) {
    // Create a new member using the admin instance
    Member member = admin.createMember(name, email, mobileNumber);
    addMember(member);
    return member;
  }
  
  public void addMember(Member member) {
    // Add the member to the list of members
    members.add(member);
  }

}

