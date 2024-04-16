import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AntiPlageSet {
    

    public static  Set <String> getPalabras(Scanner input){
        //ArrayList <String> words = new ArrayList<String>();
        Set <String> words = new HashSet<String>();
        input.useDelimiter("[^a-zA-Z']+");
        while(input.hasNext()){
            words.add(input.next().toLowerCase());
        }
        words.hashCode();
        return words;        
    }
    
    public static Set <String> getCoincidencias(Set <String> list1 ,Set<String> list2){
        Set <String> result =new HashSet<String>();
        int i1=0;
        int i2=0;
        //pasamos los elementos del set a un array para poder acceder a los indices
        String l1[] = list1.toArray(new String[0]);
        String l2[] = list2.toArray(new String[0]);
        //Ordenar ambos Strings antes para empezar a comparar
        Arrays.sort(l1);
        Arrays.sort(l2);
        while(i1<l1.length && i2<l2.length){
            int num =l1[i1].compareTo(l2[i2]);
            if(num == 0){
                result.add(l1[i1]);
                i1++;
                i2++;
            }
            else if (num<0) i1++;
            else if (num>0)i2++;
        }
        return result;
    }

    public static void Reporte(Set<String> list1, Set <String> list2, Set<String> comun){
        System.out.println("Archivo #1 palabras = " + list1.size());
        System.out.println("Archivo #2 palabras = " + list2.size());
        System.out.println("Palabras en común = " + comun.size());
        double pct1 = 100.0 * comun.size() / list1.size();
        double pct2 = 100.0 * comun.size() / list2.size();
        System.out.println("% del archivo 1 en superposición  = " + pct1);
        System.out.println("% del archivo 2 en superposición  = " + pct2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in1 =  new Scanner(new File("./p1.txt"));
        Scanner in2 = new Scanner(new File("./p2.txt"));
        Set <String> list1 = getPalabras(in1);
        Set <String> list2 = getPalabras(in2);
        

        for(String i: list1){
            System.out.println(i);
        }
        System.out.println();
        System.out.println("texto2");
        System.out.println();

        for(String i: list2){
            System.out.println(i);
        }

        System.out.println();
        System.out.println("Lista comun");
        System.out.println();

        Set <String> comun = getCoincidencias(list1, list2);
        for (String i : comun) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println("Coincidencias");
        for(String i: comun){
            System.out.println(i);
        }

        Reporte(list1, list2, comun);

    }
}
