/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PastionMedicament;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hp
 */
public class Pamed {
    private StringProperty idPa;
    private StringProperty idMe;
    private Date date;
    private IntegerProperty qte;

    

    public Pamed(String idPa,String idMe, Date date, Integer qte) {
        this.idMe =new SimpleStringProperty(idMe) ;
        this.date = date;
        this.qte =new SimpleIntegerProperty(qte);
        this.idPa=new SimpleStringProperty(idPa);
    }

    public Pamed() {
    }

   
    public String getIdPa() {
        return idPa.get();
    }

    public void setIdPa(String idPa) {
        this.idPa.set(idPa);
    }

    public String getIdMe() {
        return idMe.get();
    }

    public void setIdMe(String idMe) {
        this.idMe.set(idMe);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQte() {
        return qte.get();
    }

    public void setQte(Integer qte) {
        this.qte.set(qte);
    }

    @Override
    public String toString() {
        return "Pamed{" + "idPa=" + idPa.get() + ", idMe=" + idMe.get() + ", date=" + date + ", qte=" + qte.get() + '}';
    }

    
}
