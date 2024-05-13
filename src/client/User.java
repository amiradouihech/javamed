/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hp
 */
public class User {
    private StringProperty codeUser;
     private StringProperty emailUser;
    private StringProperty motPasseUser;
    private StringProperty nomUser;
    private StringProperty telUser;

    @Override
    public String toString() {
        return "User{" + "codeUser=" + codeUser.get() + ", emailUser=" + emailUser.get() + ", motPasseUser=" + motPasseUser.get() + ", nomUser=" + nomUser.get() + ", telUser=" + telUser.get() + '}';
    }
    

    public User(String codeUser,String emailUser, String motPasseUser, String nomUser, String telUser) {
        this.codeUser = new SimpleStringProperty(codeUser);
        this.emailUser=new SimpleStringProperty(emailUser);
        this.motPasseUser = new SimpleStringProperty(motPasseUser);
        this.nomUser = new SimpleStringProperty(nomUser);
        this.telUser = new SimpleStringProperty(telUser);
    }
    public String getemailUser() {
        return this.emailUser.get();
    }

    public void setemailUser(String emailUser) {
        this.codeUser.set(emailUser);
    }
    public String getCodeUser() {
        return this.codeUser.get();
    }

    public void setCodeUser(String codeUser) {
        this.codeUser.set(codeUser);
    }

    public String getMotPasseUser() {
        return motPasseUser.get();
    }

    public void setMotPasseUser(String motPasseUser) {
        this.motPasseUser.set(motPasseUser);
    }

    public String getNomUser() {
        return nomUser.get();
    }

    public void setNomUser(String nomUser) {
        this.nomUser.set(nomUser);
    }

    public String getTelUser() {
        return telUser.get();
    }

    public void setTelUser(String telUser) {
        this.telUser.set(telUser);
    }
    
    
    
    
    
}
