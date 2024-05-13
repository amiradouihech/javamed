/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Connexion.LaConnexionp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author hp
 */
public class DAOUser {
    public static Connection cn=LaConnexionp.seConnecter();
    public static User recherUser(String co){
        String requete="select * from user where codeUser=?";
        User u=null;
        try{
            PreparedStatement pst=cn.prepareStatement(requete);
            pst.setString(1, co);
            ResultSet rs=pst.executeQuery();
            String codeUser,emailUser,motPasseUser,nomUser,telUser;
            while(rs.next()){
                codeUser=rs.getString("codeUser");
                motPasseUser=rs.getString("motPasseUser");
                nomUser=rs.getString("nomUser");
                telUser=rs.getString("telUser");
                emailUser=rs.getString("emailUser");
                u=new User(codeUser,emailUser,motPasseUser,nomUser,telUser); 
                
            }
            System.out.println("exixte ok");
            
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return u;
    }
      public static String encrypt(String password){
        String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
    }
         public static String decrypt(String password){
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
    }
         public static User login(String em,String mot){
             boolean test=false;
             String requete="select * from users where emailUser= ? and motPasseUser= ?;";
             User u=null;
             
             try{
                 PreparedStatement pst=cn.prepareStatement(requete);
                 pst.setString(1, em);
                 pst.setString(2, encrypt(mot));
                 ResultSet rs=pst.executeQuery();
                  String codeUser,emailUser,motPasseUser,nomUser,telUser;
            while(rs.next()){
                codeUser=rs.getString("codeUser");
                motPasseUser=rs.getString("motPasseUser");
                nomUser=rs.getString("nomUser");
                telUser=rs.getString("telUser");
                emailUser=rs.getString("emailUser");
                u=new User(codeUser,emailUser,motPasseUser,nomUser,telUser); 
                
            }
            System.out.println("ok");
                 
                 
             }catch(SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
         return u;}
    public static boolean insrciption(User u){
        User u1=recherUser(u.getCodeUser());
        boolean test=false;
            if(u1 !=null){
                System.out.println("User déja exisete");
            }
            else{
               String requete="insert into User values(?,?,?,?,?)" ;
               try{
                   PreparedStatement pst=cn.prepareStatement(requete);
                  pst.setString(1,u1.getCodeUser());
                  pst.setString(2,u1.getemailUser());
                  pst.setString(3,encrypt(u1.getMotPasseUser())); 
                  pst.setString(4,u1.getNomUser()); 
                  pst.setString(5,u1.getTelUser()); 
                  int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("ajouter reusite");
                      return true;
                  }
               }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
               
            }
            return test;
    }
}
