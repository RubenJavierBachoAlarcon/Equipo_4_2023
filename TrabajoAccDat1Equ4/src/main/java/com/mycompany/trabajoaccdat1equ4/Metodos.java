/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoaccdat1equ4;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

/**
 *
 * @author pokem
 */
public class Metodos {
    
    /**
     * Método que crea un archivo que contiene todo el bestiario de enemigos
     * que se empleará en operaciones posteriores.
     * @param nombreArchivo String con el nombre que se le dará al archivo.
     */
    public static void crearBestiario(String nombreArchivo) {
        try {
            RandomAccessFile fichero = new RandomAccessFile(nombreArchivo, "rw");
            Metodos.escribeDatos(1, "Pyrobabosa", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(2, "Geobabosa", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(3, "Hydrobabosa", 20, "agua", 10, fichero);
            Metodos.escribeDatos(4, "Pyrodragarto", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(5, "Geodragarto", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(6, "Hydrodragarto", 20, "agua", 10, fichero);
            Metodos.escribeDatos(7, "Pyroesqueleto", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(8, "Geoesqueleto", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(9, "Hydroesqueleto", 20, "agua", 10, fichero);
            Metodos.escribeDatos(10, "Pyrocaballero", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(11, "Geocaballero", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(12, "Hydrocaballero", 20, "agua", 10, fichero);
            Metodos.escribeDatos(13, "Pyrocapitán", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(14, "Geocapitán", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(15, "Hydrocapitán", 20, "agua", 10, fichero);
            Metodos.escribeDatos(16, "Pyrodiablillo", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(17, "Geodiablillo", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(18, "Hydrodiablillo", 20, "agua", 10, fichero);
            Metodos.escribeDatos(19, "Pyrobestia", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(20, "Geobestia", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(21, "Hydrobestia", 20, "agua", 10, fichero);
            Metodos.escribeDatos(22, "Pyrodemonio", 20, "fuego", 10, fichero);
            Metodos.escribeDatos(23, "Geodemonio", 20, "tierra", 10, fichero);
            Metodos.escribeDatos(24, "Hydrodemonio", 20, "agua", 10, fichero);
            Metodos.escribeDatos(25, "Wyvern demente", 20, "fuego", 10, fichero);
            fichero.close();
            
        } catch (IOException ioe) {
            System.out.println("Excepción de tipo IOE.");
        }
    }
    
    
    
    /**
     * Método que escribe en un fichero de datos binario, empleando la clase
     * RandomAccessFile, un registro con los datos de un enemigo que se le hayan
     * pasado por parámetro.
     * @param identificador Número de ID que tendrá el enemigo en la lista
     * @param nombreEnemigo Nombre del enemigo
     * @param tamañoNombre Tamaño que tendrá la String con el nombre del enemigo (20 por uniformidad)
     * @param elementoEnemigo Elemento del enemigo
     * @param tamañoElemento Tamaño que tendrá la String con el elemento del enemigo (10 por uniformidad)
     * @param fichero Objeto de la clase RandomAccessFile que hará la escritura de los datos
     * @throws IOException 
     */
    public static void escribeDatos(int identificador, String nombreEnemigo, int tamañoNombre,
            String elementoEnemigo, int tamañoElemento, RandomAccessFile fichero) throws IOException {
        
        StringBuffer sbNombre = new StringBuffer(nombreEnemigo);
        sbNombre.setLength(tamañoNombre);
        
        StringBuffer sbElemento = new StringBuffer(elementoEnemigo);
        sbElemento.setLength(tamañoElemento);
        
        fichero.writeInt(identificador);
        fichero.writeChars(sbNombre.toString());
        fichero.writeChars(sbElemento.toString());
    }
    
    
    
    /**
     * Método que permite hacer una lectura secuencial completa, empleando
     * las clases FileInputStream y DataInputStream, de un fichero dedicado
     * a almacenar datos binarios de enemigos (por las dimensiones de las 
     * cadenas de caracteres que lee el algoritmo interno).
     * @param nombreFichero String con la ruta del fichero de enemigos a leer
     */
    public static void leerFicheroEnemigosSecuencial(String nombreFichero) {
        File archivo = new File(nombreFichero);
        try {
            FileInputStream lector = new FileInputStream(archivo);
            DataInputStream dis = new DataInputStream(lector);
            
            while (dis.available() > 0) {
                System.out.println(dis.readInt());
                
                StringBuffer sb1 = new StringBuffer();
                for (int i=0; i<20; i++) {
                    sb1.append(dis.readChar());
                }
                System.out.println(sb1.toString().trim());
                
                StringBuffer sb2 = new StringBuffer();
                for (int i=0; i<10; i++) {
                    sb2.append(dis.readChar());
                }
                System.out.println(sb2.toString().trim());
            }
            dis.close();
            lector.close();
            
        } catch (IOException IOE) {
            System.out.println("Excepción de tipo IOE");
        }
    }
    
    
    
    /**
     * Método que permite leer una cadena de caracteres del tamaño indicado
     * y devolverla en forma de una String, incluyendo espacios.
     * @param tamaño Cantidad de caracteres a leer (tamaño de la cadena)
     * @param fichero Objeto de la clase RandomAccessFile que realizará la lectura
     * @return String compuesta por todos los caracteres leídos, con espacios
     * @throws IOException 
     */
    public static String leerCadena(int tamaño, RandomAccessFile fichero) throws IOException {
        StringBuffer sb = new StringBuffer(tamaño);
        for (int i=0; i<tamaño; i++) {
            sb.append(fichero.readChar());
        }
        String cadena = sb.toString();
        return cadena;
    }
    
    
    
    /**
     * Método que permite hacer un borrado recursivo (incluyendo sub-directorios
     * y contenidos de los mismos) de una ruta (directorio) que le indiquemos
     * por parámetro.
     * Útil para borrarlo absolutamente todo y volver a generarlo siempre que
     * el usuario le dé a jugar al juego.
     * @param ruta String con la ruta del directorio a borrar.
     */
    public static void borradoRecursivo(String ruta) {
        File archivo = new File (ruta);
        File[] lista = archivo.listFiles();
        
        for (File e:lista) { //Si en un condicional tengo la misma instrucción en ambas, 
                            //hay papeletas para que se pueda poner fuera
                            //Si no quiero ver mensajes, puedo simplemente borrar, ponerlo fuera.
                            //Si lo pongo fuera, pierde sentido el que haya un condicional doble; con un simple if vale.
            if (e.isFile()) {
                e.delete();
                //System.out.println("Fichero borrado.");
            } else if (e.isDirectory()) {
                borradoRecursivo(e.getPath());
                e.delete();
                //System.out.println("Directorio borrado.");
            }
            
            // if (e.isDirectory()) {
            //      borrar(e.getPath());
            // }
            // e.delete();
            //
        }
        archivo.delete();
        //System.out.println("Se ha eliminado también el directorio raíz indicado.");
    }
    
    
    
    /**
     * Método que permite crear un directorio en la ruta (incluyendo nombre
     * del directorio) que le pasemos por parámetro; se utilizará para crear
     * las zonas en las que se ubicarán los grupos de enemigos.
     * 
     * Para evitar posibles conflictos entre partidas distintas, antes de
     * crear el directorio se comprueba que ya exista, y si se da el caso, se
     * hace un borrado recursivo del mismo.
     * @param rutaZona String que contiene la ruta del directorio a crear
     */
    public static void crearZona(String rutaZona) {
        File zona = new File(rutaZona);
        if (zona.exists()) {
            Metodos.borradoRecursivo(zona.getPath());
        }
        zona.mkdir();
    }
    
    
    
    /**
     * Método que permite obtener un número entero aleatorio entre un límite
     * superior y un límite inferior que se introduzcan por parámetro.
     * @param limSuperior Límite superior para el entero aleatorio obtenible
     * @param limInferior Límite inferior para el entero aleatorio obtenible
     * @return Número entero aleatorio entre unos límites superior e inferior
     */
    public static int aleatorio(int limSuperior, int limInferior) {
        Random aleatorio = new Random();
        int entero = aleatorio.nextInt(limSuperior - limInferior + 1) + limInferior;
        return entero;
    }
    
    
    
    /**
     * Método que permite leer registros de enemigos aleatorios de un bestiario
     * (archivo binario) de 25 monstruos (o menos) cuyo nombre en formato String 
     * se pase por parámetro y escribir dichos registros en otros ficheros 
     * binarios, creando grupos aleatorios de enemigos con el tamaño indicado.
     * @param nombreGrupo String con el nombre que se le dará al grupo de enemigos 
     * (archivo binario) creado.
     * @param tamañoRegistro Tamaño de los registros que leerá el objeto "bestiario"
     * de la clase RandomAccessFile empleado en el algoritmo.
     * @param nombreBestiario String con el nombre que tiene el archivo bestiario
     * (de tamaño 25 monstruos o menos) del que se leerán los registros.
     * @param tamañoGrupo Número entero que delimita el tamaño del grupo de
     * enemigos que se creará.
     */
    public static void creaGrupo(String nombreGrupo, long tamañoRegistro, String nombreBestiario, int tamañoGrupo) {
        long T = tamañoRegistro;
        long numAparecido;
        
        try {
            RandomAccessFile bestiario = new RandomAccessFile(nombreBestiario, "rw");
            RandomAccessFile grupoEnemigos = new RandomAccessFile(nombreGrupo, "rw");
            for (int i=1; i<=tamañoGrupo; i++) {
                grupoEnemigos.writeInt(i);
                
                numAparecido = Metodos.aleatorio(25, 1);
                bestiario.seek((numAparecido-1)*T);
                bestiario.skipBytes(4);
                
                grupoEnemigos.writeChars(Metodos.leerCadena(20, bestiario));
                grupoEnemigos.writeChars(Metodos.leerCadena(10, bestiario));   
            }
            grupoEnemigos.close();
            bestiario.close();
            
        } catch (IOException ioe) {
            System.out.println("Excepción del tipo IOE");
        }
    }
    
    
    
    /**
     * Método que permite mover los archivos de un directorio a otro, empleando
     * como parámetros de entrada una String que hará referencia a la ruta de
     * origen del archivo a mover y otra String que hará referencia tanto a la 
     * ruta de destino en la que se ubicará el archivo como a la variable que 
     * controlará al archivo ubicado en la misma.
     * @param stringRutaOrigen String que contiene la ruta de origen del archivo
     * @param stringRutaDestino Strin que contiene la ruta del archivo, y
     * a la vez, String que se usará para hacer referencia al objeteo File ubicado
     * en dicha ruta
     */
    public static void moverGrupo(String stringRutaOrigen, String stringRutaDestino) {
        try {
            String rutaOrigen = new String(stringRutaOrigen);
            File archivoDestino = new File(stringRutaDestino);
            String rutaDestino = new String(stringRutaDestino);
        
            Path fuente = (Path) Paths.get(rutaOrigen);
            Path nuevoDir = (Path) Paths.get(rutaDestino);
            Files.move(fuente, nuevoDir, StandardCopyOption.REPLACE_EXISTING);
        
        } catch (IOException ioe) {
            System.out.println("Excepción de tipo IOE.");
        }
    }
}
