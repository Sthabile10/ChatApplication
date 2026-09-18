/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapplication;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author setha
 */
public class LoginTest {
    //Implement JUnit unit test suite for user authentication
    @Test
    public void testCheckUserName_Success() {
        Login login = new Login();
        login.setUsername("kyl_1");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckUserName_Failure() {
        Login login = new Login();
        login.setUsername("kyle!!!!!!");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_Success() {
        Login login = new Login();
        login.setPassword("Ch&&sec@ke99!");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_Failure() {
        Login login = new Login();
        login.setPassword("password");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber_Success() {
        Login login = new Login();
        login.setCellPhoneNumber("+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Failure() {
        Login login = new Login();
        login.setCellPhoneNumber("08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginUser_Success() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(loginResult);
    }

    @Test
    public void testLoginUser_Failure() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("wrong_user", "wrong_pass");
        assertFalse(loginResult);
    }

    @Test
    public void testReturnLoginStatus_Success() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String actualMessage = login.returnLoginStatus(true);
        String expectedMessage = "Welcome Kyle Smith it is great to see you again.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String actualMessage = login.returnLoginStatus(false);
        String expectedMessage = "Username or password incorrect, please try again.";
        assertEquals(expectedMessage, actualMessage);
    }
}
