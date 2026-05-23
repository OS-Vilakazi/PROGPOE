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
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Tracks all sent messages across the session
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;
}
