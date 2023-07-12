/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.groupA.Dao;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author t
 */
public class NewClass {
    public static void main(String[] args) {
        System.out.println( BCrypt.hashpw("pass", BCrypt.gensalt()));
    }
    
}
