/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepartone;
//to have resizable array 
import java.util.ArrayList;
import java.util.Scanner;
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
        if (recipient.startsWith("+") && recipient.length() <= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
}