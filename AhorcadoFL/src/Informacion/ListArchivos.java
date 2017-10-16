/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Informacion;

import java.util.ArrayList;

/**
 *
 * @author angelraymundo
 */
public class ListArchivos {
    ArrayList<cPalabra> saveP = new ArrayList<>();
    ArrayList<cPuntaje> saveC = new ArrayList<>();
    Archivos arc = new Archivos();
    
    public ListArchivos(){
        saveP=arc.LeerP();
        saveC=arc.LeerC();
    }
    public void AgregarP(String Pal,String Desc){
        cPalabra pal = new cPalabra();
        pal.setPalabra(Pal);
        pal.setDescr(Desc);
        saveP.add(pal);
        arc.SerializarP(saveP);
    }
    public void EliminarP(int index){
        saveP.remove(index);
        arc.SerializarP(saveP);
    }
    public void AgregarC(String Nom,int ac,int err,int time){
        cPuntaje Pun = new cPuntaje();
        Pun.setNom(Nom);
        Pun.Total(ac, err,time);
        int pos=-1;
        for(cPuntaje punt:saveC){
            if(punt.getTotal()<Pun.getTotal()){
                pos=saveC.indexOf(punt);
                break;
            }
        }
        if(pos==-1){
            if(saveC.size()<10){
                saveC.add(Pun);
            }
        }else{
            saveC.add(pos,Pun);
            if(saveC.size()==11){
                saveC.remove(10);
            }
        }
        
        arc.SerializarC(saveC);
    }
    public ArrayList<cPalabra> ObPal(){
        return saveP;
    }
    public ArrayList<cPuntaje> ObPun(){
        return saveC;
    }
}
