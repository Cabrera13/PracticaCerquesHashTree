package practica3java;

/**
 *
 * @author Josep Sànchez & Miquel Bellet
 */
public class Final {
        
    public static void main(String[] args){
        Practica3Java x = new Practica3Java(1000, "si");
        x.ordenar();
        System.out.println(x.toString());
        x.cercaBinaria();
        x.cercaLineal();
        x.cercaTernaria();
    }
}
