/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author setha
 */
public class Message {
    private String messageID;
    private String recipientCell;
    private String messageBody;
    private String messageHash;
    private String sentOption;
    private static int totalMessagesSent = 0;

    // Constructor
    public Message(String messageID, String recipientCell, String messageBody) {
        this.messageID = messageID;
        this.recipientCell = recipientCell;
        this.messageBody = messageBody;
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.length() <= 10 && recipientCell.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageBody == null || messageBody.trim().isEmpty()) {
            return "INVALID_HASH";
        }
        
        String firstTwoDigits = messageID.substring(0, 2);
        String[] words = messageBody.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String hash = firstTwoDigits + ":" + "0" + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String validateMessageLength() {
        if (messageBody.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = messageBody.length() - 250;
            return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
        }
    }
    
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                this.sentOption = "Sent";
                return "Message successfully sent.";
            case 2:
                this.sentOption = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.sentOption = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageBody;
    }

    public static int returnTotalMessagess() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        String jsonContent = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"recipient\": \"" + recipientCell + "\",\n" +
                "  \"message\": \"" + messageBody + "\",\n" +
                "  \"hash\": \"" + messageHash + "\",\n" +
                "  \"status\": \"" + sentOption + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonContent + "\n");
            System.out.println("Message stored to JSON successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving to JSON: " + e.getMessage());
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}
