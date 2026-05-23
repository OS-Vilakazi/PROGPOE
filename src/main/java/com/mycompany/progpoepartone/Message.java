/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepartone;
//to have resizable array 
import java.util.ArrayList;
//Random class is used to generate pseudo-random numbers in java
import java.util.Random;
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
