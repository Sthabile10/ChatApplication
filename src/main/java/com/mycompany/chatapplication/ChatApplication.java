/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author setha
 */
public class ChatApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();
        
        //User Registration
        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        userLogin.setFirstName(firstName);

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        userLogin.setLastName(lastName);

        while (true) {
            System.out.print("Enter Username (max 5 chars & must include '_'): ");
            String username = scanner.nextLine();
            userLogin.setUsername(username);

            if (userLogin.checkUserName()) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        while (true) {
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            userLogin.setPassword(password);

            if (userLogin.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        while (true) {
            System.out.print("Enter South African Cell Phone (+27...): ");
            String cellPhone = scanner.nextLine();
            userLogin.setCellPhoneNumber(cellPhone);

            if (userLogin.checkCellPhoneNumber()) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        }

        System.out.println("\nRegistration successful!\n");

        //User Login
        System.out.println("=== USER LOGIN ===");
        System.out.print("Enter Username to Login: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter Password to Login: ");
        String loginPassword = scanner.nextLine();

        boolean success = userLogin.loginUser(loginUsername, loginPassword);
        String statusMessage = userLogin.returnLoginStatus(success);
        System.out.println(statusMessage);

        //PART 2: QUICKCHAT MENU
        if (success) {
            runQuickChatMenu(scanner);
        } else {
            System.out.println("Access denied. Please check your login details.");
        }

        scanner.close();
    }

        //MENU METHOD
    public static void runQuickChatMenu(Scanner scanner) {
        Random random = new Random();

        //Display Welcome Message
        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;

        //Application runs in a loop until user selects Option 3 (Quit)
        while (running) {
            System.out.println("\nPlease choose an option from the menu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("How many messages would you like to enter? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                        //Auto-generate 10-digit Message ID
                        long random10Digit = 1000000000L + (long)(random.nextDouble() * 9000000000L);
                        String messageID = String.valueOf(random10Digit);

                        //Capture Recipient Cell Number
                        System.out.print("Enter Recipient Cell Number (e.g., +27718693002): ");
                        String recipient = scanner.nextLine();

                        //Capture Message Body
                        System.out.print("Enter Message: ");
                        String messageBody = scanner.nextLine();

                        //Create Message Instance using Message.java
                        Message msg = new Message(messageID, recipient, messageBody);

                        //Validate Length (<= 250 characters)
                        String lengthCheck = msg.validateMessageLength();
                        if (!lengthCheck.equals("Message ready to send.")) {
                            System.out.println(lengthCheck);
                            i--;
                            continue;
                        }

                        //Validate Recipient Number Format
                        String cellCheck = msg.checkRecipientCell();
                        System.out.println(cellCheck);

                        //Prompt user action options
                        System.out.println("\nSelect an option:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Choice: ");
                        int actionChoice = scanner.nextInt();
                        scanner.nextLine();

                        String actionResult = msg.SentMessage(actionChoice);
                        System.out.println(actionResult);

                        if (actionChoice == 3) {
                            msg.storeMessage();
                        }

                        //Display full details after completion
                        System.out.println("\n--- Message Details ---");
                        System.out.println(msg.printMessages());
                    }

                    //Display accumulated total messages sent
                    System.out.println("\nTotal Messages Sent: " + Message.returnTotalMessagess());
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}