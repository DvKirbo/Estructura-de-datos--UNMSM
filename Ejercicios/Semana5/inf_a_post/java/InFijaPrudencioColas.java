
package infijaprudenciocolas;

import java.util.Scanner;
import java.util.Stack;

/* @author Yayiiiiiir */

public class InFijaPrudencioColas {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Escriba su expresión entre parentesis para evaluar");
        System.out.println("Operadores permitidos: +, -, *,/ y ^");
        System.out.print("Expresión (enter para finalizar): ");
        
        String line = console.nextLine().trim();
        while(line.length() > 0){
            revisar(line);
            convertirApostfija(line);
            System.out.println();
            System.out.print("Expresion (enter para finalizar): ");
            line = console.nextLine().trim();
        }
    }
    
    public static void convertirApostfija(String expresionInFija){
        Stack<Character> operadores = new Stack<>();
        StringSplitter inFijaConEspacios = new StringSplitter(expresionInFija);
        String postFija = "";
        boolean band = false;
        
        System.out.print("Expresion postfija: ");
        
        while(inFijaConEspacios.hasNext() && !band){
            String token = inFijaConEspacios.next();
            
            if(esOperando(token)){
                if(operadores.isEmpty()){
                    band=true;
                }else{
                    postFija += token + " ";
                }
            } else if(token.equals("(")){
                operadores.push(token.charAt(0));
            } else if(token.equals(")")){
                if(operadores.size() < 2 || operadores.peek().equals("(")){
                    band = true;
                }
                else{
                    while(!operadores.isEmpty() && operadores.peek() != '('){
                    postFija += operadores.pop() + " ";
                    }
                    operadores.pop();
                }
           
            } else{
                if(operadores.peek() != '('){
                        band = true;
                } else{
                    while(!operadores.isEmpty() && precedencia(token.charAt(0)) <= precedencia(operadores.peek())){
                        postFija += operadores.pop() + " ";
                    }
                    operadores.push(token.charAt(0));
                }
            }
        }
        
        if(band){
            System.out.println("Expresion ilegal");
        } else{
            while (!operadores.isEmpty()) {
                postFija += operadores.pop() + " ";
            }
            System.out.print(postFija);
        }
        
        System.out.println();
    }
    
    public static boolean esOperando(String token){
        return !(token.equals("(") || token.equals(")") || "+-*/^".contains(token));
    }
    
    public static int precedencia(char operador){
        return switch (operador) {
            case '+', '-', '*', '/', '^' -> 1;
            default -> -1;
        };
    }
    
    public static void revisar(String line){
        StringSplitter data = new StringSplitter(line);
        Stack<String> simbolos = new Stack<>();
        Stack<Double> valores = new Stack<>();
        boolean error = false;
        
        System.out.print("Resultado de la expresión infija: ");
        while(!error && data.hasNext()){
            String next = data.next();
            if(next.equals(")")){
                if(simbolos.size() < 2 || simbolos.peek().equals("(")){
                    error = true;
                }else{
                    String operador = simbolos.pop();
                    if(!simbolos.peek().equals("(")){
                        error = true;
                    }else{
                        simbolos.pop();
                        double op2 = valores.pop();
                        double op1 = valores.pop();
                        double valor = evaluar(operador, op1, op2);
                        valores.push(valor);
                    }
                }
            } else if("(+-*/^".contains(next)){
            simbolos.push(next);
            } else{
                valores.push(Double.parseDouble(next));
            }
        }
    
        if(error || valores.size() != 1 || !simbolos.isEmpty()){
            System.out.println("Expresion ilegal");
        } else{
            System.out.println(valores.pop());
        }
    }
    
    public static double evaluar(String operador, double operando1, double operando2){
        switch (operador) {
            case "+" -> {
                return operando1 + operando2;
            }
            case "-" -> {
                return operando1 - operando2;
            }
            case "*" -> {
                return operando1 * operando2;
            }
            case "/" -> {
                return operando1 / operando2;
            }
            case "^" -> {
                return Math.pow(operando1, operando2);
            }
            default -> throw new RuntimeException("Operador ilegal: " + operador);
        }
    }   
}