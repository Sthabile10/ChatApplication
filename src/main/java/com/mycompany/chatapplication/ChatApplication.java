/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication;
import java.util.Scanner;
/**
 *
 * @author setha
 */
public class ChatApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

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

        System.out.println("=== USER LOGIN ===");
        System.out.print("Enter Username to Login: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter Password to Login: ");
        String loginPassword = scanner.nextLine();

        boolean success = userLogin.loginUser(loginUsername, loginPassword);
        String statusMessage = userLogin.returnLoginStatus(success);
        System.out.println(statusMessage);

        scanner.close();
    }
}
