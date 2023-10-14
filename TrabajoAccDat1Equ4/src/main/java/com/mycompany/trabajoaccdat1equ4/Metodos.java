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
            
        } catch (IOException IOE) {
            System.out.println("Excepción de tipo IOE");
        }
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
}
