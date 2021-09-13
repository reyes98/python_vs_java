package Arbol;

/**
 *
 * @author Reyes
 */
public class Nodo {
    //Atributos
    private int clave;
    private Object dato;
    private Nodo hijoIzquierdo;
    private Nodo hijoDerecho;
    //Constructor Vacio
    public Nodo(){
        clave=0;
        dato=null;
        hijoIzquierdo= null;
        hijoDerecho=null;
    }
    //Constructor lleno

    public Nodo(int clave, Object dato, Nodo hijoIzquierdo, Nodo hijoDerecho) {
        this.clave = clave;
        this.dato = dato;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    //Constructor simple
    public Nodo(int clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        hijoIzquierdo= null;
        hijoDerecho=null;
    }
    //GETSET

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    //Determina si el Nodo es una hoja
    public boolean esHoja(){
        return (hijoDerecho == null && hijoIzquierdo == null);
    }
    //-------------------------
    
    //Método que retorna el grado del nodo
    public int getGrado(){
        int izqu=0;
        int dere=0;
           if(this.hijoIzquierdo!=null){
               izqu=1;
           }
           if(this.hijoDerecho!=null){
               dere=1;
           }
        return izqu+dere;
    }
    //------------------------------------------
    
    
    
}
