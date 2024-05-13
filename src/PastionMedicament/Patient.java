/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hp
 */
public class Patient {
    private StringProperty codePat;
    private StringProperty nomPat;
    private StringProperty telPat;

    public Patient(String codePat, String nomPat, String telPat) {
        this.codePat =new SimpleStringProperty(codePat);
        this.nomPat = new SimpleStringProperty(nomPat);
        this.telPat = new SimpleStringProperty(telPat) ;
    }

    public String getCodePat() {
        return codePat.get();
    }

    public void setCodePat(String codePat) {
        this.codePat.set(codePat);
    }

    public String getNomPat() {
        return nomPat.get();
    }

    public void setNomPat(String nomPat) {
        this.nomPat.set(nomPat);
    }

    public String getTelPat() {
        return telPat.get();
    }

    public void setTelPat(String telPat) {
        this.telPat.set(telPat);
    }

    @Override
    public String toString() {
        return "Patient{" + "codePat=" + codePat.get() + ", nomPat=" + nomPat.get() + ", telPat=" + telPat.get() + '}';
    }

   
    
    
}
