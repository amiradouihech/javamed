/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hp
 */
public class Medicament {

    enum Type{spacial,normal};
    private StringProperty codeMed;
    private StringProperty nomMed;
    private FloatProperty prixMed;
    private IntegerProperty qteMed;
    private Type typeMed;

    public Medicament(String codeMed, String nomMed, float prixMed,int qteMed, Type typeMed) {
        this.codeMed = new SimpleStringProperty(codeMed);
        this.nomMed = new SimpleStringProperty(nomMed);
        this.prixMed = new SimpleFloatProperty(prixMed) ;
        this.qteMed = new SimpleIntegerProperty(qteMed) ;
        this.typeMed = typeMed;
    }

    public String getCodeMed() {
        return codeMed.get();
    }

    public void setCodeMed(String codeMed) {
        this.codeMed.set(codeMed);
    }

    public String getNomMed() {
        return nomMed.get();
    }

    public void setNomMed(String nomMed) {
        this.nomMed.set(nomMed);
    }

    public Float getPrixMed() {
        return prixMed.get();
    }

    public void setPrixMed(Float prixMed) {
        this.prixMed.set(prixMed);
    }

    public Integer getQteMed() {
        return qteMed.get();
    }

    public void setQteMed(Integer qteMed) {
        this.qteMed.set(qteMed);
    }

    public Type getTypeMed() {
        return typeMed;
    }

    public void setTypeMed(Type typeMed) {
        this.typeMed = typeMed;
    }

    @Override
    public String toString() {
        return "Medicament{" + "codeMed=" + codeMed.get() + ", nomMed=" + nomMed.get() + ", prixMed=" + prixMed.get()+ ", qteMed=" + qteMed.get() + ", typeMed=" + typeMed + '}';
    }
    
}
