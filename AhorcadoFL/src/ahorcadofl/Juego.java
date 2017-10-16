/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcadofl;

import Informacion.ListArchivos;
import Informacion.cPalabra;
import Informacion.cPuntaje;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author angelraymundo
 */
public class Juego extends JFrame implements ActionListener,KeyListener,Runnable{
    JButton _ok,_nel;
    JLabel _titu,_ahor,_text,_text2, _tiempo;
    Thread _hilo;
    boolean _cronometroActivo;
    static int onoff =0;
    String _seg,_min;
    JTextField _1,_2;
    char[] _letras;
    JTextField[] _letritas;
    JScrollPane _pan;
    JList _sel;
    Random _count = new Random();
    cPalabra _tem = new cPalabra();
    int op,_ex,_errores,_cont;
    ListArchivos _DB = new ListArchivos();
    public Juego(){
        
        Vent();
        
        _tiempo = new JLabel( "00:00:000" );
        _tiempo.setFont( new Font( Font.SERIF, Font.BOLD,14 ) );
        _tiempo.setVisible(false);
        _tiempo.setOpaque( true );
        add( _tiempo );
        
        _titu = new JLabel();
        _titu.setLayout(null);
        add(_titu);
        _1 = new JTextField();
        _1.setLayout(null);
        add(_1);
        _2 = new JTextField();
        _2.setLayout(null);
        add(_2);
        _text = new JLabel();
        _text.setLayout(null);
        add(_text);
        _text2 = new JLabel();
        _text2.setLayout(null);
        add(_text2);
        _ok = new JButton();
        _ok.setLayout(null);
        _ok.addActionListener(this);
        add(_ok);
        _nel = new JButton();
        _nel.setLayout(null);
        _nel.addActionListener(this);
        add(_nel);
        _ahor = new JLabel();
        _ahor.setLayout(null);
        _ahor.setVisible(false);
        add(_ahor);
        Inicio();
        setVisible(true);
    }
    private void Ahorcado(int index){
        if(index==0){
            _ahor.setIcon(new ImageIcon("src/Imagenes/Inicio.png"));
        }else if(index==1){
            _ahor.setIcon(new ImageIcon("src/Imagenes/2.png"));
        }else if(index==2){
            _ahor.setIcon(new ImageIcon("src/Imagenes/3.png"));
        }else if(index==3){
            _ahor.setIcon(new ImageIcon("src/Imagenes/4.png"));
        }else if(index==4){
            _ahor.setIcon(new ImageIcon("src/Imagenes/5.png"));
        }else if(index==5){
            _ahor.setIcon(new ImageIcon("src/Imagenes/6.png"));
        }else if(index==6){
            _ahor.setIcon(new ImageIcon("src/Imagenes/7.png"));
        }else if(index==7){
            _ahor.setIcon(new ImageIcon("src/Imagenes/8.png"));
        }else if(index==8){
            _ahor.setIcon(new ImageIcon("src/Imagenes/9.png"));
        }else if(index==9){
            _ahor.setIcon(new ImageIcon("src/Imagenes/10.png"));
        }else if(index==10){
            _ahor.setIcon(new ImageIcon("src/Imagenes/Muerto.png"));
        }else if(index==11){
            _ahor.setIcon(new ImageIcon("src/Imagenes/Salvado.png"));
        }
        
    }
    private void Vent(){
        setTitle("AhorcadoFL");
        setLayout(null);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    private void Inicio(){
        op=0;
        _ahor.setVisible(false);
        _titu.setText("Seleccione una opcion");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 35));
        _titu.setBounds(300, 50, 500, 50);
        
        _ok.setText("Comienze el Juego");
        _ok.setBounds(100, 300, 350, 200);
        
