/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapplication;

import com.mycompany.chatapplication.Message;
import com.mycompany.chatapplication.Message;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author setha
 */
public class MessageTest {

    // 1. Test message length validation success (Under 250 characters)
    @Test
    public void testValidateMessageLengthSuccess() {
        Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    // 2. Test message length validation failure (Exceeds 250 characters)
    @Test
    public void testValidateMessageLengthFailure() {
        String longMessage = "A".repeat(260);
        Message msg = new Message("1234567890", "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.validateMessageLength());
    }

    // 3. Test exact boundary condition (Exactly 250 characters)
    @Test
    public void testValidateMessageLengthBoundary() {
        String exactMessage = "A".repeat(250);
        Message msg = new Message("1234567890", "+27718693002", exactMessage);
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    // 4. Test cell phone number formatting success (+27 international code)
    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message("1234567890", "+27718693002", "Hello there!");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // 5. Test cell phone number formatting failure (Local format starting with 0)
    @Test
    public void testCheckRecipientCellFailureLocalFormat() {
        Message msg = new Message("1234567890", "08575975889", "Hello there!");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // 6. Test cell phone number formatting failure (Missing plus sign)
    @Test
    public void testCheckRecipientCellFailureMissingPlus() {
        Message msg = new Message("1234567890", "27718693002", "Hello there!");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // 7. Test message hash generation (Test Data Case 1 format)
    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    // 8. Test message ID verification (Ensures valid ID length)
    @Test
    public void testCheckMessageIDValid() {
        Message msg = new Message("1234567890", "+27718693002", "Test");
        assertTrue(msg.checkMessageID());
    }

    // 9. Test message sending option 1 (Send Message)
    @Test
    public void testSentMessageOption1() {
        Message msg = new Message("1234567890", "+27718693002", "Test");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    // 10. Test message sending option 2 (Disregard Message)
    @Test
    public void testSentMessageOption2() {
        Message msg = new Message("1234567890", "+27718693002", "Test");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    // 11. Test message sending option 3 (Store Message)
    @Test
    public void testSentMessageOption3() {
        Message msg = new Message("1234567890", "+27718693002", "Test");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}