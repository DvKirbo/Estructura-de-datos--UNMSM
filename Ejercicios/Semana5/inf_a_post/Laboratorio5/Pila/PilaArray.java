package PilasResolver;

import java.util.Arrays;

/* @author Yayiiiiiiiir */
public class PilaArray {
    private final int max;
    private final int Pila[];
    private int cima;
    
    public PilaArray(){
        this.max = 20;
        this.Pila = new int[max + 1];
        this.cima = 0;
    }
    
    public PilaArray(int max){
        this.max = max;
        this.Pila = new int[max + 1];
        this.cima = 0;
    }
    
    public boolean isPilaVacia(){
        if(cima == -1){
            return true;
        }else{
            return false;
        }         
    }
    
    public boolean isPilaLlena(){
        if(cima == max){
            return true;
        }else{
            return false;
        }           
    }
    
    public void insertarPila(int elem){
        if(cima == max){
            System.out.println("Pila llena. No se puede insertar");
        }else{
            cima++;
            Pila[cima] = elem;
        }
    }
    
    public int eliminarPila(){
        if(cima == -1){
            System.out.println("Cola Vacia. No hay elementos");
            return 0;
        }else{
            int fuera = Pila[cima];
            cima--;
            return fuera;
        }
    }
    
    public void vaciarPila(){
        cima = -1;
    }
    
    public String mostrarPila(){
        if(isPilaVacia()){
            return "Pila Vacia. No hay elementos";
        }else{
            return toString();
        }
    }
    
    public int ultimoPila(){
        if(isPilaVacia()){
            System.out.println("Pila Vacia");
            return 0;
        }else{
            System.out.println("Ultimo elemento " + Pila[cima]);
            return Pila[cima];
        }
    }
    
    public String toString(){
        String texto = "";
        for(int i=0; i<=cima; i++){
            texto = texto + "\n" + Pila[i];
        }
        
        return texto;
    }
}
