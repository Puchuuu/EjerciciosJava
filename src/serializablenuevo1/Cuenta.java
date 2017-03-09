/*
1. Crea una clase Cuenta con los métodos ingreso, reintegro, transferencia 
(que transferira dinero de una cuenta a otra)  y consultaSaldo. La clase contendrá 
un constructor por defecto, un constructor con parámetros y los métodos getters y setters. 
Crea un menú que permita crear cuentas nuevas. Las cuentas se identificaran por un número 
(la primera un 1, la segunda un 2, etc). El programa guardarà todos los objetos cuando se 
seleccione la opción salir del menú. Cuando se ejecute el programa cargarà todos los objetos 
guardados para volver al estado en el que se dejò el programa. Una opción del menú serà mostrar 
todos las cuentas actuales con su saldo.
 */
package serializablenuevo1;

import java.io.*;

public class Cuenta implements Serializable{
    
    protected int id;
    protected String nombrePropietario;
    protected String apellidosPropietario;
    protected float saldo;
    
    
    public Cuenta(){
    }
    public Cuenta(int num, String nombre, String apellidos){
        
        id = num;
        nombrePropietario = nombre;
        apellidosPropietario = apellidos;
    }
    
    public void setID(int num){
        
        id = num;
    }
    public void setNombre(String n){
        
        nombrePropietario = n;
    }
    public void setApellidos(String a){
        
        apellidosPropietario = a;
    }
    public void setSaldo(float d){
        
        saldo = d;
    }
    
    public int getID(){
        
        return id;
    }
    public String getNombre(){
        
        return nombrePropietario;
    }
    public String getApellidos(){
        
        return apellidosPropietario;
    }
    public float getSaldo(){
        
        return saldo;
    }
    
    public void Ingreso(float dinero){
        
        saldo = saldo+dinero;
    }
    public void Reintegro(float dinero){//Aprovechar para la funcion TRANSFERENCIA
        
        saldo = saldo-dinero;
    }
    public void consultaSaldo(){
        
        System.out.println("Tu saldo actual es de: "+saldo+" €");
    }
}
