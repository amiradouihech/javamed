/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;

import Connexion.LaConnexionp;
import static PastionMedicament.DAOPatient.cn;
import PastionMedicament.Medicament.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class DAOPamed {
 public static Connection cn = LaConnexionp.seConnecter();

    public static Pamed recherPamed(String id) {
        String requete = "select * from pamed where id=?";
        Pamed pamed = null;
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String idPa = rs.getString("idPa");
                String idMe = rs.getString("idMe");
                java.sql.Date date = rs.getDate("date");
                int qte = rs.getInt("qte");
                pamed = new Pamed(idPa, idMe, date, qte);
            }
            System.out.println("Recherche Pamed effectuée avec succès");

        } catch (SQLException e) {
            System.out.println("Problème lors de la recherche de Pamed: " + e.getMessage());
        }
        return pamed;
    }

    public static boolean ajouter(Pamed p) {
        Pamed pa = recherPamed(p.getIdPa());
        boolean ajoutReussi = false;
        if (pa != null) {
            System.out.println("Pamed déjà existant");
        } else {
            String requete = "insert into pamed (idPa, idMe, date, qte) values (?, ?, ?, ?)";
            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setString(1, p.getIdPa());
                pst.setString(2, p.getIdMe());
                pst.setDate(3, p.getDate());
                pst.setInt(4, p.getQte());
                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("Ajout réussi du Pamed");
                    ajoutReussi = true;
                }
            } catch (SQLException e) {
                System.out.println("Problème lors de l'ajout du Pamed : " + e.getMessage());
            }
        }
        return ajoutReussi;
    }

    public static boolean modifierByIdMe(Pamed p) {
        Pamed pa = recherPamed(p.getIdPa());
        boolean modificationReussie = false;
        if (pa == null) {
            System.out.println("Pamed inexistant");
        } else {
            String requete = "update pamed set date=?, qte=? where idMe=?";
            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setDate(1, p.getDate());
                pst.setInt(2, p.getQte());
                pst.setString(3, p.getIdMe());
                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("Modification réussie du Pamed par idMe");
                    modificationReussie = true;
                }
            } catch (SQLException e) {
                System.out.println("Problème lors de la modification du Pamed par idMe : " + e.getMessage());
            }
        }
        return modificationReussie;
    }

    public static boolean modifierByDate(Pamed p) {
        Pamed pa = recherPamed(p.getIdPa());
        boolean modificationReussie = false;
        if (pa == null) {
            System.out.println("Pamed inexistant");
        } else {
            String requete = "update pamed set idMe=?, qte=? where date=?";
            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setString(1, p.getIdMe());
                pst.setInt(2, p.getQte());
                pst.setDate(3, p.getDate());
                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("Modification réussie du Pamed par date");
                    modificationReussie = true;
                }
            } catch (SQLException e) {
                System.out.println("Problème lors de la modification du Pamed par date : " + e.getMessage());
            }
        }
        return modificationReussie;
    }

    public static boolean modifierByIdPation(Pamed p) {
        Pamed pa = recherPamed(p.getIdPa());
        boolean modificationReussie = false;
        if (pa == null) {
            System.out.println("Pamed inexistant");
        } else {
            String requete = "update pamed set idMe=?, date=?, qte=? where idPation=?";
            try {
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setString(1, p.getIdMe());
                pst.setDate(2, p.getDate());
                pst.setInt(3, p.getQte());
                pst.setString(4, p.getIdPa());
                int n = pst.executeUpdate();
                if (n >= 1) {
                    System.out.println("Modification réussie du Pamed par idPation");
                    modificationReussie = true;
                }
            } catch (SQLException e) {
                System.out.println("Problème lors de la modification du Pamed par idPation : " + e.getMessage());
            }
        }
        return modificationReussie;
    }
       public static ArrayList<Medicament> getMedicamentsByPatientId(String idPatient) {
        ArrayList<Medicament> medicaments = new ArrayList<>();
        String requete = "SELECT m.codeMed, m.nomMed, m.prixMed, m.qteMed, m.typeMed " +
                         "FROM medicament m " +
                         "JOIN pation p ON m.codeMed = p.codeMed " +
                         "WHERE p.imp = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, idPatient);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String codeMed = rs.getString("codeMed");
                String nomMed = rs.getString("nomMed");
                float prixMed = rs.getFloat("prixMed");
                int qteMed = rs.getInt("qteMed");
                Type typeMed=(Medicament.Type) rs.getObject("typeMed");
                Medicament medicament = new Medicament(codeMed, nomMed, prixMed, qteMed, typeMed);
                medicaments.add(medicament);
            }
            System.out.println("Sélection des médicaments par patient effectuée avec succès");
        } catch (SQLException e) {
            System.out.println("Problème lors de la sélection des médicaments par patient : " + e.getMessage());
        }
        return medicaments;
    }
}
