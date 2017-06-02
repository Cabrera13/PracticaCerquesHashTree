package practica3java;

import java.util.Random;

/**
 *
 * @author Josep Sànchez & Miquel Bellet
 */

public class Practica3Java {
    public static int posicioTaula;
    public static int numeroTaula;
    public static int size;
    public static int accessos = 0;
    public static int r;
    public static int [] array;
    HeapSort arrayOrd;
    
    public Practica3Java (int size, String grumolls) {
        this.size=size;
        array = new int[size];
        arrayOrd = new HeapSort((size+1));

        if (grumolls == "no") {
            for (int i = 0; i < size; i++) {
                numeroTaula = randInt(0,size*100);
                array[i] = numeroTaula;
            }
        }
        else if (grumolls == "si"){
            int posicioGrumolls = size/10;
            for (int i = 0; i < size; i++) {
                numeroTaula = randInt(0,size*100);
                if (i < posicioGrumolls) {
                    array [i] = i ;
                }
                else {
                    array[i] = numeroTaula;
                }

            }

        }
        
    }
    
    public void ordenar () {
        for (int i = 0; i < array.length; i++) {
            arrayOrd.Insert(array[i]);
        }
        
        int i=0;
        while(!arrayOrd.Empty()){
            array[i] = (int) arrayOrd.Lesser();
            arrayOrd.Delete();
            i++;
        }
    }
    
    public static int randInt(int min, int max) {
        Random rand = new Random ();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public static int BS(int v){
        int a=0;        // begin of subtable
        int b=size-1;      // end of subtable
        int m=size/2;      // middle of subtable
        r=1;            // access count
        while(a<b && array[m]!=v){
            r++;
            //System.out.println(a+" "+m+" "+b);
            if(array[m]>v)
                b=m-1;  // left subtable
            else
                a=m+1;  // right subtable
            m=(a+b)/2;  // new middle
        }
        if(array[m]==v)
            return m;
        else
            return -1;        
    }
    
    public static int TS (int v) {
        int a=0;
        int b = size-1; //end
        int m = size /3; // 1 part of 3
        int n = (size-m) /2;
        
        r=1;
        while (a<b && array[m] != v) {
            r++;
            if(array[m]>v) // primer terç de l'array
                b=m-1;  // canviar l'últim número perque acabi en la divisió de l'array en la part que pertoca
            else {
                a=m+1;  // incrementar el contador
            }    
            if(array[n]<v) // tercer terç de l'array
                b=m-1;  // ""
            else{
                a=m+1;  // ""
            }
            if(array[m]>v && array[n]<v) // segon terç de l'array
                b=m-1;  // ""
            else{
                a=m+1;  // ""
            }
            m=(a+b)/3;
        }
        
        if(array[m]==v)
            return m;
        else
            return -1; 
    }
    
     public static int LI(int v){
        r=1;
        int a=0;
        int b=size-1;
        int ka=array[a];
        int kb=array[b];
        int d=b-a;
        int m=-1;
        boolean found=false;
        while(a<=b && v>=ka && v<=kb && !found){
            m=(int)(d*(double)(v-ka)/(double)(kb-ka))+a;
            if(array[m]==v)
                found=true;
            else{
                r++;
                if(v<array[m]){
                    b=m-1;
                    kb=array[b];
                }
                else{
                    a=m+1;
                    ka=array[a];
                }
                d=b-a;
            }
        }    
        if(found)
            return m;
        else
            return -1;   
    }
    
    
    public void cercaBinaria() {
        for(int i=0;i<array.length;i++){
            accessos+=r;
            int num = BS(array[i]);
            int nummes = BS(array[i]+1);
            System.out.println("Cerca binaria: Per el nombre: " + array[i] +" s'ha tardat " + r + " vegades.");
        }
        accessos /= size;
        
        System.out.println("Nombre d'accessos en cerca Binaria: " + accessos);
        accessos = 0;
     
    }
    
    public void cercaLineal() {
        for(int i=0;i<array.length;i++){
            accessos+=r;
            int num = LI(array[i]);
            int nummes = LI(array[i]+1);
            System.out.println("Cerca Lineal: Per el nombre: " + array[i] +" s'ha tardat " + r + " vegades.");
        }
        accessos = accessos / size;
        System.out.println("Nombre d'accessos en cerca Lineal: " + accessos);
        accessos = 0;
    }
    
    public void cercaTernaria() {
        for(int i=0;i<array.length;i++){
            accessos+=r;
            int num = TS(array[i]);
            int nummes = TS(array[i]+1);
            System.out.println("Cerca ternària: Per el nombre: " + array[i] +" s'ha tardat " + r + " vegades.");
        }
        accessos = accessos / size;
        System.out.println("Nombre d'accessos en cerca Ternaria: " + accessos);
        accessos = 0;
    }
    
    @Override
    public String toString () {
        String z = "";
        for (int x = 0; x < 20;x++){
            z += array[x]+",";
        }
        for (int x = array.length-20; x < array.length;x++){
            z += array[x]+",";
        }
        return z;
    }
}
