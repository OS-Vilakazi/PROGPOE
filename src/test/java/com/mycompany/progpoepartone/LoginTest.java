/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progpoepartone;


import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }

   

    /**
     * Test of checkUserName method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kais_";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
       
    }
    @org.junit.jupiter.api.Test
        public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kaiser";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Password1$";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of checkCellPhone method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testCheckCellPhone() {
        System.out.println("checkCellPhone");
        String cellPhone = "+27123456789";
        Login instance = new Login();
        Boolean expResult = true;
        Boolean result = instance.checkCellPhone(cellPhone);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @org.junit.jupiter.api.Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String storedUser = "kais_";
        String storedPass = "Password1$";
        String loginUser = "kais_";
        String loginPass = "Password1$";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.loginUser(storedUser, storedPass, loginUser, loginPass);
        assertEquals(expResult, result);
       
    }
    
}
