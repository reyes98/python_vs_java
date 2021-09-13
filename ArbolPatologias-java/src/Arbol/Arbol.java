/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

/**
 *
 * @author steve
 */
public class Arbol {

    private Nodo raiz;
    private int size;

    //Constructores
    public Arbol() {
        this.raiz = null;
        this.size = 0; 
    }

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
        this.size = 1;
    }
    //--------------------------

    //Retorna la cantidad de elementos de el arbol
    public int getSize() {
        return size;
    }
    //--------------------------
    
    //Retorna la raiz
    public Nodo getRaiz() {
        return raiz;
    }
    //-------------------    

    //verdadero si no hay elementos en el arbol
    public boolean esVacio() {
        return (raiz == null);
    }
    
    //Retorna el nivel de un elemento de un Nodo
    private int getNivelAux(int clave, Nodo temp, int nivel) {
        if (temp != null || temp.esHoja()) {
            return 0;
        } else {
            if (temp.getHijoIzquierdo().getClave() == clave || temp.getHijoDerecho().getClave() == clave) {
                return nivel;
            } else{
                nivel++;
                if (temp.getClave() < clave) {
                    return getNivelAux(clave, temp.getHijoIzquierdo(), nivel);
                } else {
                    return getNivelAux(clave, temp.getHijoDerecho(), nivel);
                }
            }
        }
    }
    public int getNivel(int clave) {
        if (clave == this.raiz.getClave()) {
            return 0;
        } else {
            Nodo temp = raiz;
            return getNivelAux(clave, temp, 0) + 1;
        }
    }
    //------------------------------------------------

    //Adiciona un Nodo nuevo
    public void addElemento(Nodo nuevo) {                          
        boolean insertado = false;                              
        Nodo aux = raiz;                                        
        if (esVacio()) {
            raiz = nuevo;                                       
        } else {
            while (!insertado) {                                    
                if (nuevo.getClave() == aux.getClave()) {                   
                    aux.setHijoIzquierdo(nuevo);                
                    insertado = true;                           
                } else {
                    if (nuevo.getClave() > aux.getClave()) {                
                        if (aux.getHijoDerecho() == null) {     
                            aux.setHijoDerecho(nuevo);          
                            insertado = true;                   
                        } else {
                            aux = aux.getHijoDerecho();         
                        }
                    } else {
                        if (aux.getHijoIzquierdo() == null) {   
                            aux.setHijoIzquierdo(nuevo);        
                            insertado = true;                   
                        } else {
                            aux = aux.getHijoIzquierdo();       
                        }
                    }
                }
            }
        }
    }

    //Busca un nodo por la clave
    public Nodo buscar(int clave) {
        Nodo aux = raiz;                       
        Nodo salida = null;                     
        if (!esVacio()) {                           
            return salida = buscarAux(clave, raiz);  
        }
        return salida;                          
    }
    
    private Nodo buscarAux(int clave, Nodo ra) {                      
        Nodo salida = null;   
        if (ra != null) {
            if (clave == ra.getClave()) {       
                salida = ra;  
                return salida;                  
            } else if (clave < ra.getClave()) {        
                return buscarAux(clave, ra.getHijoIzquierdo()); 
            } else {
                return buscarAux(clave, ra.getHijoDerecho());  
            }
        }
        return salida;
    }   
    //--------------------------
    
    //Retorna si existe la clave
    public boolean existe(int clave) {
        if (esVacio()) {
            return false;
        } else {
            return existeAux(raiz, clave);
        }
    }

    private boolean existeAux(Nodo aux, int clave) {
        if (clave == aux.getClave()) {
            return true;
        } else {
            if (clave > aux.getClave() && aux.getHijoDerecho() != null) {
                return existeAux(aux.getHijoDerecho(), clave);
            } else {
                if (clave > aux.getClave() && aux.getHijoIzquierdo() != null) {
                    return existeAux(aux.getHijoIzquierdo(), clave);
                } else {
                    return false;
                }
            }
        }
    }
    //---------------------------------
    
     //Determina la altura del arbol
    public int getAltura(){
        if(esVacio()){
            return 0;
        }else{
            return getAlturaAux(raiz);
        }
    }
    //------------------------------

    //Determina la altura de cierto elemento
    private int getAlturaAux(Nodo aux){
        int ad=0;
        int iz=0;
        if(aux == null){
            return 0;
        }else{
            ad=getAlturaAux(aux.getHijoDerecho())+1;
            iz=getAlturaAux(aux.getHijoIzquierdo())+1;

            if(ad <= iz){
                return iz;
            }else{
                return ad;
            }
        }
    }
    
    public int getAlturaNodo(int clave){
        Nodo nodo = buscar(clave);
        return getAlturaAux(nodo);
    }
    //---------------------------------------
    
    
}
