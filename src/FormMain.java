import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.*;
 
public class FormMain extends JFrame {
 
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
 
	 ArrayList<Evento> eventos = new ArrayList<Evento>();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	
    	
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FormMain frame = new FormMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    

    public FormMain() {
 
        //Parametros asociados a la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
 
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero de audio");
        textField.setBounds(52, 26, 209, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        JButton btnSeleccionar = new JButton("Seleccionar...");
        btnSeleccionar.setBounds(288, 25, 109, 23);
        contentPane.add(btnSeleccionar);
 
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(52, 76, 360, 156);
 
        JScrollPane scroll=new JScrollPane(textArea);
        scroll.setBounds(52, 76, 360, 156);
        contentPane.add(scroll);
 
        btnSeleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	//Creamos el objeto JFileChooser
            	JFileChooser fc=new JFileChooser();
            	 
            	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            	int seleccion=fc.showOpenDialog(contentPane);
            	 
            	//Si el usuario, pincha en aceptar
            	if(seleccion==JFileChooser.APPROVE_OPTION){
            	 
            	    //Seleccionamos el fichero
            	    File fichero=fc.getSelectedFile();
            	 
            	    //Ecribe la ruta del fichero seleccionado en el campo de texto
            	    textField.setText(fichero.getAbsolutePath());
            	 
            	   getArchivo(fichero.getAbsolutePath());
            	}
            }
            
            public Evento mostrarMayorKm(){
                Evento mayor = eventos.get(0);
                for(int i = 0; i < eventos.size(); i++){
                    if(eventos.get(i).getPrecio() > mayor.getPrecio())
                        mayor = eventos.get(i);
                }
                return mayor;
            }
           
            public String getArchivo( String ruta ){
                FileReader fr = null;
                BufferedReader br = null;
                //Cadena de texto donde se guardara el contenido del archivo
                String contenido = "";
                int sumaprecios = 0;
                int contprecios  = 0;
                
                int precio = 0;
                String nombreEvento;
                
     

                try
                {
                    //ruta puede ser de tipo String o tipo File
                    fr = new FileReader( ruta );
                    br = new BufferedReader( fr );
         
                    String linea;
                    //Obtenemos el contenido del archivo linea por linea
                    while( ( linea = br.readLine() ) != null ){ 
                    
               
                    		
                    		 try{
                    			 
                    			 String[] parts = linea.split("#");
                    			 nombreEvento = parts[0];
                    			 precio = Integer.parseInt(parts[1]);
                    			 	contprecios++;
                    			 	try {
                    			 		Evento  evento1= new Evento(parts[0],precio);
                                		eventos.add(evento1);

                    			 		
                    			 	}
                    			 	
                    			 	  catch (NumeroNegativoException e) {
                    	        	        textArea.setText(textArea.getText()+ "Numero negativo ");
                                  	 }
                            	
                            		
                                     contenido += linea + "\n";
                     			
                          }
                          catch (Exception e) {
                         
                         	 }
                    	
                    	
              
                    
                    }
                    int preciototal = 0;
                    int asistentestotales = 0;

        	        
                    for (int i = 0; i < eventos.size();i++) {

                   }
                    
                    
                    
                    for (int i = 0; i < eventos.size();i++) {
                    	preciototal += eventos.get(i).getPrecio();
         

                    }
                    Collections.sort(eventos);
                   int recaudacion = 2500 / eventos.get(eventos.size()-1).getPrecio();


        	        textArea.setText(" El precio medio de los eventos es de: "+preciototal  / eventos.size() + 
        	        		"€\n El evento mas caro es "+ eventos.get(eventos.size()-1).getNombre() +
        	        		" Con un precio de "+  eventos.get(eventos.size()-1).getPrecio()+ 
        	        		"€\n El evento mas barato es "+ eventos.get(0).getNombre() +
        	        		" Con un precio de "+  eventos.get(0).getPrecio()+
        	        		"€\n El evento "+ eventos.get(eventos.size()-1).getNombre() +
        	        		" necesita  "+  recaudacion + 
        	        		" asistentes para recaudar 2500€");
        	        		
        	        
                   
      
   
                }catch( Exception e ){  }
                //finally se utiliza para que si todo ocurre correctamente o si ocurre 
                //algun error se cierre el archivo que anteriormente abrimos
                finally
                {
                    try{
                        br.close();
                    }catch( Exception ex ){}
                }
                return contenido;
            }
        });
 
    }
    
    
}