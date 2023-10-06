# A2 - The Stuff Lending System 

## Introduction

The Stuff Lending System is a Java application designed to manage the lending and borrowing of various items among members. This readme provides an overview of the application's structure, classes, functionality, and testing.

## Table of Contents

1. [Application Overview](#application-overview)
2. [Building](#building)
3. [How to Use the Application](#how-to-use-the-application)
4. [Application Behavior](#application-behavior)
5. [Testing](#testing)
6. [Authors](#authors)
7. [License](#license)

## Application Overview

The Stuff Lending System is organized into three main packages: `controller`, `model`, and `view`. Below is a brief overview of each package and its key classes:

- `controller`: Contains classes responsible for controlling interactions between the model and view.
  - `App`: The main class of the application, responsible for starting and managing the application's core functionality.
  - `AdminController`: Controls interactions related to administrative tasks such as member management.
  - `MemberController`: Manages interactions related to members' items and contracts.
  
- `model`: Contains classes that define the data structure and logic of the application.
  - `AdminModel`: Manages the list of members and provides methods for creating, editing, and deleting members.
  - `Member`: Represents a member with associated items and contracts.
  - `Item`: Represents an item that can be borrowed and includes information such as name, category, and description.
  - `Contract`: Represents a contract between a member and an item, specifying the borrowing period.

- `view`: Contains classes responsible for displaying information to the user and receiving input.
  - `AdminView`: Handles user interface interactions for administrative tasks.


## Building
Build the application by running console command `./gradlew build`  


## How to use the application

To use the Stuff Lending System application, follow these steps:

1. Start the application by running the console command `./gradlew run -q --console=plain`
2. The application will display a main menu with options for various actions, including member management, item management, and administrative tasks.
3. Select the desired option by entering the corresponding number.
4. Follow the prompts to perform the selected action.

Here are some key actions you can perform in the application:

- Create, edit, and delete members.
- Add, edit, and delete items for members.
- Create contracts to lend items to members.
- View a list of all members, items, or item information.
- Perform administrative tasks such as increasing the day count.

## Application Behavior

The Stuff Lending System operates based on the following principles:

- **Credits Transfer**: All credits for the entire loan period are transferred immediately when a contract is created.

- **Booking Restrictions**: Items can only be booked from today's date and onwards. If an item is not available now, it cannot be booked. Users must wait until the contract expires and the item becomes available.

- **Editing Members and Items**: When modifying a member or item, you need to enter all the details, regardless of what you want to change.

- **User Verification**: There is no verification of the user's identity or their rights within the system. This means that anyone can perform any action, and the system may be susceptible to misuse.


## Testing

Manual testing has been performed to ensure the application's functionality. A test report summarizing the test cases and results can be found in [testreport.md](testreport.md).

### Test Cases

Below are some of the test cases that have been performed:

#### Member Data
- Checking the existence of at least 3 Members.
- Creating members with specific credits and items.
- Verifying active lending contracts for members.

#### Create Member
- Creating a member with specific details and verifying creation.

#### Create Member - Duplicate Email and Phone
- Creating members with duplicate email and phone numbers and verifying creation or rejection.

#### Delete Member
- Creating a member, deleting it, and verifying deletion.

#### Create Item
- Creating an item for a member, checking item creation, and the member's credits.

#### Delete Item
- Deleting an item that is not involved in any contract and verifying deletion.
- Deleting an item that is booked (has a future contract) and verifying deletion and contract cancellation.

#### Create Contract
- Creating contracts with various conditions and verifying their creation or rejection.

#### Advance Time
- Advancing time to fulfill contracts and update member credits.

## Authors

The application has been developed by:
- Maddelen Hedenström <mh222vu@student.lnu.se>
- Maria Fredriksson <mf223wk@student.lnu.se>

## License

The Stuff Lending System is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute the code as per the terms of this license.
