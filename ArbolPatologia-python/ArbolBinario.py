# Clase Nodo
class Nodo:
    # Constructor 10
    def __init__(self, clave, valor, izquierdo=None, derecho=None, padre=None):
        self.clave = clave
        self.cargaUtil = valor
        self.hijoIzquierdo = izquierdo
        self.hijoDerecho = derecho
        self.padre = padre

    # Metodos de la clase Nodo
    # 2
    def tieneHijoIzquierdo(self): 
        return self.hijoIzquierdo
    #2
    def tieneHijoDerecho(self):
        return self.hijoDerecho
    #6
    def esHijoIzquierdo(self):
        return self.padre and self.padre.hijoIzquierdo == self
    #6
    def esHijoDerecho(self):
        return self.padre and self.padre.hijoDerecho == self

    def esRaiz(self):
        return not self.padre

    def esHoja(self):
        return not (self.hijoDerecho or self.hijoIzquierdo)

    def tieneAlgunHijo(self):
        return self.hijoDerecho or self.hijoIzquierdo

    def tieneAmbosHijos(self):
        return self.hijoDerecho and self.hijoIzquierdo

    def reemplazarDatoDeNodo(self, clave, valor, hizq, hder):
        self.clave = clave
        self.cargaUtil = valor
        self.hijoIzquierdo = hizq
        self.hijoDerecho = hder
        if self.tieneHijoIzquierdo():
            self.hijoIzquierdo.padre = self
        if self.tieneHijoDerecho():
            self.hijoDerecho.padre = self


# -----------------------fin clase Nodo---------------------------------

# Clase ArbolBinario
class ArbolBinario:

    # Construtor con peso 4
    def __init__(self):
        self.raiz = None    
        self.tamano = 0     

    #peso 1
    def __len__(self):
        return self.tamano  

    #complejidad = 18+ 2(8n+12)
    def agregar(self, clave, valor):    
        if self.raiz:#2
            self._agregar(clave, valor, self.raiz) #2(8n+12)
        else:
            self.raiz = Nodo(clave, valor)#12
        self.tamano = self.tamano + 1 #4

    #complejidad= (8n+12)2
    def _agregar(self, clave, valor, nodoActual):
        if clave < nodoActual.clave: #2
            if nodoActual.tieneHijoIzquierdo(): #4
                self._agregar(clave, valor, nodoActual.hijoIzquierdo)#n(8)
            else:
                nodoActual.hijoIzquierdo = Nodo(clave, valor, padre=nodoActual)#12
        else:
            if nodoActual.tieneHijoDerecho():#4
                self._agregar(clave, valor, nodoActual.hijoDerecho)#n(8)
            else:
                nodoActual.hijoDerecho = Nodo(clave, valor, padre=nodoActual)#12
    #complejidad = 19 + 2(8n+12)
    def __setitem__(self, c, v):
        self.agregar(c, v)#19+ 2(8n+12)

    #complejidad = 11+20n
    def obtener(self, clave):
        if self.raiz:#2
            res = self._obtener(clave, self.raiz)#4+20n
            if res:#1
                return res.cargaUtil#2
            else:
                return None #1
        else:
            return None#1

    #complejidad = 20n
    def _obtener(self, clave, nodoActual):
        if not nodoActual:#1
            return None#1
        elif nodoActual.clave == clave:#2
            return nodoActual#1
        elif clave < nodoActual.clave:#2
            return self._obtener(clave, nodoActual.hijoIzquierdo)#10n
        else:
            return self._obtener(clave, nodoActual.hijoDerecho)#10n

    def __getitem__(self, clave):
        return self.obtener(clave)#12+20n

    def __contains__(self, clave):
        if self._obtener(clave, self.raiz):
            return True
        else:
            return False

    def eliminar(self, clave):
        if self.tamano > 1:
            nodoAEliminar = self._obtener(clave, self.raiz)
            if nodoAEliminar:
                self.remover(nodoAEliminar)
                self.tamano = self.tamano - 1
            else:
                raise KeyError('Error, la clave no esta en el arbol')
        elif self.tamano == 1 and self.raiz.clave == clave:
            self.raiz = None
            self.tamano = self.tamano - 1
        else:
            raise KeyError('Error, la clave no esta en el arbol')

    def __delitem__(self, clave):
        self.eliminar(clave)

    def empalmar(self):
        if self.esHoja():
            if self.esHijoIzquierdo():
                self.padre.hijoIzquierdo = None
            else:
                self.padre.hijoDerecho = None
        elif self.tieneAlgunHijo():
            if self.tieneHijoIzquierdo():
                if self.esHijoIzquierdo():
                    self.padre.hijoIzquierdo = self.hijoIzquierdo
                else:
                    self.padre.hijoDerecho = self.hijoIzquierdo
                self.hijoIzquierdo.padre = self.padre
            else:
                if self.esHijoIzquierdo():
                    self.padre.hijoIzquierdo = self.hijoDerecho
                else:
                    self.padre.hijoDerecho = self.hijoDerecho
                self.hijoDerecho.padre = self.padre

    def encontrarSucesor(self):
        suc = None
        if self.tieneHijoDerecho():
            suc = self.hijoDerecho.encontrarMin()
        else:
            if self.padre:
                if self.esHijoIzquierdo():
                    suc = self.padre
                else:
                    self.padre.hijoDerecho = None
                    suc = self.padre.encontrarSucesor()
                    self.padre.hijoDerecho = self
        return suc

    def encontrarMin(self):
        actual = self
        while actual.tieneHijoIzquierdo():
            actual = actual.hijoIzquierdo
        return actual

    def remover(self, nodoActual):
        if nodoActual.esHoja():  # hoja
            if nodoActual == nodoActual.padre.hijoIzquierdo:
                nodoActual.padre.hijoIzquierdo = None
            else:
                nodoActual.padre.hijoDerecho = None
        elif nodoActual.tieneAmbosHijos():  # interior
            suc = nodoActual.encontrarSucesor()
            suc.empalmar()
            nodoActual.clave = suc.clave
            nodoActual.cargaUtil = suc.cargaUtil

        else:  # este nodo tiene un (1) hijo
            if nodoActual.tieneHijoIzquierdo():
                if nodoActual.esHijoIzquierdo():
                    nodoActual.hijoIzquierdo.padre = nodoActual.padre
                    nodoActual.padre.hijoIzquierdo = nodoActual.hijoIzquierdo
                elif nodoActual.esHijoDerecho():
                    nodoActual.hijoIzquierdo.padre = nodoActual.padre
                    nodoActual.padre.hijoDerecho = nodoActual.hijoIzquierdo
                else:
                    nodoActual.reemplazarDatoDeNodo(nodoActual.hijoIzquierdo.clave,
                                                    nodoActual.hijoIzquierdo.cargaUtil,
                                                    nodoActual.hijoIzquierdo.hijoIzquierdo,
                                                    nodoActual.hijoIzquierdo.hijoDerecho)
            else:
                if nodoActual.esHijoIzquierdo():
                    nodoActual.hijoDerecho.padre = nodoActual.padre
                    nodoActual.padre.hijoIzquierdo = nodoActual.hijoDerecho
                elif nodoActual.esHijoDerecho():
                    nodoActual.hijoDerecho.padre = nodoActual.padre
                    nodoActual.padre.hijoDerecho = nodoActual.hijoDerecho
                else:
                    nodoActual.reemplazarDatoDeNodo(nodoActual.hijoDerecho.clave,
                                                    nodoActual.hijoDerecho.cargaUtil,
                                                    nodoActual.hijoDerecho.hijoIzquierdo,
                                                    nodoActual.hijoDerecho.hijoDerecho)



