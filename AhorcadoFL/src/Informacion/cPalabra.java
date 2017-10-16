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
public class cPalabra implements Serializable {
    String _palabra,_descr;

    public String getPalabra() {
        return _palabra;
    }

    public void setPalabra(String _palabra) {
        this._palabra = _palabra;
    }

    public String getDescr() {
        return _descr;
    }

    public void setDescr(String _descr) {
        this._descr = _descr;
    }
    
}
