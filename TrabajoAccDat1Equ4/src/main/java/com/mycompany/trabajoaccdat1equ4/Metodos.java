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
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author pokem, patri, bacho
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
     * Versión sobrecargada del método leerCadena; permite ejecutar el mismo
     * proceso, con la salvedad de que admite un valor booleano mediante el cual
     * se indicará si se desea o no hacer una operación .trim() de la cadena
     * leída, útil a la hora de discriminar si hay que escribir utilizando todos
     * los carácteres o suprimiendo los espacios (por ejemplo, a la hora de crear
     * los archivos XML).
     * @param tamaño Cantidad de caracteres a leer (tamaño de la cadena)
     * @param fichero Objeto de la clase RandomAccessFile que realizará la lectura
     * @param cortar Valor booleano que indicará si se hace o no la operación .trim()
     * a la String obtenida
     * @return String compuesta por todos los caracteres leídos, sin espacios o
     * con ellos según se indique
     * @throws IOException 
     */
    public static String leerCadena(int tamaño, RandomAccessFile fichero, boolean cortar) throws IOException {
        StringBuffer sb = new StringBuffer(tamaño);
        for (int i=0; i<tamaño; i++) {
            sb.append(fichero.readChar());
        }
        String cadenaConEspacios = sb.toString();
        String cadena=null;
        
        if (cortar) {
            cadena = cadenaConEspacios.trim();
        } else {
            cadena = cadenaConEspacios;
        }
        
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
    
    
    
    /**
    * Método que permite reescribir el ID de un enemigo, cambiándolo a -1
    * si su elemento es más débil frente al elemento del jugador.
    * @param elementoElegido String del elemento con el que jugará el usuario
    * @param rutaFichEnemigos String que contiene la ruta del fichero de enemigos
    */ 
   public static void reescrituraID(String elementoElegido, String rutaFichEnemigos) {
    try {
        
        File f = new File(rutaFichEnemigos);
        
        RandomAccessFile fichero = new RandomAccessFile(rutaFichEnemigos, "rw");
        FileInputStream lector = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(lector);
        
        long tam = fichero.length();
        String elementoEnemigo = null;
        long registroActual = 0;
        long tamRegistro = 64; // 4 por el int de ID, 40 por los 20 caracteres 
                              // del nombre y 20 por los 10 caracteres del
                              // elemento

        while (registroActual < tam) {
            
            fichero.seek(registroActual);
            int id = fichero.readInt();
            elementoEnemigo = devolverElementoLecturaSecuencial(dis);

            if (elementoEnemigo.equalsIgnoreCase(elementoElegido)) {
                System.out.println("Mismo elemento, no se realiza acción.");
                
            } else {
                if (esElementoElegidoMasFuerte(elementoElegido, elementoEnemigo)) {
                    
                    System.out.println("El elemento elegido ("+elementoElegido+") es más fuerte que "
                            + "el elemento del enemigo ("+elementoEnemigo+").");
                    fichero.seek(registroActual);
                    fichero.writeInt(-1);
                    
                } else {
                    System.out.println("El elemento elegido ("+elementoElegido+") es más débil que "
                            + "el elemento del enemigo ("+elementoEnemigo+").");
                }
            }

            // Avanza al siguiente registro
            registroActual += tamRegistro;
        }

        fichero.close();
        dis.close();
        lector.close();

    } catch (IOException ioe) {
        System.out.println("EXCEPCIÓN POR REESCRITURA");
    }
  }

    
   
    /**
     * Método que devuelve el elemento del enemigo mediante una lectura secuencial.
     * @param dis DataInputStream desde el cual se realizará la lectura
     * @return String que contiene el elemento del enemigo
     */
    public static String devolverElementoLecturaSecuencial(DataInputStream dis) {
    try {
        int id = dis.readInt();
        
        for (int i = 0; i < 20; i++) {
            dis.readChar();
        }
        
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb2.append(dis.readChar());
        }
        return sb2.toString().trim();
    } catch (IOException IOE) {
        System.out.println("EXCEPCIÓN POR LECTURA");
    }
    return null;
}
    
    
    
    /**
     * Método que compara elementos para saber si el elemento elegido por
     * el jugador es más fuerte que el elemento del enemigo.
     * @param elementoElegido String que contiene el elemento elegido por el jugador
     * @param elementoEnemigo String que contiene el elemento del enemigo
     * @return true si el elemento elegido por el jugador es más fuerte,
     * false en caso contrario
     */
    public static boolean esElementoElegidoMasFuerte(String elementoElegido, String elementoEnemigo) {
        boolean fuerte=false;
        
        if ((elementoElegido.equalsIgnoreCase("agua") && elementoEnemigo.equalsIgnoreCase("fuego")) ||
           (elementoElegido.equalsIgnoreCase("tierra") && elementoEnemigo.equalsIgnoreCase("agua")) ||
           (elementoElegido.equalsIgnoreCase("fuego") && elementoEnemigo.equalsIgnoreCase("tierra"))) {
            
            fuerte=true;
        }
        
        return fuerte;
        
        
    }
    
    
    
    /**
     * Método que permite inicializar la creación de un archivo XML utilizando
     * DOM, recibiendo como parámetro el nombre que tendrá el documento
     * XML creado. (No confundir con el nombre del archivo)
     * 
     * QUE NI SE OS OCURRA QUE EL DOCUMENTO XML CREADO TENGA ESPACIOS
     * EN EL NOMBRE, PEGA UNA EXCEPCIÓN QUE TE CAGAS
     * 
     * @param nombre Nombre que se dará al documento XML creado
     * @return El documento XML creado, para poder manejarlo y añadirle
     * nodos principales, expandiendo así el XML.
     */
    public static Document inicializar(String nombre) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document raiz = implementation.createDocument(null, nombre, null);
        raiz.setXmlVersion("1.0");
        return raiz;
    }
    
    
    
    /**
     * Método que permite crear un nodo principal en un documento XML que
     * se pase como parámetro.
     * @param nombreNodoPrincipal String que contiene el nombre que se 
     * le dará al nodo principal creado.
     * @param documento Documento XML del que colgará el nodo principal
     * creado.
     * @return El nodo principal creado, permitiéndonos así crearle más
     * hijos para poder expandirlo.
     */
    public static Element añadirNodoPrincipal(String nombreNodoPrincipal, Document documento) {
        Element nodoPrincipal = documento.createElement(nombreNodoPrincipal);
        documento.getDocumentElement().appendChild(nodoPrincipal);
        return nodoPrincipal;
    }
    
    
    
    /**
     * Método que permite, en un documento XML concreto, crear nodos hijos que 
     * contengan únicamente texto y colgarlos de un elemento raíz ("padre").
     * @param nombreNodo String que contiene el nombre que se dará al nodo hijo creado
     * @param textoNodo String que contiene el texto que irá dentro del nodo hijo creado
     * @param documento Documento en el que se crearán el nodo hijo y el texto indicados
     * @param raiz Elemento padre del que se colgará el nodo hijo que creemos en el método
     */
    public static void crearNodo(String nombreNodo, String textoNodo, Document documento, Element raiz) {
        Element nodoHijo = documento.createElement(nombreNodo);
        Text texto = documento.createTextNode(textoNodo);
        
        nodoHijo.appendChild(texto);
        raiz.appendChild(nodoHijo);
    }
    
    
    
    /**
     * Método que permite generar un archivo XML tomando como parámetro de
     * entrada un objeto Document (documento XML) que haya creado en memoria,
     * y como salida, un archivo cuyo nombre indicaremos en una String pasada
     * por parámetro.
     * @param documento Documento XML que se utilizará como fuente para tomar
     * los datos del archivo final generado
     * @param nombreArchivo String que contiene el nombre que se dará al archivo
     * final generado
     */
    public static void generarXml(Document documento, String nombreArchivo) throws TransformerException {
        Source source = new DOMSource(documento);
        Result salida = new StreamResult(new File(nombreArchivo));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, salida);
    }
    
    
    
    /**
     * Método "grande" que emplea métodos anteriores (versión sobrecargada de
     * leerCadena, inicializar, generarXml) para convertir en archivo XML un
     * fichero con un grupo de enemigos que se le pase por parámetro.
     * 
     * El parámetro nombreDocumento, al usarse en el método inicializar, no
     * debe contener espacios (barras bajas son válidas); la ruta del archivo
     * a convertir en XML puede ponerse como ruta relativa, y el parámetro
     * nombreXml debe, por cohesión, terminar con el formato .xml
     * 
     * @param nombreDocumento String que contiene el nombre del documento XML
     * que se va a crear.
     * @param rutaArchivo String que contiene la ruta del archivo que vamos a
     * convertir en XML.
     * @param nombreXml String que contiene el nombre que se dará al archivo
     * XML final.
     */
    public static void convertirGrupoAXml(String rutaArchivo, String nombreXml) {
        
        Document documento=null;
        
        try {
            documento = Metodos.inicializar("Grupo_de_enemigos");
            
            long tamaño = 64;
            long registroActual = 0;
            int id;
            Element nodo;
            RandomAccessFile fichero = new RandomAccessFile(rutaArchivo, "rw");
            
            while (registroActual < fichero.length()) {
                
                fichero.seek(registroActual);
                id = fichero.readInt();
                
                if (id != -1) {
                    fichero.seek(registroActual);
                    
                    nodo = Metodos.añadirNodoPrincipal("Enemigo", documento);
                    Metodos.crearNodo("id", String.valueOf(fichero.readInt()), documento, nodo);
                    Metodos.crearNodo("nombre", Metodos.leerCadena(20, fichero, true), documento, nodo);
                    Metodos.crearNodo("elemento", Metodos.leerCadena(10, fichero, true), documento, nodo);
                    
                }
                
                registroActual = registroActual + tamaño;
            }
            fichero.close();
            
        } catch (IOException ioe) {
            System.out.println("EXCEPCIÓN CREANDO EL XML");
        } catch (ParserConfigurationException pce) {
            System.out.println(pce);
        }
        
        
        try {
            Metodos.generarXml(documento, nombreXml);
        } catch (TransformerException te) {
            System.out.println(te);
        }
    }
    
    /**
     * Método que permite la creación de un fichero HTML a partir de una hoja
     * de estilos XSL y un fichero fuente XML
     * @param hojaEstilo
     * @param fichXML
     * @throws IOException 
     */
    public static void crearHTML(String hojaEstilo, String fichXML) throws IOException {
        File pagHTML = new File("pagina.html");
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo);
        Source datos = new StreamSource (fichXML);
        Result result = new StreamResult(os);
        
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result);
        } catch (Exception e) {
            System.out.println(e);
        }
        os.close();
       
    }
}
