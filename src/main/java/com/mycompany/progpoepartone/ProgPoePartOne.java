package com.mycompany.progpoepartone;

import java.util.Scanner;

public class ProgPoePartOne {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login valid = new Login();

        
        //Declaring Variables
        String username, password, phoneNum, firstName, lastName;

        //Header
        System.out.println("༺ Sign Up ༻");

        //inputing the users name and last name
        System.out.println("Please enter your First Name");
        firstName = input.nextLine();

        System.out.println("Please enter your Last Name");
        lastName = input.nextLine();

        //Registering a valid username
        System.out.println("Please enter a Valid username");
        username = input.nextLine();

        //Validate
        
        while (!valid.checkUserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            System.out.print("Enter Username: ");
            username = input.nextLine();
        }
        System.out.println("Username successfully captured.");

        //Password Registration
        System.out.println("Please create a Valid Password it must conain:\n\u2022 at least 8 characters long\n\u2022 Contain a capital letter\n\u2022 Contain a number\n\u2022 Contain a special Character.");
        password = input.nextLine();

        //Validate 
        while (!valid.checkPasswordComplexity(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            System.out.print("Enter Password: ");
            password = input.nextLine();
        }
        System.out.println("Password successfully captured.");

        System.out.println("Please enter a Valid Cell phone number. ");
        phoneNum = input.nextLine();

        //Validate
        while (!valid.checkCellPhone(phoneNum)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            System.out.print("Try again: ");
            phoneNum = input.nextLine();
        }
        System.out.println("Cell Phone number successfully added.");

        //Login VERIFICATION
        System.out.println("\n LOGIN");
        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        //Verifying the login info
        if (valid.loginUser(username, password, loginUser, loginPass)) {

            System.out.println("Welcome to HermesChat");

            int menuChoice = 0; // stores the user's menu selection

            // Keep showing the menu until the user chooses Quit
            while (menuChoice != 3) {

                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");

                menuChoice = Integer.parseInt(input.nextLine());

                if (menuChoice == 1) {

                    // Ask how many messages the user wants to send this session
                    System.out.println("How many messages do you want to send?");
                    int numMessages = Integer.parseInt(input.nextLine());
                    
                    
                    // for loop runs exactly numMessages times
                    for (int i = 0; i < numMessages; i++) {

                        System.out.println("\nMessage " + (i + 1) + " of " + numMessages);

                        //validating recipient number
                        System.out.println("Enter recipient number (must start with +27 and be max 12 characters):");
                        String recipient = input.nextLine();

                        // If number is invalid, redo this iteration
                        if (!recipient.startsWith("+27") || recipient.length() > 12) {
                            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            i--; // step back so this attempt doesn't count
                            continue; // go back to the top of the loop
                        }

                        // Get and validate message text
                        System.out.println("Enter your message (max 250 characters):");
                        String text = input.nextLine();

                        // If message is too long, redo this iteration
                        if (text.length() > 250) {
                            int over = text.length() - 250;
                            System.out.println("Message exceeds 250 characters by " + over + "; please reduce the size.");
                            i--; // step back so this attempt doesn't count
                            continue; // go back to the top of the loop
                        }

                        // Create the message object with the entered details
                        Message msg = new Message(i + 1, recipient, text);

                        // Show the full message details
                        System.out.println(msg.printMessages());

                        // Ask the user what to do: send, discard, or store
                        String result = msg.sentMessage();
                        System.out.println(result);

                    } 

                    // Show total after all messages are done
                    Message temp = new Message(0, "+270000000000", "placeholder");
                    System.out.println("\nTotal messages sent: " + temp.returnTotalMessages());

                } else if (menuChoice == 2) {
                    System.out.println("Coming Soon.");

                } else if (menuChoice == 3) {
                    System.out.println("Goodbye.");

                } else if (menuChoice == 4) {

                    // Stored Messages sub-menu
                    int subChoice = 0;

                    while (subChoice != 7) {
                        System.out.println("\n--- Stored Messages Menu ---");
                        System.out.println("1) Display all stored messages (recipient + message)");
                        System.out.println("2) Display the longest message");
                        System.out.println("3) Search for a message by ID");
                        System.out.println("4) Search messages for a particular recipient");
                        System.out.println("5) Delete a message using its hash");
                        System.out.println("6) Display full message report");
                        System.out.println("7) Back to main menu");

                        subChoice = Integer.parseInt(input.nextLine());

                        if (subChoice == 1) {
                            // 2a - display stored messages
                            Message.displayStoredSendersAndRecipients();

                        } else if (subChoice == 2) {
                            // 2b - longest message
                            Message.displayLongestMessage();

                        } else if (subChoice == 3) {
                            // 2c - search by message ID
                            System.out.println("Enter Message ID to search:");
                            String searchID = input.nextLine();
                            Message.searchByMessageID(searchID);

                        } else if (subChoice == 4) {
                            // 2d - search by recipient
                            System.out.println("Enter recipient number to search:");
                            String searchRecipient = input.nextLine();
                            Message.searchByRecipient(searchRecipient);

                        } else if (subChoice == 5) {
                            // 2e - delete by hash
                            System.out.println("Enter message hash to delete:");
                            String hash = input.nextLine();
                            Message.deleteByHash(hash);

                        } else if (subChoice == 6) {
                            // 2f - full report
                            Message.displayReport();

                        } else if (subChoice == 7) {
                            // go back to main menu
                            System.out.println("Returning to main menu.");

                } else {
                    System.out.println("Invalid option, please choose 1, 2, or 3.");
                }
            }

        } else {
                    System.out.println("Invalid option, please choose 1, 2, 3, or 4.");
                }
            }

        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}
