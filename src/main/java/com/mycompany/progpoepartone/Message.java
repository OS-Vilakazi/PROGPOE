/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepartone;
//to have resizable array 
import java.util.ArrayList;
import java.util.Scanner;
//to help create a json file to store messages
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
//
/**
 *
 * @author falconsyre
 */
public class Message {
    
    //private variables
    private String messageID;       // random 10-digit number
    private int messageNumber;      // which number message this is (1, 2, 3...)
    private String recipient;       // the phone number we're sending to
    private String messageText;     // the actual message
    

    private static ArrayList<String> sentMessages = new ArrayList<>(); // stores all sent messages
    private static ArrayList<String> disregardedMessages = new ArrayList<>(); // all disregarded message details
    private static ArrayList<String> storedMessages      = new ArrayList<>(); // loaded from JSON file
    private static ArrayList<String> messageHashes       = new ArrayList<>(); // all hashes
    private static ArrayList<String> messageIDs          = new ArrayList<>(); // all IDs
    private static ArrayList<String> sentRecipients      = new ArrayList<>(); // recipients of sent messages
    private static ArrayList<String> sentTexts           = new ArrayList<>(); // texts of sent messages
    
     private static int totalMessagesSent = 0; // counts how many were sent
     
    //A Constructor to run new messages 
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();   // auto-generate the ID
    }
    
    // Builds a 10-digit ID by picking random digits one at a time
    private String generateMessageID() {
        String id = ""; // start with empty text
        for (int i = 0; i < 10; i++) {
            int randomDigit = (int) (Math.random() * 10); // random number 0-9
            id = id + randomDigit; // glue digit onto the end
        }
        return id;
    }

    // Returns true if the message ID is 10 characters or fewer
    public boolean checkMessageID() {
        if (messageID.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
    
    // Checks the recipient number starts with '+' and is max 10 characters
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

// Builds the hash: first 2 digits of ID + ":" + message number + ":" + first word + last word
    // Example result: "00:1:HITONIGHT"
    public String createMessageHash() {
        // Split the message into individual words using spaces
        String[] words = messageText.trim().split(" ");

        // Grab the first and last words
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Grab only the first 2 characters of the message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Build the hash by joining the parts with colons
        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;

        // Return it in all caps
        return hash.toUpperCase();
    }
    
    // Saves this message into a file called "messages.json"
    public void storeMessage() {
    try {
        // Build the message text
        String messageData = "{\n" +
            "  \"messageID\": \"" + messageID + "\",\n" +
            "  \"messageHash\": \"" + createMessageHash() + "\",\n" +
            "  \"recipient\": \"" + recipient + "\",\n" +
            "  \"message\": \"" + messageText + "\"\n" +
            "}";

        // Saves to your project folder automatically
        String projectPath = System.getProperty("user.dir") + "/messages.json";

        System.out.println("Saving to: " + projectPath);

        FileWriter writer = new FileWriter(projectPath, true);
        writer.write(messageData + ",\n");
        writer.flush();
        writer.close();

        System.out.println("File saved successfully.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    // Reads messages.json and loads each stored message into the storedMessages array
    public static void loadStoredMessages() {
        storedMessages.clear(); // clear first to avoid duplicates on repeated calls

        try {
            String projectPath = System.getProperty("user.dir") + "/messages.json";
            FileReader fr = new FileReader(projectPath);
            BufferedReader reader = new BufferedReader(fr);

            String line = "";
            String currentBlock = "";

            // Read file line by line, group each {...} block as one message
            while ((line = reader.readLine()) != null) {
                currentBlock = currentBlock + line + "\n";

                // Each message ends with "}," so we know the block is complete
                if (line.trim().equals("},")) {
                    storedMessages.add(currentBlock.trim());
                    currentBlock = ""; // reset for next message
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("No stored messages file found.");
        }
    }
    
     // Helper: extract a field value from a JSON block line
    // e.g. extractField(block, "message") returns the message text
    private static String extractField(String block, String fieldName) {
        String[] lines = block.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("\"" + fieldName + "\"")) {
                // Line looks like: "message": "Hi there"
                // Remove the field name and quotes to get the value
                String value = line.replace("\"" + fieldName + "\":", "").trim();
                value = value.replace("\"", "").trim();
                // Remove trailing comma if present
                if (value.endsWith(",")) {
                    value = value.substring(0, value.length() - 1);
                }
                return value;
            }
        }
        return "";
    }
    
    // Asks the user what to do with the message
    public String sentMessage() {
        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        int choice = Integer.parseInt(input.nextLine()); // read the user's choice

        if (choice == 1) {
            totalMessagesSent++;              // add 1 to the counter
            sentMessages.add(printMessages()); // save message details to the list
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Returns all message details as one block of text
    public String printMessages() {
        String details = "Message ID: " + messageID + "\n";
        details = details + "Message Hash: " + createMessageHash() + "\n";
        details = details + "Recipient: " + recipient + "\n";
        details = details + "Message: " + messageText;
        return details;
    }

    // Returns the total number of messages sent so far
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Getters - let other classes read private fields
    public String getMessageID()   { return messageID; }
    public String getMessageText() { return messageText; }
    public String getRecipient()   { return recipient; }
}