# --------------------------Fin clase ArbolBinario----------------------------


# Clase LeerArchivo
class LeerArchivo:

    def getTratamiento(self,nombreArchivo, patologia): #t(n)=16 + 3n
        arreglo = None#2
        archivo = open(nombreArchivo, "r")#3
        for linea in archivo.readlines():#3
            arreglo = linea.split(";")#2
            if arreglo[0] == patologia:#2
                archivo.close()#1
                return arreglo[1]#2
        #3n+10
        archivo.close()#1
    # ------------Fin LeerArchivo---------------------


# Pruebas
arbol = ArbolBinario() #6
arbol["1"] = "presenta sintomas internos?"#2
arbol["11"] = "Dolor de cabeza?"#2
arbol["10"] = "Dolor articular?"#2
arbol["111"] = "Tiene tos?"#2
arbol["110"] = "Dolor en el pecho?"#2
arbol["101"] = "Inmovilidad total?"#2
arbol["100"] = "Se presentan erosiones en la piel?"#2
arbol["1111"] = "gripe"#2
arbol["1110"] = "hipertension"#2
arbol["1101"] = "dificultades al respirar?"#2
arbol["1100"] = "dolor abdominal?"#2
arbol["1011"] = "fractura"#2
arbol["1010"] = "esguince"#2
arbol["1001"] = "alergia"#2
arbol["1000"] = "gripe"#2
arbol["11011"] = "asma"#2
arbol["11010"] = "gripe"#2
arbol["11001"] = "apendicitis"#2
arbol["11000"] = "vision borrosa?"#2
arbol["110001"] = "diabetes"#2
arbol["110000"] = "examenes"#2
#complejidad agreagacion y creación = (21(2)+ 21(19 + 2(8n+12))) + 6

nombre = raw_input('Ingrese su nombre:') #3
print('Bienvenido ' + nombre) #3
print ('Para contestar (1)si (0)no')#1
tratamiento = False #2
clave = "1" #2
#acumulado = 11 + (21(2)+ 21(19 + 2(8n+12))) + 6

while(tratamiento==False): #1
    decision = raw_input(arbol[clave]) #14+20n
    if decision == "1": #1
        clave = clave+"1" #2
    if decision == "0": #1
        clave = clave + "0" #2
    if not("?" in arbol[clave]): #14+20n
        tratamiento = True #1
#7n(2(14+20n))
#acumulado = 11 + (21(2)+ 21(19 + 2(8n+12))) + 7n(2(14+20n))
        
lector = LeerArchivo() #3
print(nombre +" usted tiene "+ arbol[clave]) #4
print(lector.getTratamiento("tratamientos.txt", arbol[clave]))#18+3n + 12+20n

#total = 11 + (21(2)+ 21(19 + 2(8n+12))) + 7n(2(14+20n)) + 18+3n + 12+20n
#t(n)= 280n^2+555n+986
# ------------------------
