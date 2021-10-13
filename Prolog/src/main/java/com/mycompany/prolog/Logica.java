
package com.mycompany.prolog;

import java.util.HashMap;
import java.util.Stack;

public class Logica {
    
    String cadena = "[(p->q)^p]->q";
    Stack<Character> letras = new Stack();
    Stack<Character> conectores = new Stack();
   
    
    public void organizador(){
        
        System.out.println("Programa Logico");
        
        cadena = cadena.replace("->", "#");
        cadena = cadena.replace("[", "(");
        cadena = cadena.replace("]", ")");

        char temporal;
        
        for (int i = 0; i < cadena.length(); i++) {
            
            temporal = cadena.charAt(i);
            
            if(temporal=='p'||temporal=='q'){
                
                letras.add(temporal);
                
            }else{
            
                comparador(temporal);
            }
            
        }      
         while(!conectores.empty()){

            letras.add(conectores.pop());

        }
            System.out.println(letras);
            System.out.println(conectores);
            resolvedor();
    }
    
    public void comparador(char temporal){
        //temporal es el ultimo en sacarse de cadena
        
        if(conectores.empty()){
            conectores.add(temporal);
        }else{
        
        char ultimo = conectores.peek();

            if(ultimo == '('||temporal=='('){
                conectores.add(temporal);
            }else if(temporal == '#'||temporal=='^'){
                conectores.add(temporal);
            }else if(temporal == ')'){
            
                 while(!conectores.empty() && conectores.peek() != '('){
                    letras.add(conectores.pop());
                }
                if(!conectores.empty()){
                    conectores.pop();
                }
                
            }
        }
    }

    public void resolvedor(){
        HashMap<String, char[]> dict = new HashMap<String, char[]>();
        dict.put("p", new char[] { 'V', 'V','F','F' });
        dict.put("q", new char[] { 'V', 'F','V','F' });
        
        Stack<String> resultado = new Stack();
        
//        char [] p= {'V', 'V','F','F'};
//        char [] q= {'V', 'F','V','F'};
//        char [] resultados = new char[4];
    
           for (int i = 0; i < letras.size(); i++) {
            
               //if(letras.get(i)!='p'||letras.get(i)!='q'){
               if(letras.get(i) == '#' || letras.get(i) == '^'){
                   
                String b = (resultado.pop());
                String a = (resultado.pop());
                char[] arrb = dict.get(b);
                char[] arra = dict.get(a);
                char[] nuevores = new char[4];
                String clave;
                switch(letras.get(i)){
                    
                    case '#':
                        
                        
                        for (int j = 0; j < 4; j++) {
                            
                            if(arra[j]=='V'& arrb[j]=='F'){
                                nuevores[j] = 'F';
                            }else{
                                nuevores[j]='V';
                            }
                        }
                        
                        clave = "x"+i;
                        dict.put(clave, nuevores);
                        resultado.add(clave);
                        
                        break;
                        
                    case '^':
                        
                        for (int j = 0; j < 4; j++) {
                            
                            if(arra[j]=='V'& arrb[j]=='V'){
                                nuevores[j] = 'V';
                            }else{
                                nuevores[j]='F';
                            }
                        }
                        
                        clave = "x"+i;
                        dict.put(clave, nuevores);
                        resultado.add(clave);                        
                        break;     
                }
                
            }else{
                resultado.add(letras.get(i) + "");
            }
        }
           
        dict.forEach((k, v)->{
            String linea = k + ": ";
            for(int i = 0; i < v.length; i++){
                linea += v[i];
            }

            System.out.println(linea);
        });
        
    }
    
    

    
}
