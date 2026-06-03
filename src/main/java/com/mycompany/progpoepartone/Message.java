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
            sentRecipients.add(recipient);           // add recipient to recipients array
            sentTexts.add(messageText);              // add text to texts array
            messageHashes.add(createMessageHash());  // add hash to hashes array
            messageIDs.add(messageID);               // add ID to IDs array
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();                         // write to JSON file
            messageHashes.add(createMessageHash());
            messageIDs.add(messageID);
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
    
     //Display sender and recipient of all stored messages (reads from JSON)
    public static void displayStoredSendersAndRecipients() {
        loadStoredMessages();

        if (storedMessages.size() == 0) {
            System.out.println("No stored messages found.");
            return;
        }

        System.out.println("\n--- Stored Messages: Recipient and Message ---");
        for (int i = 0; i < storedMessages.size(); i++) {
            String block = storedMessages.get(i);
            String recipient = extractField(block, "recipient");
            String message = extractField(block, "message");
            System.out.println((i + 1) + ". Recipient: " + recipient + " | Message: " + message);
        }
    }
    
    //Display the longest message across sent and stored messages
    public static void displayLongestMessage() {
        // Collect all message texts
        ArrayList<String> allTexts = new ArrayList<>();

        // Add sent message texts
        for (int i = 0; i < sentTexts.size(); i++) {
            allTexts.add(sentTexts.get(i));
        }

        // Add stored message texts from JSON
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String text = extractField(storedMessages.get(i), "message");
            if (!text.equals("")) {
                allTexts.add(text);
            }
        }

        if (allTexts.size() == 0) {
            System.out.println("No messages found.");
            return;
        }

        // Find the longest one by looping through all texts
        String longest = allTexts.get(0);
        for (int i = 1; i < allTexts.size(); i++) {
            if (allTexts.get(i).length() > longest.length()) {
                longest = allTexts.get(i);
            }
        }

        System.out.println("\nLongest message: " + longest);
    }
    
    //Search for a message by ID, display recipient and message
    public static void searchByMessageID(String searchID) {
        boolean found = false;

        // Search in sent messages
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(searchID)) {
                if (i < sentRecipients.size()) {
                    System.out.println("Recipient: " + sentRecipients.get(i));
                    System.out.println("Message: " + sentTexts.get(i));
                }
                found = true;
            }
        }

        // Also search in stored JSON messages
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String id = extractField(storedMessages.get(i), "messageID");
            if (id.equals(searchID)) {
                System.out.println("Recipient: " + extractField(storedMessages.get(i), "recipient"));
                System.out.println("Message: " + extractField(storedMessages.get(i), "message"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No message found with ID: " + searchID);
        }
    }
    
    //Search for all messages sent or stored for a particular recipient
    public static void searchByRecipient(String searchRecipient) {
        boolean found = false;

        System.out.println("\nMessages for " + searchRecipient + ":");

        // Search sent messages
        for (int i = 0; i < sentRecipients.size(); i++) {
            if (sentRecipients.get(i).equals(searchRecipient)) {
                System.out.println("- " + sentTexts.get(i));
                found = true;
            }
        }

        // Search stored messages from JSON
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String recipient = extractField(storedMessages.get(i), "recipient");
            if (recipient.equals(searchRecipient)) {
                String message = extractField(storedMessages.get(i), "message");
                System.out.println("- " + message);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for: " + searchRecipient);
        }
    }
    
    //Delete a message using its hash
    public static void deleteByHash(String hashToDelete) {
        boolean found = false;
        String upperHash = hashToDelete.toUpperCase();

        // Search the messageHashes array
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(upperHash)) {
                // Get the message text before removing it
                String deletedText = "";
                if (i < sentTexts.size()) {
                    deletedText = sentTexts.get(i);
                    sentTexts.remove(i);
                    sentRecipients.remove(i);
                    messageIDs.remove(i);
                }
                messageHashes.remove(i);

                System.out.println("Message: \"" + deletedText + "\" successfully deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with hash: " + hashToDelete);
        }
    }
    
    // Getters - let other classes read private fields
    public String getMessageID()   { return messageID; }
    public String getMessageText() { return messageText; }
    public String getRecipient()   { return recipient; }
}