        _nel.setText("Datos");
        _nel.setBounds(550, 300, 350, 200);
        
    }
    private void Datos(){
        op=2;
        _ahor.setVisible(false);
        _titu.setText("Seleccione una opcion");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 35));
        _titu.setBounds(300, 50, 500, 50);
        
        _ok.setText("Administrar Palabras");
        _ok.setBounds(100, 300, 350, 200);
        
        _nel.setText("Consultar Top 10");
        _nel.setBounds(550, 300, 350, 200);
    }
    private void Palabras(){
        op=3;
        _titu.setText("Seleccione una palabra o registrela");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 24));
        _titu.setBounds(225, 50, 500, 50);
        
        _sel = new JList();
        _sel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        try{
           DefaultListModel palabritas = new DefaultListModel();
            for(cPalabra pal:_DB.ObPal()){
                palabritas.addElement(pal.getPalabra());
            }
            _sel.setModel(palabritas); 
        }catch(Exception e){
            
        }
        _pan = new JScrollPane();
        _pan.setBounds(250,120, 500,400);
        _pan.setViewportView(_sel);
        _pan.setVisible(true);
        add(_pan);
        _ok.setBounds(200, 600, 200, 50);
        _ok.setText("Siguiente");
        _nel.setBounds(600, 600, 200, 50);
        _nel.setText("Regresar");
    }
    private void Puntajes(){
        op=6;
        _titu.setText("Top 10");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 24));
        _titu.setBounds(225, 50, 500, 50);
        
        _sel = new JList();
        _sel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _sel.setFont(new Font("Times Roman",Font.BOLD,28));
           DefaultListModel palabritas = new DefaultListModel();
           
            for(cPuntaje pun:_DB.ObPun()){
                palabritas.addElement(pun.getNom()+"---"+pun.getTotal());
            }
            _sel.setModel(palabritas); 
        
        _pan = new JScrollPane();
        _pan.setBounds(250,120, 500,400);
        _pan.setViewportView(_sel);
        _pan.setVisible(true);
        add(_pan);
        _ok.setVisible(false);
        _nel.setBounds(600, 600, 200, 50);
        _nel.setText("Regresar");
    }
    private void RegPal(){
        op=4;
        _titu.setText("Registro");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 24));
        _titu.setBounds(225, 50, 500, 50);
        _text.setVisible(true);
        _text2.setVisible(true);
        _1.setVisible(true);
        _2.setVisible(true);
        _text.setText("Palabra");
        _text.setBounds(100, 120, 100, 50);
        _1.setBounds(300, 120, 200, 50);
        _text2.setText("Descripcion");
        _text2.setBounds(100,200, 100, 50);
        _2.setBounds(300,200, 200, 50);
    }
    private void ModPal(cPalabra lol,int index){
        op=5;
        _ex=index;
        _titu.setText("Registro");
        _titu.setFont(new Font("Tahoma", Font.BOLD, 24));
        _titu.setBounds(225, 50, 500, 50);
        _text.setVisible(true);
        _text2.setVisible(true);
        _1.setVisible(true);
        _2.setVisible(true);
        _text.setText("Palabra");
        _text.setBounds(100, 120, 100, 50);
        _1.setBounds(300, 120, 200, 50);
        _1.setText(lol.getPalabra());
        _text2.setText("Descripcion");
        _text2.setBounds(100,200, 100, 50);
        _2.setBounds(300,200, 200, 50);
        _2.setText(lol.getDescr());
        
    }
    private void Juego(cPalabra act){
        op=1;
        _cont=0;
        _errores=0;
        _letras = new char[act.getPalabra().length()];
        
        for(int xPos=0;xPos<act.getPalabra().length();xPos++){
            _letras[xPos]=act.getPalabra().charAt(xPos);
        }
        _titu.setText("Ayuda");
        _titu.setFont(new Font("Tahoma", Font.BOLD,16));
        _titu.setBounds(475, 10, 200, 25);
        _text.setVisible(true);
        _text.setText(act.getDescr());
        _text.setFont(new Font("Tahoma", Font.BOLD,16));
        _text.setBounds(100,35,800, 25);
        
        _ahor.setVisible(true);
        Ahorcado(0);
        _ahor.setBounds(300,75, 402, 398);
        
        _letritas = new JTextField[act.getPalabra().length()];
        int ran = (int) (_count.nextDouble()*_letras.length);
        for(int xNum=0;xNum<act.getPalabra().length();xNum++){
            _letritas[xNum] = new JTextField();
            _letritas[xNum].setBounds(100+xNum*60,500,50,50);
            _letritas[xNum].setFont(new Font("Tahoma", Font.BOLD,32));
            _letritas[xNum].setVisible(true);
            
            if(_letras[xNum]==' '){
                _letritas[xNum].setText(String.valueOf(act.getPalabra().charAt(xNum)));
                _letritas[xNum].setEditable(false);
                _cont++;
            }else if(ran==xNum){
                _letritas[xNum].setText(String.valueOf(act.getPalabra().charAt(xNum)));
                _letritas[xNum].setEditable(false);
                _cont++;
            }
            _letritas[xNum].addKeyListener(this);
            add(_letritas[xNum]);
        }
        
        _ok.setBounds(200, 700, 200, 50);
        _ok.setText("Aceptar");
        _tiempo.setBounds(450, 700, 100, 50);
        _tiempo.setVisible(true);
        _nel.setBounds(600, 700, 200, 50);
        _nel.setText("Regresar");
        if(onoff == 0){
            onoff = 1;
            _cronometroActivo = true;
            _hilo = new Thread( this );
            _hilo.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==_ok){
            if(op==0){
                int pos = (int) (_count.nextDouble()*_DB.ObPal().size());
                Juego(_DB.ObPal().get(pos));  
            }else if(op==1){
                int sig = 0;
                for(int xNum=0;xNum<_letras.length;xNum++){
                    if(_letritas[xNum].isEditable()){
                        if(_letritas[xNum].getText().equalsIgnoreCase(String.valueOf(_letras[xNum]))){
                            _letritas[xNum].setEditable(false);
                            _cont++;
                            if(_cont==_letras.length){
                                Ahorcado(11);
                                JOptionPane.showMessageDialog(this, "Ganaste!!");
                                for(int xNu=0;xNu<_letras.length;xNu++){
                                      _letritas[xNu].setVisible(false);
                                  }
                                  _letritas = null;
                                 _text.setVisible(false);
                                 if (onoff == 1){
                                    onoff = 0;
                                    _cronometroActivo = false;
                                  }
                                   int time = Integer.parseInt(_seg)+Integer.parseInt(_min)*60;
                                   _tiempo.setVisible(false);
                                 _DB.AgregarC(JOptionPane.showInputDialog("Ingresa tu Nombre"),_cont,_errores,time);
                                 Inicio();
                                 break;
                            }
                        }else if(_letritas[xNum].getText().equalsIgnoreCase("")){

                        }else{
                            _letritas[xNum].setText("");
                            sig=1;
                        }
                    }
                }
                if(sig==1){
                    _errores++;
                    if(_errores==10){
                        Ahorcado(10);
                        JOptionPane.showMessageDialog(this, "Muerto jaja");
                          for(int xNum=0;xNum<_letras.length;xNum++){
                                _letritas[xNum].setVisible(false);
                            }
                            _letritas = null;
                           _text.setVisible(false);
                           if (onoff == 1){
                             onoff = 0;
                             _cronometroActivo = false;
                           }
                            int time = Integer.parseInt(_seg)+Integer.parseInt(_min)*60;
                            _tiempo.setVisible(false);
                           _DB.AgregarC(JOptionPane.showInputDialog("Ingresa tu Nombre"),_cont,_errores,time);
                           Inicio(); 
                    }else{
                        Ahorcado(_errores);
                    }
                }
            }else if(op==2){
                Palabras();
            }else if(op==3){
                if(_sel.getSelectedIndex()==-1){
                    RegPal();
                    _pan.setVisible(false);
                }else{
                    ModPal(_DB.ObPal().get(_sel.getSelectedIndex()),_sel.getSelectedIndex());
                    _pan.setVisible(false);
                }
            }else if(op==4){
                if(_1.getText().equals("")||_2.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Campos vacios");
                }else{
                    String pal = _1.getText();
                    String desc = _2.getText();
                    _DB.AgregarP(pal,desc);
                    JOptionPane.showMessageDialog(this, "Hecho");
                    _text.setVisible(false);
                    _text2.setVisible(false);
                    _1.setVisible(false);
                    _2.setVisible(false);
                    Palabras();
                }
            }else if(op==5){
                if(_1.getText().equals("")||_2.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Campos vacios");
                }else{
                    String pal = _1.getText();
                    String desc = _2.getText();
                    _DB.EliminarP(_ex);
                    _DB.AgregarP(pal,desc);
                    JOptionPane.showMessageDialog(this, "Hecho");
                    _text.setVisible(false);
                    _text2.setVisible(false);
                    _1.setVisible(false);
                    _2.setVisible(false);
                    Palabras();
                    _ex=0;
                }
            }
            
            
        }else if(e.getSource()==_nel){
            if(op==0){
                Datos();
            }else if(op==1){
                for(int xNum=0;xNum<_letras.length;xNum++){
                    _letritas[xNum].setVisible(false);
                }
                _letritas = null;
               _text.setVisible(false);
               
               if (onoff == 1){
                   onoff = 0;
                    _cronometroActivo = false;
                }
               _tiempo.setVisible(false);
               Inicio(); 
            }else if(op==2){
               Puntajes(); 
            }else if(op==3){
               _pan.setVisible(false);
               Inicio(); 
            }else if(op==4){
                _text.setVisible(false);
                _text2.setVisible(false);
                _1.setVisible(false);
                _2.setVisible(false);
                Palabras();
            }else if(op==5){
                _text.setVisible(false);
                _text2.setVisible(false);
                _1.setVisible(false);
                _2.setVisible(false);
                Palabras();
            }else if(op==6){
               _pan.setVisible(false);
               _ok.setVisible(true);
               Inicio(); 
            }
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(int xNum=0;xNum<_letras.length;xNum++){
            if(e.getSource()==_letritas[xNum]){
               if(_letritas[xNum].getText().length()==1){
                   e.consume();
               }
            }
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void run() {
       Integer minutos = 0 , segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min="", seg="", mil="";
        try
        {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while(_cronometroActivo )
            {
                Thread.sleep( 4 );
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;

                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                    }
                }

                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();

                if( milesimas < 10 ) mil = "00" + milesimas;
                else if( milesimas < 100 ) mil = "0" + milesimas;
                else mil = milesimas.toString();

                //Colocamos en la etiqueta la informacion
                _tiempo.setText( min + ":" + seg + ":" + mil );
                _seg=seg;
                _min=min;
            }
        }catch(Exception e){}
        //Cuando se reincie se coloca nuevamente en 00:00:000
        _tiempo.setText( "00:00:000" );
    }
    
}
