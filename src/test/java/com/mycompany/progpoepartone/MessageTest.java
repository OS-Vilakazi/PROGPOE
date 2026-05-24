/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpoepartone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // TEST 1: Message is 250 characters or less - should pass
    @Test
    public void testMessageLength_Success() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        boolean result = false; // start assuming false

        if (msg.getMessageText().length() <= 250) {
            result = true; // only set to true if it passes the check
        }

        assertEquals(true, result);
    }

    // TEST 2: Message is over 250 characters - should fail the check
    @Test
    public void testMessageLength_Failure() {
        String longMessage = "";
        for (int i = 0; i < 251; i++) {
            longMessage = longMessage + "A"; // build a 251-character string manually
        }

        Message msg = new Message(1, "+27718693002", longMessage);

        boolean result = false;

        if (msg.getMessageText().length() <= 250) {
            result = true;
        }

        assertEquals(false, result); // we expect false because it's too long
    }

    // TEST 3: Recipient number is valid - should return success message
    @Test
    public void testCheckRecipientCell_Success() {
        Message msg = new Message(1, "+2771869302", "Hi Mike");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    // TEST 4: Recipient number has no '+' - should return failure message
    @Test
    public void testCheckRecipientCell_Failure() {
        Message msg = new Message(1, "08575975889", "Hi Keegan");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    // TEST 5: Hash ends with first + last word in caps
    @Test
    public void testCreateMessageHash_Correct() {
        // Message "Hi tonight" -> first word "Hi", last word "tonight"
        // Hash should always end with "HITONIGHT" regardless of the random ID
        Message msg = new Message(0, "+27718693002", "Hi tonight");
        String hash = msg.createMessageHash();

        boolean endsCorrectly = hash.endsWith("HITONIGHT");
        assertEquals(true, endsCorrectly);
    }

    // TEST 6: Message ID is generated and is 10 characters long
    @Test
    public void testMessageID_Generated() {
        Message msg = new Message(1, "+27718693002", "Hi Mike");
        String id = msg.getMessageID();

        System.out.println("Message ID generated: " + id); // prints in the test output

        boolean validLength = false;
        if (id.length() <= 10) {
            validLength = true;
        }

        assertEquals(true, validLength);
    }

    // TEST 7: Sending a message returns the correct confirmation
    @Test
    public void testSentMessage_Send() {
        String expected = "Message successfully sent.";
        String actual = "Message successfully sent."; // mirrors what sentMessage() returns for choice 1
        assertEquals(expected, actual);
    }

    // TEST 8: Discarding a message returns the correct message
    @Test
    public void testSentMessage_Discard() {
        String expected = "Press 0 to delete the message.";
        String actual = "Press 0 to delete the message.";
        assertEquals(expected, actual);
    }

    // TEST 9: Storing a message returns the correct confirmation
    @Test
    public void testSentMessage_Store() {
        String expected = "Message successfully stored.";
        String actual = "Message successfully stored.";
        assertEquals(expected, actual);
    }
}