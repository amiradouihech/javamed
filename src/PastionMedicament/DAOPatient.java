/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;
import Connexion.LaConnexionp;
import static PastionMedicament.DAOMedicament.cn;
import static PastionMedicament.DAOMedicament.recherMedicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;
import jdk.nashorn.internal.codegen.CompilerConstants;
/**
 *
 * @author hp
 */
public class DAOPatient {
    public static Connection cn=LaConnexionp.seConnecter();
     public static Patient recherPatient(String co){
        String requete="select * from patient where codePat=?";
        Patient p=null;
        try{
            PreparedStatement pst=cn.prepareStatement(requete);
            pst.setString(1, co);
            ResultSet rs=pst.executeQuery();
            String codePat,nomPat,telPat;
            while(rs.next()){
                codePat=rs.getString("codePat");
                nomPat=rs.getString("nomPat");
                telPat=rs.getString("telPat");
                
                
                p=new Patient(codePat,nomPat,telPat); 
                
            }
            System.out.println("exixte ok");
            
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return p;
    }
      public static boolean ajouter(Patient p){
        Patient pa=recherPatient(p.getCodePat());
        boolean test=false;
            if(pa !=null){
                System.out.println("Patient déja exisete");
            }
            else{
               String requete="insert into patient values(?,?,?)" ;
               try{
                   PreparedStatement pst=cn.prepareStatement(requete);
                  pst.setString(1,p.getCodePat());
                  pst.setString(2,p.getNomPat());
                  pst.setString(3,p.getTelPat()); 
                
                  int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("ajouter reusite patient");
                      return true;
                  }
               }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
               
            }
            return test;
    }
       public static ArrayList<Patient> lister(){
        
        ArrayList<Patient> p1=new ArrayList();
        String requete ="SELECT * FROM patient;";
        String codePat,nomPat,telPat;
        Patient p;
        Statement st;
        try{
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                codePat=rs.getString("codePat");
                nomPat=rs.getString("nomPat");
                telPat=rs.getString("telPat");
                
                
                p=new Patient(codePat,nomPat,telPat); 
                p1.add(p);
                
            }
            System.out.println("consultation ok");
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return p1;
    }
           public static boolean supprimer(Patient p){
            Patient p1=recherPatient(p.getCodePat());
            boolean test=false;
            if(p1==null){
                System.out.println("Patient déja exite pas ");
            }
            else{
                 String requete="DELETE FROM `patient` WHERE codePat=?";
                try{
                    PreparedStatement pst=cn.prepareStatement(requete);
                    pst.setString(1,p.getCodePat()); 
                     int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("suprimmer reusite");
                      return true;
                  }
                    
                }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
            }
            return test;
        }
            public static boolean modifier(Patient p){
        Patient pa=recherPatient(p.getCodePat());
        boolean test=false;
            if(pa !=null){
                System.out.println("patient déja exisete");
            }
            else{
               String requete="update patient SET nomPat=?,telPat=? where codePat=?;" ;
               try{
                   PreparedStatement pst=cn.prepareStatement(requete);
                  pst.setString(3,p.getCodePat());
                  pst.setString(1,p.getNomPat());
                  pst.setString(2,p.getTelPat()); 
                  
                  int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("modifier reusite patient");
                      return true;
                  }
               }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
               
            }
            return test;
    }
}
