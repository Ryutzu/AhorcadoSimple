/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Informacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author angelraymundo
 */
public class Archivos {
    ArrayList<cPalabra> saveP = new ArrayList<>();
    ArrayList<cPuntaje> saveC = new ArrayList<>();
    
    public ArrayList<cPalabra> LeerP(){
        try{
            ObjectInputStream ob = new ObjectInputStream(new FileInputStream("Palabras.dat"));
            saveP = (ArrayList<cPalabra>)ob.readObject();
        }catch(Exception e){
            
        }
        return saveP;
    }
    public ArrayList<cPuntaje> LeerC(){
        try{
            ObjectInputStream ob = new ObjectInputStream(new FileInputStream("Puntajes.dat"));
            saveC = (ArrayList<cPuntaje>)ob.readObject();
        }catch(Exception e){
            cPuntaje lol = new cPuntaje();
            lol.setNom("Dios");
            lol.setTotal(0);
            saveC.add(lol);
        }
        return saveC;
    }
    public void SerializarP(ArrayList<cPalabra> list){
        try{
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Palabras.dat"));
            ob.writeObject(list);
            ob.close();
        }catch(Exception e){
            
        }
    }
    public void SerializarC(ArrayList<cPuntaje> list){
        try{
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Puntajes.dat"));
            ob.writeObject(list);
            ob.close();
        }catch(Exception e){
            
        }
    }
}
