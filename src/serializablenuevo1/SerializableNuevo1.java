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

import java.util.*;
import java.io.*;

public class SerializableNuevo1{

    public static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Vector Cuentas = new Vector(5,5);
        int opcion1, opcion2, opcion3;
        
        //////////////////////////////////////
        ////////// L E C T U R A /////////////
        //////////////////////////////////////
        
        ObjectInputStream ois = null;
        
        try{
            File f = new File("cuentas.obj");
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
           
            Cuentas = (Vector) ois.readObject();
            ois.close();
        }
        catch(IOException io){
            System.out.println("El archivo esta vacio o no se ha podido leer");
        }
        
        do{
            opcion1 = MenuGeneral();
            switch(opcion1){
                case 1:
                    int i;
                    
                    for(i=0;i<Cuentas.size();i++){
                        
                        System.out.println("No Cuenta: "+((Cuenta)Cuentas.elementAt(i)).getID());
                        System.out.println("Nombre: "+((Cuenta)Cuentas.elementAt(i)).getNombre()     );//AQUI LA MANERA DE HACER EL CASTING PARA ACCEDER A LOS METODOS DEL OBJETO, CAZURRO
                        System.out.println("Apellidos: "+((Cuenta)Cuentas.elementAt(i)).getApellidos()     );
                        System.out.println("Saldo: "+((Cuenta)Cuentas.elementAt(i)).getSaldo());
                    }
                    break;
                case 2:
                    
                    float dinero;
                    
                    do{
                        opcion2 = MenuAdministrar(Cuentas);
                        opcion3 = MenuCuenta();
                        
                        switch(opcion3){
                            case 1:
                                System.out.println("Tu saldo actual es de: "+((Cuenta)Cuentas.elementAt(opcion3)).getSaldo()+"€");
                                break;
                            case 2:
                                System.out.println("Cuánto dinero vas a ingresar?");
                                dinero = teclado.nextFloat();
                                ((Cuenta)Cuentas.elementAt(opcion3)).Ingreso(dinero);
                                System.out.println("Dinero ingresado satisfactoriamente");
                                break;
                            case 3:
                                System.out.println("Cuánto dinero vas a sacar?");
                                dinero = teclado.nextFloat();
                                ((Cuenta)Cuentas.elementAt(opcion3)).Reintegro(dinero);
                                System.out.println("Aquí tienes tu dinero. Saldo actual: "+((Cuenta)Cuentas.elementAt(opcion3)).getSaldo()+"€");
                                break;
                            case 4:
                                int transf;
                                
                                System.out.println("Cuánto dinero quieres transferir?");
                                dinero = teclado.nextFloat();
                                ((Cuenta)Cuentas.elementAt(opcion3)).Reintegro(dinero);
                                System.out.println("A qué cuenta quieres transferirle el dinero?");
                                mostrarCuentas(Cuentas);//La perfección sería poner un do-while y que no salga hasta que ponga una diferente de la misma opcion3. Sino se lo estaría ingresando a si mismo
                                transf = teclado.nextInt();
                                ((Cuenta)Cuentas.elementAt(transf)).Ingreso(dinero);
                                System.out.println("Has ingresado "+dinero+"€ a la cuenta de "+((Cuenta)Cuentas.elementAt(transf)).getNombre()+" satisfactoriamente.");
                                break;
                            case 0:
                                System.out.println("Saliendo del menú de administración de cuenta...");
                                break;
                            default:
                                break;
                        }
                    }while(opcion3!=0);
                    break;
                case 3:
                    String nombre, apellidos;
                    
                    teclado.nextLine();
                    System.out.println("Dime el nombre del propietario de la cuenta: ");
                    nombre = teclado.nextLine();
                    System.out.println("Dime los apellidos del propietario de la cuenta: ");
                    teclado.nextLine();
                    apellidos = teclado.nextLine();
                    
                    Cuentas.addElement(new Cuenta(Cuentas.size(),nombre,apellidos));
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }while(opcion1!=0);
        
        //////////////////////////////////////
        ////////// E S C R I T U R A /////////
        //////////////////////////////////////
        
        File f = new File("cuentas.obj");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Cuentas);
    }
    
    public static int MenuGeneral(){
        
        int op;
        
        System.out.println("************** M E N U ************");
        System.out.println("1. Ver Cuentas");
        System.out.println("2. Administrar Cuenta");
        System.out.println("3. Crear Cuenta");
        System.out.println("0. Salir");
        
        op = teclado.nextInt();
        
        return op;
    }
    
    public static int MenuAdministrar(Vector Cuentas){
         
        int op, i;
        
        System.out.println("*********** M E N U **********");
        System.out.println("Selecciona la cuenta a administrar: ");
        mostrarCuentas(Cuentas);
        
        op = teclado.nextInt();
        
        return op;
    }
    
    public static int MenuCuenta(){
        
        int op;
        
        System.out.println("********** M E N U *********");
        System.out.println("0. Ver saldo");
        System.out.println("2. Ingresar");
        System.out.println("3. Sacar dinero");
        System.out.println("4. Transferir dinero");
        System.out.println("0. Salir");
        
        op = teclado.nextInt();
        
        return op;
    }
    
    public static void mostrarCuentas(Vector Cuentas){
        
        int i;
        
        for(i=0;i<Cuentas.size();i++){
            System.out.println(i+" - ID: "+((Cuenta)Cuentas.elementAt(i)).getID()+" | Nombre: "+((Cuenta)Cuentas.elementAt(i)).getNombre()+" | Apellidos: "+((Cuenta)Cuentas.elementAt(i)).getApellidos()+"\n");
        }
    }
}
