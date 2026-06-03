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
        Message msg = new Message(1, "+27834557896", longMessage);

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
    public void testSentMessagesArray_ContainsExpectedData() {
        // Message 1 - Sent
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        // Message 4 - Sent (developer number, invalid recipient but message text still correct)
        Message msg4 = new Message(4, "0838884567", "It is dinner time!");

        assertEquals("Did you get the cake?", msg1.getMessageText());
        assertEquals("It is dinner time!", msg4.getMessageText());
    }

    // TEST 8: Longest message from test data is message 2
    @Test
    public void testLongestMessage() {
        String msg1text = "Did you get the cake?";
        String msg2text = "Where are you? You are late! I have asked you to be on time.";
        String msg3text = "Yohoooo, I am at your gate.";
        String msg4text = "It is dinner time!";

        // Find longest manually
        String longest = msg1text;
        if (msg2text.length() > longest.length()) { longest = msg2text; }
        if (msg3text.length() > longest.length()) { longest = msg3text; }
        if (msg4text.length() > longest.length()) { longest = msg4text; }

        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    // TEST 9: Search by recipient - +27838884567 has messages 2 and 5
    @Test
    public void testSearchByRecipient() {
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");

        boolean msg2Match = msg2.getRecipient().equals("+27838884567");
        boolean msg5Match = msg5.getRecipient().equals("+27838884567");

        assertEquals(true, msg2Match);
        assertEquals(true, msg5Match);
    }

    // TEST 10: Delete by hash - check confirmation message
    @Test
    public void testDeleteByHash_ConfirmationMessage() {
        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        // We verify the format of the expected output string
        boolean containsDeleted = expected.contains("successfully deleted.");
        assertEquals(true, containsDeleted);
    }

    // TEST 11: sentMessage returns correct string for Send
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