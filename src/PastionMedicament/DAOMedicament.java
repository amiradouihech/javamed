/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;
import Connexion.LaConnexionp;
import PastionMedicament.Medicament.Type;
import static client.DAOUser.cn;
import static client.DAOUser.encrypt;
import static client.DAOUser.recherUser;
import client.User;
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
public class DAOMedicament {
    public static Connection cn=LaConnexionp.seConnecter();
    public static Medicament recherMedicament(String co){
        String requete="select * from medicament where codeMed=?";
        Medicament m=null;
        try{
            PreparedStatement pst=cn.prepareStatement(requete);
            pst.setString(1, co);
            ResultSet rs=pst.executeQuery();
            String codeMed,nomMed;
            float prixMed;int qteMed;Type typeMed;
            while(rs.next()){
                codeMed=rs.getString("codeMed");
                nomMed=rs.getString("nomMed");
                prixMed=rs.getFloat("qteMed");
                qteMed=rs.getInt("qteMed");
                typeMed=(Type) rs.getObject("typeMed");
                m=new Medicament(codeMed,nomMed,prixMed,qteMed,typeMed); 
                
            }
            System.out.println("exixte ok");
            
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return m;
    }
        public static boolean ajouter(Medicament m){
        Medicament ma=recherMedicament(m.getCodeMed());
        boolean test=false;
            if(ma !=null){
                System.out.println("Medicament déja exisete");
            }
            else{
               String requete="insert into medicament values(?,?,?,?,?)" ;
               try{
                   PreparedStatement pst=cn.prepareStatement(requete);
                  pst.setString(1,m.getCodeMed());
                  pst.setString(2,m.getNomMed());
                  pst.setFloat(3,m.getPrixMed()); 
                  pst.setInt(4,m.getQteMed()); 
                  pst.setObject(5,ma.getTypeMed()); 
                  int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("ajouter reusite medicament");
                      return true;
                  }
               }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
               
            }
            return test;
    }
        public static ArrayList<Medicament> lister(){
        
        ArrayList<Medicament> c1=new ArrayList();
        String requete ="SELECT * FROM medicament;";
        String codeMed,nomMed;
        float prixMed;int qteMed;Type typeMed;
        Medicament m;
        Statement st;
        try{
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                  codeMed=rs.getString("codeMed");
                nomMed=rs.getString("nomMed");
                prixMed=rs.getFloat("qteMed");
                qteMed=rs.getInt("qteMed");
                typeMed=(Type) rs.getObject("typeMed");
                m=new Medicament(codeMed,nomMed,prixMed,qteMed,typeMed); 
                c1.add(m);
                
            }
            System.out.println("consultation ok");
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return c1;
    }
    /*public static TreeSet<Medicament> listerParVille(String ville){
        TreeSet<Medicament> res=new TreeSet();
        String requete ="select * from client where adresseCli ='"+ville+";";
       String codeCl;
        String nomCl,adrCl,emailCl;
        Client c;
        Statement st;
        try{
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                codeCl=rs.getString("codeCli");
                nomCl=rs.getString("nomCli");
                adrCl=rs.getString("adresseCli");
                emailCl=rs.getString("emailCli");
                c=new Client(codeCl,nomCl,adrCl,emailCl);
                res.add(c);
                
            }
            System.out.println("consultation ok");
        }catch (SQLException e){
            System.out.println("probleme "+e.getMessage());
        }
        return res;
        
    }*/
      public static boolean supprimer(Medicament m){
            Medicament m1=recherMedicament(m.getCodeMed());
            boolean test=false;
            if(m1==null){
                System.out.println("Medicament déja exite pas ");
            }
            else{
                 String requete="DELETE FROM `medicament` WHERE codeMed=?";
                try{
                    PreparedStatement pst=cn.prepareStatement(requete);
                    pst.setString(1,m.getCodeMed()); 
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
      public static boolean modifier(Medicament m){
        Medicament ma=recherMedicament(m.getCodeMed());
        boolean test=false;
            if(ma !=null){
                System.out.println("Medicament déja exisete");
            }
            else{
               String requete="update medicament SET nomMed=?,prixMed=?,qteMed=?,typeMed=? where codeMed=?;" ;
               try{
                   PreparedStatement pst=cn.prepareStatement(requete);
                  pst.setString(5,m.getCodeMed());
                  pst.setString(1,m.getNomMed());
                  pst.setFloat(2,m.getPrixMed()); 
                  pst.setInt(3,m.getQteMed()); 
                  pst.setObject(4,ma.getTypeMed()); 
                  int n=pst.executeUpdate();
                  if(n>=1){
                      System.out.println("modifier reusite medicament");
                      return true;
                  }
               }catch (SQLException e){
                     System.out.println("probleme "+e.getMessage());
                }
               
            }
            return test;
    }
}
