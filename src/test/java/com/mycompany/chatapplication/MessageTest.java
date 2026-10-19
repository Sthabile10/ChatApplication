/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author setha
 */
public class MessageTest {

    @Test
    public void testValidateMessageLengthSuccess() {
        Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    @Test
    public void testValidateMessageLengthFailure() {
        String longMessage = "A".repeat(260);
        Message msg = new Message("1234567890", "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.validateMessageLength());
    }

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message("1234567890", "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message("1234567890", "+27718693002", "Test Message");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}