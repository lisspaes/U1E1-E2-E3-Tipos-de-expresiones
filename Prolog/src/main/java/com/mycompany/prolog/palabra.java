
package com.mycompany.prolog;

import java.util.ArrayList;

public class palabra {

    String cadena = "( 9 + 9 ) * .2 / 6^2 - b7.6";
    ArrayList<Float> numeros = new ArrayList();
    ArrayList<Character> caracter = new ArrayList();
    ArrayList<String> cadenas = new ArrayList();
    
    public void separador(){
    
        System.out.println("Palabras");
        String [] partes = cadena.split(" ");
        float verificador;
        String verifica2;
        
        for(int i = 0; i < partes.length; i++) {
            
            try{
               verificador = Float.parseFloat(partes[i]);
               numeros.add(verificador);      
            }catch(Exception e){
                verifica2 = partes[i];
                if (verifica2.length()>1) {
                    cadenas.add(verifica2);
                }else{
                    caracter.add(verifica2.charAt(0));
                }              
            }
        }
        
        System.out.println("Numeros: "+numeros);
        System.out.println("Total de números: "+numeros.size());
        System.out.println("Cadena: "+cadenas);
        System.out.println("Total de cadenas: "+cadenas.size());
        System.out.println("Caracter: "+caracter);
        System.out.println("Total de caracteres: "+caracter.size());

        
    }
}
