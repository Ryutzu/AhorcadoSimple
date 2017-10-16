/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Informacion;

import java.io.Serializable;

/**
 *
 * @author angelraymundo
 */
public class cPuntaje implements Serializable {
    String _nom;
    double _tiempo,_total;

    public String getNom() {
        return _nom;
    }

    public void setNom(String _nom) {
        this._nom = _nom;
    }
    public void Total(int ac,int err,int time){
        
        if(err==0){
            _total=(ac*100)/time;
        }else{
            _total=((ac/err)*100)/time;
        }
    }

    public double getTiempo() {
        return _tiempo;
    }

    public void setTiempo(double _tiempo) {
        this._tiempo = _tiempo;
    }

    public double getTotal() {
        return _total;
    }

    public void setTotal(double _total) {
        this._total = _total;
    }
    
    
    
}
