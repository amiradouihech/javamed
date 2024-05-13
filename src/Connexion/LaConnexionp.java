/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class LaConnexionp {
    private static Connection con;
     public static Connection seConnecter() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdpharmacie", "root", "");
                System.out.println("Connexion établie");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver MySQL introuvable");
            } catch (SQLException ex) {
                System.out.println("Base de données non trouvée ou problème d'identification : " + ex.getMessage());
            }
        }
        return con;
    }
    
}
