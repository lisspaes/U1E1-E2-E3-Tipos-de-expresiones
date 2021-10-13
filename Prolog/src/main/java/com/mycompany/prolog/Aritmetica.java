
package com.mycompany.prolog;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aritmetica {

    String[] letras = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    String valor = "(95+19)*0.2/6.6^4-7.46"; //-> "(a+b)*c/d^e-f";
    //Ponerle un "0" antes del punto decimal o no sale bien
    Stack<Character> temporal = new Stack();
    Stack<Character> contenedor = new Stack();
    HashMap<String, Float> valores = new HashMap();
    
    public void recorrer(){
        System.out.println("Programa aritmetico");
        
        System.out.println(valor);
        
        int k = 0;
        while(valor.matches(".*\\d.*")){
            Matcher matcher = Pattern.compile("\\d+\\.*\\d*").matcher(valor);
            matcher.find();
            valores.put(letras[k], Float.parseFloat(matcher.group()));
            valor = valor.replaceFirst("\\d+\\.*\\d*", letras[k]);
            k++;
        }
        
        System.out.println(valor);
        
        
        for (int i = 0; i < valor.length(); i++) {
            
            char actual = valor.charAt(i);
                      
            if(isANumber(actual)){
       
                contenedor.add(actual);
                
            }else{
            
                comprobacion(actual);
            }
                        
        }
        
        while(!temporal.empty()){

            contenedor.add(temporal.pop());

        }
        
        System.out.println("Cadena: "+contenedor);
        System.out.println("Temporal: "+temporal);

        Stack<String> resultado = new Stack();
        
        for (int i = 0; i < contenedor.size(); i++) {
            //if(!Character.isDigit(contenedor.get(i))){
            if(!Character.isLetter(contenedor.get(i))){
                //float b = Float.parseFloat(resultado.pop());
                //float a = Float.parseFloat(resultado.pop());
                float b = valores.get(resultado.pop());
                float a = valores.get(resultado.pop());
                float res = 0;
                String clave = "x" + i;
                
                switch(contenedor.get(i)){
                    
                    case '+':
                        //resultado.add((a+b)+"");
                        res = a + b;
                        valores.put(clave, res);
                        resultado.add(clave);
                        break;
                    
                    case '-':
                        //resultado.add((a-b)+"");
                        res = a - b;
                        valores.put(clave, res);
                        resultado.add(clave);
                        break;
                        
                    case '*':
                        //resultado.add((a*b)+"");
                        res = a * b;
                        valores.put(clave, res);
                        resultado.add(clave);
                        break;
                    
                    case '/':
                        //resultado.add((a/b)+"");
                        res = a / b;
                        valores.put(clave, res);
                        resultado.add(clave);
                        break;
                        
                    case '^':
                        //resultado.add((Math.pow(a, b))+"");
                        res = (float)Math.pow(a, b);
                        valores.put(clave, res);
                        resultado.add(clave);
                        break;     
                }
                
            }else{
                resultado.add(contenedor.get(i) + "");
            }
        }
        
        System.out.println("Resultado: "+ valores.get(resultado.peek()));
        System.out.println("Valor: "+valor);

        
    }
    
    
    public boolean isANumber(char actual){
        
        //boolean valor = Character.isDigit(actual);
        boolean valor = Character.isLetter(actual);
        return valor;       
    }
    
    
    public void comprobacion(char actual){
    
        if(temporal.empty()){
            temporal.add(actual);
        }else{
        
            char ultimo = temporal.peek();

            if(actual == '(' || ultimo == '(')
                temporal.add(actual);
            else if(actual == '+'||actual == '-'){
                organizadorSuR(ultimo, actual);
            }else if(actual == '*'||actual == '/'){
                organizadorMuD(ultimo, actual);
            }else if(actual =='^'){
                organizadorPot(ultimo, actual);
            }else if(actual == ')'){
                while(!temporal.empty() && temporal.peek() != '('){
                    contenedor.add(temporal.pop());
                }
                if(!temporal.empty()){
                    temporal.pop();
                }
            }
        }
    }
    
    public void organizadorSuR(char ultimo, char actual){
    
        if(ultimo == '+'||ultimo == '-'){
            contenedor.add(ultimo);
            temporal.pop();
            temporal.add(actual);
        }   
        
        if(ultimo == '*'||ultimo == '/'||ultimo=='^'){
            while(!temporal.empty() && temporal.peek() != '('){
                contenedor.add(temporal.pop());
            }
            temporal.add(actual);            
        }
    }
    
    public void organizadorMuD(char ultimo, char actual){
        if(ultimo == '*'||ultimo == '/'){
            contenedor.add(ultimo);
            temporal.pop();
            temporal.add(actual);
        }   
        
        if(ultimo == '+'||ultimo == '-'){
            temporal.add(actual);            
        }
        if(ultimo == '^'){
            /*contenedor.add(ultimo);
            temporal.pop();*/
            while(!temporal.empty() && temporal.peek() != '('){
                contenedor.add(temporal.pop());
            }
            temporal.add(actual);
        }   
                
    }
    
    public void organizadorPot(char ultimo, char actual){

        if(ultimo == '^'){
            contenedor.add(ultimo);
            temporal.pop();
            temporal.add(actual);
        }   
       
        if(ultimo == '+'||ultimo == '-'||ultimo == '*'||ultimo == '/'){
            
            temporal.add(actual);
        }   
        
    }

    
}
