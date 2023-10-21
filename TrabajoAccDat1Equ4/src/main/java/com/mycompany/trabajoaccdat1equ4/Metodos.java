/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoaccdat1equ4;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.StringReader;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 *
 * @author pokem, patri, bacho
 */
public class Metodos {

    public static boolean waitingNextGroup;
    public static ArrayList<Integer> idGrupo = new ArrayList<>();
    public static ArrayList<String> nombreGrupo = new ArrayList<>();
    public static ArrayList<String> tipoGrupo = new ArrayList<>();
    public static File zonaActual;

    /**
     * Método que crea un archivo que contiene todo el bestiario de enemigos que
     * se empleará en operaciones posteriores.
     *
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

    public static File elegirArchivo() {
        try {
            // Establecer el Look and Feel de Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Ruta incorrecta");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./Zona personalizada"));
        File selectedFile = null;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        return selectedFile;
    }

    public static File elegirArchivo(String extension) {
        try {
            // Establecer el Look and Feel de Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Ruta incorrecta");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        // Crear un filtro de extensión para el tipo de archivo deseado
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos " + extension, extension);
        fileChooser.setFileFilter(filter);

        File selectedFile = null;
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        return selectedFile;
    }

    public static File elegirDirectorioZona() {
        try {
            // Establecer el Look and Feel de Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Ruta incorrecta");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./Zona de juego"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Crear un filtro personalizado para mostrar solo directorios que empiezan por "Zona"
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() && file.getName().startsWith("Zona");
            }

            @Override
            public String getDescription() {
                return "Directorios que empiezan por 'Zona'";
            }
        });

        File selectedDirectory = null;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = fileChooser.getSelectedFile();
            System.out.println("Directorio seleccionado: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún directorio.");
        }
        zonaActual = selectedDirectory;
        return selectedDirectory;
    }

    public static File elegirDirectorio() {
        try {
            // Establecer el Look and Feel de Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Ruta incorrecta");
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Crear un filtro personalizado para mostrar solo directorios que empiezan por "Zona"
        File selectedDirectory = null;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = fileChooser.getSelectedFile();
            System.out.println("Directorio seleccionado: " + selectedDirectory.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún directorio.");
        }
        return selectedDirectory;
    }

    /**
     * Método que escribe en un fichero de datos binario, empleando la clase
     * RandomAccessFile, un registro con los datos de un enemigo que se le hayan
     * pasado por parámetro.
     *
     * @param identificador Número de ID que tendrá el enemigo en la lista
     * @param nombreEnemigo Nombre del enemigo
     * @param tamañoNombre Tamaño que tendrá la String con el nombre del enemigo
     * (20 por uniformidad)
     * @param elementoEnemigo Elemento del enemigo
     * @param tamañoElemento Tamaño que tendrá la String con el elemento del
     * enemigo (10 por uniformidad)
     * @param fichero Objeto de la clase RandomAccessFile que hará la escritura
     * de los datos
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
     * Método que permite hacer una lectura secuencial completa, empleando las
     * clases FileInputStream y DataInputStream, de un fichero dedicado a
     * almacenar datos binarios de enemigos (por las dimensiones de las cadenas
     * de caracteres que lee el algoritmo interno).
     *
     * @param nombreFichero String con la ruta del fichero de enemigos a leer
     */
    public static void leerFicheroEnemigosSecuencial(String nombreFichero) {
        idGrupo = new ArrayList<>();
        nombreGrupo = new ArrayList<>();
        tipoGrupo = new ArrayList<>();

        File archivo = new File(nombreFichero);
        try {
            FileInputStream lector = new FileInputStream(archivo);
            DataInputStream dis = new DataInputStream(lector);

            while (dis.available() > 0) {
                int id = dis.readInt();
                idGrupo.add(id);

                StringBuffer sb1 = new StringBuffer();
                for (int i = 0; i < 20; i++) {
                    sb1.append(dis.readChar());
                }
                String nombre = sb1.toString().trim();
                nombreGrupo.add(nombre);

                StringBuffer sb2 = new StringBuffer();
                for (int i = 0; i < 10; i++) {
                    sb2.append(dis.readChar());
                }
                String tipo = sb2.toString().trim();
                tipoGrupo.add(tipo);
            }
            dis.close();
            lector.close();

        } catch (IOException IOE) {
            System.out.println("Excepción de tipo IOE");
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
        for (String str : tipoGrupo) {
            System.out.println(str);
        }
    }

    /**
     * Método que permite leer una cadena de caracteres del tamaño indicado y
     * devolverla en forma de una String, incluyendo espacios.
     *
     * @param tamaño Cantidad de caracteres a leer (tamaño de la cadena)
     * @param fichero Objeto de la clase RandomAccessFile que realizará la
     * lectura
     * @return String compuesta por todos los caracteres leídos, con espacios
     * @throws IOException
     */
    public static String leerCadena(int tamaño, RandomAccessFile fichero) throws IOException {
        StringBuffer sb = new StringBuffer(tamaño);
        for (int i = 0; i < tamaño; i++) {
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
     * los carácteres o suprimiendo los espacios (por ejemplo, a la hora de
     * crear los archivos XML).
     *
     * @param tamaño Cantidad de caracteres a leer (tamaño de la cadena)
     * @param fichero Objeto de la clase RandomAccessFile que realizará la
     * lectura
     * @param cortar Valor booleano que indicará si se hace o no la operación
     * .trim() a la String obtenida
     * @return String compuesta por todos los caracteres leídos, sin espacios o
     * con ellos según se indique
     * @throws IOException
     */
    public static String leerCadena(int tamaño, RandomAccessFile fichero, boolean cortar) throws IOException {
        StringBuffer sb = new StringBuffer(tamaño);
        for (int i = 0; i < tamaño; i++) {
            sb.append(fichero.readChar());
        }
        String cadenaConEspacios = sb.toString();
        String cadena = null;

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
     * por parámetro. Útil para borrarlo absolutamente todo y volver a generarlo
     * siempre que el usuario le dé a jugar al juego.
     *
     * @param ruta String con la ruta del directorio a borrar.
     */
    public static void borradoRecursivo(String ruta) {
        File archivo = new File(ruta);
        File[] lista = archivo.listFiles();

        for (File e : lista) { //Si en un condicional tengo la misma instrucción en ambas, 
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
     * Método que permite crear un directorio en la ruta (incluyendo nombre del
     * directorio) que le pasemos por parámetro; se utilizará para crear las
     * zonas en las que se ubicarán los grupos de enemigos.
     *
     * Para evitar posibles conflictos entre partidas distintas, antes de crear
     * el directorio se comprueba que ya exista, y si se da el caso, se hace un
     * borrado recursivo del mismo.
     *
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
     *
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
     *
     * @param nombreGrupo String con el nombre que se le dará al grupo de
     * enemigos (archivo binario) creado.
     * @param tamañoRegistro Tamaño de los registros que leerá el objeto
     * "bestiario" de la clase RandomAccessFile empleado en el algoritmo.
     * @param nombreBestiario String con el nombre que tiene el archivo
     * bestiario (de tamaño 25 monstruos o menos) del que se leerán los
     * registros.
     * @param tamañoGrupo Número entero que delimita el tamaño del grupo de
     * enemigos que se creará.
     */
    public static void creaGrupo(String nombreGrupo, long tamañoRegistro, String nombreBestiario, int tamañoGrupo) {
        long T = tamañoRegistro;
        long numAparecido;

        try {
            RandomAccessFile bestiario = new RandomAccessFile(nombreBestiario, "rw");
            RandomAccessFile grupoEnemigos = new RandomAccessFile(nombreGrupo, "rw");
            for (int i = 1; i <= tamañoGrupo; i++) {
                grupoEnemigos.writeInt(i);

                numAparecido = Metodos.aleatorio(25, 1);
                bestiario.seek((numAparecido - 1) * T);
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
     *
     * @param stringRutaOrigen String que contiene la ruta de origen del archivo
     * @param stringRutaDestino Strin que contiene la ruta del archivo, y a la
     * vez, String que se usará para hacer referencia al objeteo File ubicado en
     * dicha ruta
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
     * Método que permite reescribir el ID de un enemigo, cambiándolo a -1 si su
     * elemento es más débil frente al elemento del jugador.
     *
     * @param elementoElegido String del elemento con el que jugará el usuario
     * @param rutaFichEnemigos String que contiene la ruta del fichero de
     * enemigos
     */
    public static void reescrituraID(String elementoElegido, String rutaFichEnemigos) {
        try {

            int iteracciones = 0;

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

                        System.out.println("El elemento elegido (" + elementoElegido + ") es más fuerte que "
                                + "el elemento del enemigo (" + elementoEnemigo + ").");

                        idGrupo.set(iteracciones, -1);

                        fichero.seek(registroActual);
                        fichero.writeInt(-1);

                    } else {
                        System.out.println("El elemento elegido (" + elementoElegido + ") es más débil que "
                                + "el elemento del enemigo (" + elementoEnemigo + ").");
                    }
                }

                // Avanza al siguiente registro
                registroActual += tamRegistro;
                iteracciones++;
            }

            fichero.close();
            dis.close();
            lector.close();

        } catch (IOException ioe) {
            System.out.println("EXCEPCIÓN POR REESCRITURA");
        }
    }

    /**
     * Método que devuelve el elemento del enemigo mediante una lectura
     * secuencial.
     *
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
     * Método que compara elementos para saber si el elemento elegido por el
     * jugador es más fuerte que el elemento del enemigo.
     *
     * @param elementoElegido String que contiene el elemento elegido por el
     * jugador
     * @param elementoEnemigo String que contiene el elemento del enemigo
     * @return true si el elemento elegido por el jugador es más fuerte, false
     * en caso contrario
     */
    public static boolean esElementoElegidoMasFuerte(String elementoElegido, String elementoEnemigo) {
        boolean fuerte = false;

        if ((elementoElegido.equalsIgnoreCase("agua") && elementoEnemigo.equalsIgnoreCase("fuego"))
                || (elementoElegido.equalsIgnoreCase("tierra") && elementoEnemigo.equalsIgnoreCase("agua"))
                || (elementoElegido.equalsIgnoreCase("fuego") && elementoEnemigo.equalsIgnoreCase("tierra"))) {

            fuerte = true;
        }

        return fuerte;

    }

    /**
     * Método que permite inicializar la creación de un archivo XML utilizando
     * DOM, recibiendo como parámetro el nombre que tendrá el documento XML
     * creado. (No confundir con el nombre del archivo)
     *
     * QUE NI SE OS OCURRA QUE EL DOCUMENTO XML CREADO TENGA ESPACIOS EN EL
     * NOMBRE, PEGA UNA EXCEPCIÓN QUE TE CAGAS
     *
     * @param nombre Nombre que se dará al documento XML creado
     * @return El documento XML creado, para poder manejarlo y añadirle nodos
     * principales, expandiendo así el XML.
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
     * Método que permite crear un nodo principal en un documento XML que se
     * pase como parámetro.
     *
     * @param nombreNodoPrincipal String que contiene el nombre que se le dará
     * al nodo principal creado.
     * @param documento Documento XML del que colgará el nodo principal creado.
     * @return El nodo principal creado, permitiéndonos así crearle más hijos
     * para poder expandirlo.
     */
    public static Element añadirNodoPrincipal(String nombreNodoPrincipal, Document documento) {
        Element nodoPrincipal = documento.createElement(nombreNodoPrincipal);
        documento.getDocumentElement().appendChild(nodoPrincipal);
        return nodoPrincipal;
    }

    /**
     * Método que permite, en un documento XML concreto, crear nodos hijos que
     * contengan únicamente texto y colgarlos de un elemento raíz ("padre").
     *
     * @param nombreNodo String que contiene el nombre que se dará al nodo hijo
     * creado
     * @param textoNodo String que contiene el texto que irá dentro del nodo
     * hijo creado
     * @param documento Documento en el que se crearán el nodo hijo y el texto
     * indicados
     * @param raiz Elemento padre del que se colgará el nodo hijo que creemos en
     * el método
     */
    public static void crearNodo(String nombreNodo, String textoNodo, Document documento, Element raiz) {
        Element nodoHijo = documento.createElement(nombreNodo);
        Text texto = documento.createTextNode(textoNodo);

        nodoHijo.appendChild(texto);
        raiz.appendChild(nodoHijo);
    }

    /**
     * Método que permite generar un archivo XML tomando como parámetro de
     * entrada un objeto Document (documento XML) que haya creado en memoria, y
     * como salida, un archivo cuyo nombre indicaremos en una String pasada por
     * parámetro.
     *
     * @param documento Documento XML que se utilizará como fuente para tomar
     * los datos del archivo final generado
     * @param rutaArchivo String que contiene la ruta del archivo final
     * generado, incluyendo nombre y extensión
     */
    public static void generarXml(Document documento, String rutaArchivo) throws TransformerException {
        Source source = new DOMSource(documento);
        Result salida = new StreamResult(new File(rutaArchivo));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, salida);
    }

    /**
     * Método "grande" que emplea métodos anteriores (versión sobrecargada de
     * leerCadena, inicializar, generarXml) para convertir en archivo XML un
     * fichero con un grupo de enemigos que se le pase por parámetro.
     *
     * El parámetro nombreDocumento, al usarse en el método inicializar, no debe
     * contener espacios (barras bajas son válidas); la ruta del archivo a
     * convertir en XML puede ponerse como ruta relativa, y el parámetro
     * nombreXml debe, por cohesión, terminar con el formato .xml
     *
     * @param nombreDocumento String que contiene el nombre del documento XML
     * que se va a crear.
     * @param rutaArchivo String que contiene la ruta del archivo que vamos a
     * convertir en XML.
     * @param nombreXml String que contiene el nombre que se dará al archivo XML
     * final.
     */
    public static void convertirGrupoAXml(String rutaArchivo, String nombreXml) {

        Document documento = null;

        try {
            documento = Metodos.inicializar("Grupo_de_enemigos");

            long tamaño = 64;
            long registroActual = 0;
            int id;
            Element nodo;
            RandomAccessFile fichero = new RandomAccessFile(rutaArchivo, "rw");

            while (registroActual < fichero.length()) {

                byte[] registro = new byte[(int) tamaño];
                fichero.seek(registroActual);
                fichero.readFully(registro);
                // Antes de evaluar si el ID del enemigo leído es o no -1 para
                // ver si se añade o no al XML, se evalúa que se trate de un
                // registro en blanco para sencillamente omitirlo y que no
                // aparezca en el archivo XML final.

                // Se crea un array de bytes de tamaño igual al tamaño de los
                // registros que estamos utilizando, se ubica el puntero en el
                // registro que estemos leyendo, se lee el registro completo,
                // se asigna al array de bytes creado y se pasa como parámetro
                // a un método que lo evalúa.
                fichero.seek(registroActual);
                id = fichero.readInt();

                if (!esRegistroEnBlanco(registro)) {
                    if (id != -1) {
                        fichero.seek(registroActual);

                        nodo = Metodos.añadirNodoPrincipal("Enemigo", documento);
                        Metodos.crearNodo("id", String.valueOf(fichero.readInt()), documento, nodo);
                        Metodos.crearNodo("nombre", Metodos.leerCadena(20, fichero, true), documento, nodo);
                        Metodos.crearNodo("elemento", Metodos.leerCadena(10, fichero, true), documento, nodo);

                    }
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
     * Método que permite verificar si un registro de bytes que se esté
     * evaluando está o no completamente en blanco, fruto de haber escrito con
     * RandomAccessFile saltando registros, para así omitirlo en la creación del
     * archivo XML final.
     *
     * @param registro Array de bytes que conforman un registro a evaluar
     * @return True si se trata de un registro en blanco, False en caso
     * contrario
     */
    public static boolean esRegistroEnBlanco(byte[] registro) {
        boolean enBlanco = true;

        for (byte e : registro) {
            if (e != 0x00) {
                enBlanco = false;
            }
        }

        return enBlanco;
    }

    /**
     * Método que permite la creación de un fichero HTML a partir de una hoja de
     * estilos XSL y un fichero fuente XML
     *
     * @param hojaEstilo fichero con la plantilla XSL
     * @param fichXML fichero fuente con la estructura XML
     * @throws IOException
     */
    public static void crearHTML(String hojaEstilo, String fichXML) throws IOException {
        File pagHTML = new File("pagina.html");
        FileOutputStream os = new FileOutputStream(pagHTML);
        Source estilos = new StreamSource(hojaEstilo);
        Source datos = new StreamSource(fichXML);
        Result result = new StreamResult(os);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result);
        } catch (Exception e) {
            System.out.println("Ruta invalida");
        }
        os.close();

    }

    /**
     * Lee el contenido de un archivo HTML o XML y lo devuelve como una cadena.
     *
     * @param ruta Ruta del archivo HTML o XML que se desea leer.
     * @return El contenido del archivo como una cadena en formato UTF-8.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static String leerHTMLXML(String ruta, String hojaEstilo) throws IOException {
        crearHTML(hojaEstilo, ruta);
        byte[] bytes = Files.readAllBytes(Paths.get("pagina.html"));
        String fileContent = new String(bytes, "UTF-8");
        return fileContent;
    }

    /**
     * Duplica un archivo desde una ubicación de origen a una ubicación de
     * destino.
     *
     * @param sourcePath Ruta del archivo de origen que se desea duplicar.
     * @param destinationPath Ruta del archivo de destino donde se copiará el
     * archivo.
     * @throws IOException Si ocurre un error al duplicar el archivo, como si el
     * archivo de origen no existe o el archivo de destino ya existe.
     */
    public static void duplicarFile(String sourcePath, String destinationPath) {
        try {
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationPath);

            // Verificar si el archivo de origen existe
            if (!sourceFile.exists()) {
                throw new IOException("El archivo de origen no existe.");
            }

            // Verificar si el archivo de destino ya existe
            if (destinationFile.exists()) {
                throw new IOException("El archivo de destino ya existe. No se puede duplicar.");
            }

            // Crear streams de entrada y salida
            FileInputStream input = new FileInputStream(sourceFile);
            FileOutputStream output = new FileOutputStream(destinationFile);

            // Leer y escribir el archivo
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) > 0) {
                output.write(buffer, 0, bytesRead);
            }

            // Cerrar los streams
            input.close();
            output.close();

            System.out.println("Archivo duplicado con éxito.");
        } catch (IOException ioe) {
            System.out.println("Excepción de tipo IOE al duplicar archivos");
        }
    }

    /**
     * Duplica un directorio y su contenido desde una ubicación de origen a una
     * ubicación de destino.
     *
     * @param sourceDirectory Ruta del directorio de origen que se desea
     * duplicar.
     * @param destinationDirectory Ruta del directorio de destino donde se
     * copiará el directorio y su contenido.
     * @throws IOException Si ocurre un error al duplicar el directorio, como si
     * el directorio de origen no existe.
     */
    public static void duplicarDirectory(String sourceDirectory, String destinationDirectory) throws IOException {
        File source = new File(sourceDirectory);
        File destination = new File(destinationDirectory);

        // Verificar si el directorio de origen existe
        if (!source.exists() || !source.isDirectory()) {
            throw new IOException("El directorio de origen no existe.");
        }

        // Crear el directorio de destino si no existe
        if (!destination.exists()) {
            destination.mkdir();
        }

        // Obtener lista de archivos y subdirectorios en el directorio de origen
        File[] files = source.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                // Si es un subdirectorio, duplicar recursivamente
                duplicarDirectory(file.getPath(), destinationDirectory + File.separator + file.getName());
            } else {
                // Si es un archivo, duplicar utilizando Files.copy
                File destFile = new File(destinationDirectory + File.separator + file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }

    }

    /**
     * Mueve un directorio y su contenido desde una ubicación de origen a una
     * ubicación de destino.
     *
     * @param directorioOrigen Ruta del directorio de origen que se desea mover.
     * @param directorioDestino Ruta del directorio de destino donde se moverá
     * el directorio y su contenido.
     * @throws IOException Si ocurre un error al mover el directorio, como si el
     * directorio de origen no existe.
     */
    public static void moverDirectorio(String directorioOrigen, String directorioDestino) {
        try {
            File origen = new File(directorioOrigen);
            File destino = new File(directorioDestino);

            // Verificar si el directorio de origen existe
            if (!origen.exists() || !origen.isDirectory()) {
                throw new IOException("El directorio de origen no existe.");
            }

            // Crear el directorio de destino si no existe
            if (!destino.exists()) {
                destino.mkdir();
            }

            // Obtener lista de archivos y subdirectorios en el directorio de origen
            File[] archivos = origen.listFiles();

            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    // Si es un subdirectorio, mover recursivamente
                    moverDirectorio(archivo.getPath(), directorioDestino + File.separator + archivo.getName());
                } else {
                    // Si es un archivo, mover utilizando Files.move
                    File destinoArchivo = new File(directorioDestino + File.separator + archivo.getName());
                    Files.move(archivo.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // Después de mover los contenidos, eliminar el directorio de origen
            origen.delete();
        } catch (IOException ioe) {
            System.out.println("Excepción de tipo ioe");
        }
    }

    /**
     * Método que permite activar el "modo fácil" para lidiar con un grupo de
     * enemigos en concreto, recorriéndolo y reasignando la debilidad de todos
     * los enemigos existentes a aquella que se haya pasado en una String por
     * parámetro. En el caso de pasarse una String cuyo contenido no coincida
     * con "fuego", "tierra" o "agua" (los tres elementos existentes en el
     * juego), por defecto se asignará a "tierra".
     *
     * @param rutaArchivo String que contiene la ruta del archivo para el cual
     * se quiere activar el "modo fácil".
     * @param debilidadAsignar String que contiene el elemento al cual se
     * reasignará la debilidad del grupo completo de enemigos. De ser un valor
     * distinto de "fuego", "tierra" o "agua", se asignará automáticamente a
     * "tierra".
     */
    public static void modoFacil(String rutaArchivo, String debilidadAsignar) {
        String debilidad;
        if (!debilidadAsignar.equalsIgnoreCase("fuego")
                && !debilidadAsignar.equalsIgnoreCase("agua")
                && !debilidadAsignar.equalsIgnoreCase("tierra")) {

            debilidad = new String("tierra");

        } else {

            debilidad = new String(debilidadAsignar);

        }
        StringBuffer sb = new StringBuffer(debilidad);
        sb.setLength(10);

        try {

            RandomAccessFile fichero = new RandomAccessFile(rutaArchivo, "rw");
            while (fichero.getFilePointer() < fichero.length()) {

                fichero.readInt();
                leerCadena(20, fichero);
                fichero.writeChars(sb.toString());

            }

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
//        if (Juego.grupoActual != null){
//            Metodos.leerFicheroEnemigosSecuencial(Juego.grupoActual);
//            if (tipoGrupo != null){
//                for (String str : tipoGrupo){
//                System.out.println(str);
//            }
//            }
//            
//        }

    }

    /**
     * Método que, utilizando DOM, permite llevar a cabo modificaciones del
     * texto de nodos existentes en un fichero XML. Se pasarán como parámetros
     * una String que contenga el texto de aquellos nodos que se deseen
     * modificar y una String que contenga el nuevo texto que se desea que
     * tengan estos nodos. También una String que contenga la ruta del archivo a
     * modificar. Únicamente será aplicable para los nodos de nombres, para
     * utilizarse en la sección de juego personalizado y poder nombrar a los
     * enemigos que se generen como se quiera.
     *
     * @param rutaArchivo String que contiene la ruta del archivo XML cuyo nodo
     * será modificado
     * @param textoNodo String que contiene el texto (contenido en un nodo) que
     * se quiera modificar; se empleará para modificar los nombres de los
     * enemigos (se modificarán todos aquellos nodos cuyo texto contenido
     * coincida con este parámetro; no se reasignan los nombres de los enemigos
     * de un tipo, se reasignan todos)
     * @param textoNuevo String que contiene el nuevo valor de texto que
     * contendrán los nodos modificacos; el nuevo nombre que se dará a los
     * enemigos. (Al igual que en el caso anterior, se utiliza en todos los
     * nodos reasignados)
     */
    public static void modificarXml(String rutaArchivo, String textoNodo, String textoNuevo) {
        Document documento = null;

        try {
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(archivo);

            documento.getDocumentElement().normalize();

            // Obtenemos todos los nodos etiquetados como "nombre", que son
            // aquellos que pretendemos modificar (el elemento no se puede
            // tocar aquí)
            NodeList listaNodos = documento.getElementsByTagName("nombre");

            // Recorremos la lista de nodos obtenida, elemento por elemento,
            // iterando entre ellos mediante la i
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    // Se compara que el elemento texto contenido por el nodo
                    // que está siendo evaluado coincida con el texto contenido
                    // por la String que se ha pasado por parámetro
                    if (nodo.getTextContent().equalsIgnoreCase(textoNodo)) {
                        nodo.setTextContent(textoNuevo);
                    }
                }
            }

            archivo.delete();

        } catch (ParserConfigurationException pce) {
            System.out.println("Excepción en el parser para modificar nodos");
        } catch (SAXException saxe) {
            System.out.println("Excepción del SAX al modificar nodos");
        } catch (IOException ioe) {
            System.out.println("Excepción de tipo IOE");
        }

        try {
            generarXml(documento, rutaArchivo);
        } catch (TransformerException tce) {
            System.out.println("Excepción del transformer en la modificación del XML");
        }
    }

    public static void esperar() throws InterruptedException {
        waitingNextGroup = true;
        System.out.println("hola");
        new Thread(() -> {
            System.out.println("asdasd");
            while (waitingNextGroup) {
                // Realiza la espera o procesamiento en segundo plano
                try {
                    System.out.println("hika");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void detenerEsperar() {
        waitingNextGroup = false;
    }

    /**
     * Método que comprueba que la estructura de un archivo XML pasado por
     * parámetro cumple con la estructura esperada.
     *
     * @param fichXML String que contiene el nombre del archivo XML a comprobar
     * @return true si cumple con la estructura esperada, false en caso
     * contrario
     */
    public static boolean comprobarEstructuraXML(String fichXML) {
        boolean estructuraAdecuada = true;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fichXML));
            Element root = document.getDocumentElement();

            if (root.getTagName().equals("Grupo_de_enemigos")) {
                NodeList enemigos = root.getElementsByTagName("Enemigo");
                boolean continuarVerificacion = true;

                for (int i = 0; i < enemigos.getLength() && continuarVerificacion; i++) {
                    Element enemigo = (Element) enemigos.item(i);
                    NodeList id = enemigo.getElementsByTagName("id");
                    NodeList nombre = enemigo.getElementsByTagName("nombre");
                    NodeList elemento = enemigo.getElementsByTagName("elemento");

                    if (id.getLength() != 1 || nombre.getLength() != 1 || elemento.getLength() != 1) {
                        System.out.println("Enemigo " + (i + 1) + " no cumple con la estructura esperada.");
                        estructuraAdecuada = false;
                        continuarVerificacion = false;
                    }
                }
            } else {
                System.out.println("El documento no tiene la estructura esperada.");
                estructuraAdecuada = false;
            }
        } catch (Exception e) {
            System.out.println(e);
            estructuraAdecuada = false;
        }

        return estructuraAdecuada;
    }

    /**
     * Método que encripta una cadena utilizando la técnica de sumar +1 a cada
     * carácter
     *
     * @param contenido String que contiene el texto que queremos encriptar
     * @return contenidoEncriptado String con el texto encriptado
     */
    public static String encriptarContenido(String contenido) {
        String contenidoEncriptado = "";
        for (char c : contenido.toCharArray()) {
            char caracterEncriptado = c;

            if (c >= '0' && c <= '9') {
                caracterEncriptado = (char) ('0' + (c - '0' + 1) % 10);
            } else {
                caracterEncriptado = (char) (c + 1);
            }

            contenidoEncriptado += caracterEncriptado;
        }

        return contenidoEncriptado;
    }

    /**
     * Método de encriptación de ficheros XML utilizando la lectura con DOM
     *
     * @param fichXML String que contiene el nombre del fichero XML a encriptar
     */
    public static void encriptarXML(String fichXML) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fichXML));
            document.getDocumentElement().normalize();
            boolean estructuraValida = comprobarEstructuraXML(fichXML);

            if (estructuraValida) {
                NodeList enemigos = document.getElementsByTagName("Enemigo");
                for (int i = 0; i < enemigos.getLength(); i++) {
                    Element enemigo = (Element) enemigos.item(i);
                    NodeList elementos = enemigo.getChildNodes();

                    for (int j = 0; j < elementos.getLength(); j++) {
                        Node elemento = elementos.item(j);

                        if (elemento.getNodeType() == Node.ELEMENT_NODE) {
                            String contenido = elemento.getTextContent();
                            String contenidoEncriptado = encriptarContenido(contenido);
                            elemento.setTextContent(contenidoEncriptado);
                        }
                    }
                }
            } else {
                System.out.println("La estructura del XML no es válida, no se encriptará.");
            }

            generarXml(document, fichXML);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * Método que permite crear y reubicar todo lo necesario para poder iniciar
     * el juego.
     * 
     * Inicia creando un archivo bestiario, que contendrá todos los posibles
     * enemigos que puedan aparecer a lo largo del juego.
     * 
     * Crea también tres zonas de juego y una zona "grande" que las contendrá,
     * para que el inicio del juego se realice desde este directorio "zona grande".
     * 
     * A continuación, crea los archivos grupos de enemigos (leyendo registros
     * aleatorios del bestiario inicialmente generado) para el modo normal
     * del juego, los mueve a sus respectivas zonas y los duplica, para tener
     * disponibles los archivos que emplear en el modo fácil del juego.
     * 
     * Finalmente, mueve los directorios de las tres zonas junto con todo su
     * contenido hacia el interior del directorio de la zona principal y 
     * elimina el bestiario, para que el usuario no tenga acceso a él desde
     * este modo de juego.
     */
    public static void iniciarJuegoNormal() {

        int tamañoRegistro = 64;

        Metodos.crearBestiario("enemigos.dat");
        Metodos.crearZona(".\\Zona 1");
        Metodos.crearZona(".\\Zona 2");
        Metodos.crearZona(".\\Zona 3");
        Metodos.crearZona(".\\Zona de juego");

        Metodos.creaGrupo("nGrupo 1-1.dat", tamañoRegistro, "enemigos.dat", 5);
        Metodos.moverGrupo(".\\nGrupo 1-1.dat", ".\\Zona 1\\nGrupo 1-1.dat");
        Metodos.duplicarFile(".\\Zona 1\\nGrupo 1-1.dat", ".\\Zona 1\\fGrupo 1-1.dat");

        Metodos.creaGrupo("nGrupo 1-2.dat", tamañoRegistro, "enemigos.dat", 6);
        Metodos.moverGrupo(".\\nGrupo 1-2.dat", ".\\Zona 1\\nGrupo 1-2.dat");
        Metodos.duplicarFile(".\\Zona 1\\nGrupo 1-2.dat", ".\\Zona 1\\fGrupo 1-2.dat");

        Metodos.creaGrupo("nGrupo 1-3.dat", tamañoRegistro, "enemigos.dat", 7);
        Metodos.moverGrupo(".\\nGrupo 1-3.dat", ".\\Zona 1\\nGrupo 1-3.dat");
        Metodos.duplicarFile(".\\Zona 1\\nGrupo 1-3.dat", ".\\Zona 1\\fGrupo 1-3.dat");

        Metodos.creaGrupo("nGrupo 2-1.dat", tamañoRegistro, "enemigos.dat", 1);
        Metodos.moverGrupo(".\\nGrupo 2-1.dat", ".\\Zona 2\\nGrupo 2-1.dat");
        Metodos.duplicarFile(".\\Zona 2\\nGrupo 2-1.dat", ".\\Zona 2\\fGrupo 2-1.dat");

        Metodos.creaGrupo("nGrupo 2-2.dat", tamañoRegistro, "enemigos.dat", 2);
        Metodos.moverGrupo(".\\nGrupo 2-2.dat", ".\\Zona 2\\nGrupo 2-2.dat");
        Metodos.duplicarFile(".\\Zona 2\\nGrupo 2-2.dat", ".\\Zona 2\\fGrupo 2-2.dat");

        Metodos.creaGrupo("nGrupo 2-3.dat", tamañoRegistro, "enemigos.dat", 3);
        Metodos.moverGrupo(".\\nGrupo 2-3.dat", ".\\Zona 2\\nGrupo 2-3.dat");
        Metodos.duplicarFile(".\\Zona 2\\nGrupo 2-3.dat", ".\\Zona 2\\fGrupo 2-3.dat");

        Metodos.creaGrupo("nGrupo 3-1.dat", tamañoRegistro, "enemigos.dat", 4);
        Metodos.moverGrupo(".\\nGrupo 3-1.dat", ".\\Zona 3\\nGrupo 3-1.dat");
        Metodos.duplicarFile(".\\Zona 3\\nGrupo 3-1.dat", ".\\Zona 3\\fGrupo 3-1.dat");

        Metodos.creaGrupo("nGrupo 3-2.dat", tamañoRegistro, "enemigos.dat", 8);
        Metodos.moverGrupo(".\\nGrupo 3-2.dat", ".\\Zona 3\\nGrupo 3-2.dat");
        Metodos.duplicarFile(".\\Zona 3\\nGrupo 3-2.dat", ".\\Zona 3\\fGrupo 3-2.dat");

        Metodos.creaGrupo("nGrupo 3-3.dat", tamañoRegistro, "enemigos.dat", 9);
        Metodos.moverGrupo(".\\nGrupo 3-3.dat", ".\\Zona 3\\nGrupo 3-3.dat");
        Metodos.duplicarFile(".\\Zona 3\\nGrupo 3-3.dat", ".\\Zona 3\\fGrupo 3-3.dat");

        Metodos.moverDirectorio(".\\Zona 1", ".\\Zona de juego\\Zona 1");
        Metodos.moverDirectorio(".\\Zona 2", ".\\Zona de juego\\Zona 2");
        Metodos.moverDirectorio(".\\Zona 3", ".\\Zona de juego\\Zona 3");

        File bestiario = new File(".\\enemigos.dat");
        bestiario.delete();
    }

    /**
     * Método que permite comprobar si se ha realizado el borrado lógico de un
     * fichero completo de enemigos; lo revisa utilizando un contador para
     * aquellos registros de ID que no estén asignados a -1, y si detecta que
     * todos los registros de ID se encuentran en ese estado, devuelve true.
     *
     * @param rutaArchivo String que contiene la ruta del fichero cuyo contenido
     * se va a evaluar
     * @return true si se ha realizado un borrado lógico completo de todos los
     * registros existentes, false en caso contrario
     */
    public static boolean compruebaBorradoCompleto(String rutaArchivo) {
        boolean borradoCompleto = false;

        int cuentaNoBorrado = 0;
        int idLeido;
        try {

            RandomAccessFile fichero = new RandomAccessFile(rutaArchivo, "rw");
            while (fichero.getFilePointer() < fichero.length()) {

                idLeido = fichero.readInt();
                if (idLeido != -1) {
                    cuentaNoBorrado++;
                }

                fichero.skipBytes(60);
            }
            fichero.close();

        } catch (IOException ioe) {
            System.out.println("Excepción de tipo IOE al verificar que un fichero de enemigos esté borrado completo");
        }

        if (cuentaNoBorrado == 0) {
            borradoCompleto = true;
        }

        return borradoCompleto;
    }

    /**
     * Método que desencripta una cadena utilizando la técnica de restar -1 a
     * cada carácter
     *
     * @param contenidoEncriptado String que contiene el texto que queremos
     * desencriptar
     * @return contenidoDesencriptado String con el texto desencriptado
     */
    public static String desencriptarContenido(String contenidoEncriptado) {
        String contenidoDesencriptado = "";
        for (char c : contenidoEncriptado.toCharArray()) {
            char caracterDesencriptado = c;

            if (c >= '0' && c <= '9') {
                caracterDesencriptado = (char) ('0' + (c - '0' - 1 + 10) % 10);
            } else {
                caracterDesencriptado = (char) (c - 1);
            }

            contenidoDesencriptado += caracterDesencriptado;
        }

        return contenidoDesencriptado;
    }

    /**
     * Método para desencriptar un archivo XML utilizando la lectura con SAX y
     * guardar el resultado en un nuevo archivo.
     *
     * @param fichXMLEncriptado Nombre del archivo XML encriptado.
     * @param nombreFichDesencriptado Nombre del archivo desencriptado donde se
     * guardará el resultado.
     * @throws ParserConfigurationException Cuando ocurre un error de
     * configuración del parser SAX.
     * @throws SAXException Cuando ocurre un error durante el análisis del
     * archivo XML encriptado.
     * @throws TransformerException Cuando ocurre un error durante la
     * transformación del XML desencriptado.
     */
    public static void desencriptarXML(String fichXMLEncriptado, String nombreFichDesencriptado)
            throws ParserConfigurationException, SAXException, TransformerException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            GestionContenido handler = new GestionContenido();
            reader.setContentHandler(handler);

            InputSource source = new InputSource(fichXMLEncriptado);
            reader.parse(source);
            String nuevoXml = handler.getNuevoXml();
            StringBuilder xmlCompleto = new StringBuilder();
            xmlCompleto.append("<Grupo_de_enemigos>")
                    .append(nuevoXml)
                    .append("</Grupo_de_enemigos>");
            String xmlCompleto2 = xmlCompleto.toString();
            //System.out.println(xmlCompleto2);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StreamSource fuente = new StreamSource(new StringReader(xmlCompleto2));
            StreamResult result = new StreamResult(new File(nombreFichDesencriptado));
            transformer.transform(fuente, result);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Método que permite utilizar las clases FileOutputStream y
     * DataOutputStream para escribir "a continuación", tomando un fichero de
     * datos binario y escribiendo al final del mismo datos leídos de un fichero
     * bestiario que se le pase por parámetro y que se leerá mediante la clase
     * RandomAccessFile
     *
     * @param registroUsuario Número de ID con que el usuario desea que sus
     * monstruos aparezcan en el fichero personalizado.
     * @param registroElegido Número de ID asociado en el bestiario al monstruo
     * que el usuario desea introducir en su fichero de monstruos personalizado.
     * @param rutaBestiario String que contiene la ruta del fichero bestiario
     * del cual se leerán los registros para después escribirlos en el fichero
     * de datos personalizado.
     * @param rutaPersonalizado String que contiene la ruta en la que se ubica
     * el fichero de datos personalizado en el que se escribirán los datos
     * leídos.
     */
    public static void escribirAContinuacion(int registroUsuario, int registroElegido, String rutaBestiario, String rutaPersonalizado) {
        long tamañoRegistro = 64;
        long bytePos = (registroElegido - 1) * tamañoRegistro;

        try {
            RandomAccessFile ficheroBestiario = new RandomAccessFile(rutaBestiario, "rw");
            FileOutputStream escritor = new FileOutputStream(rutaPersonalizado, true);
            DataOutputStream dos = new DataOutputStream(escritor);

            ficheroBestiario.seek(bytePos);
            ficheroBestiario.readInt();
            dos.writeInt(registroUsuario);
            dos.writeChars(Metodos.leerCadena(20, ficheroBestiario, false));
            dos.writeChars(Metodos.leerCadena(10, ficheroBestiario, false));

            dos.close();
            escritor.close();
            ficheroBestiario.close();

        } catch (IOException ioe) {
            System.out.println("Excepción IOE al añadir un enemigo al grupo personalizado con File/DataOutputStream");
        }
    }

    /**
     * Método que permite utilizar la clase RandomAccessFile para escribir "al
     * final" de un archivo, dejando un registro completo en blanco entre el
     * punto en el que termina el archivo y el punto de escritura de registros.
     * Los registros que se escriban serán aquellos que se lean de un archivo
     * bestiario, mediante el uso de la clase RandomAccessFile.
     *
     * @param registroUsuario Número de ID con que el usuario desea que sus
     * monstruos aparezcan en el fichero personalizado.
     * @param registroElegido Número de ID asociado en el bestiario al monstruo
     * que el usuario desea introducir en su fichero de monstruos personalizado.
     * @param rutaBestiario String que contiene la ruta del fichero bestiario
     * del cual se leerán los registros para después escribirlos en el fichero
     * de datos personalizado.
     * @param rutaPersonalizado String que contiene la ruta en la que se ubica
     * el fichero de datos personalizado en el que se escribirán los datos
     * leídos.
     */
    public static void insertarAccesoAleatorio(int registroUsuario, int registroElegido, String rutaBestiario, String rutaPersonalizado) {

        long tamañoRegistro = 64;
        long bytePos = (registroElegido - 1) * tamañoRegistro;

        try {
            RandomAccessFile ficheroBestiario = new RandomAccessFile(rutaBestiario, "rw");
            RandomAccessFile ficheroPersonalizado = new RandomAccessFile(rutaPersonalizado, "rw");

            ficheroBestiario.seek(bytePos);
            // Línea en la que se hace la inserción aleatoria dejando
            // registros en blanco; se salta hasta el final del archivo, se
            // deja un registro completo en blanco y a continuación se escribe
            ficheroPersonalizado.seek(ficheroPersonalizado.length() + tamañoRegistro);

            ficheroPersonalizado.writeInt(registroUsuario);
            ficheroPersonalizado.writeChars(Metodos.leerCadena(20, ficheroBestiario, false));
            ficheroPersonalizado.writeChars(Metodos.leerCadena(10, ficheroBestiario, false));

            ficheroPersonalizado.close();
            ficheroBestiario.close();

        } catch (IOException ioe) {
            System.out.println("Excepción IOE al añadir un enemigo al grupo personalizado con RandomAccessFile saltando registros");
        }
    }

    /**
     * Método que decide a cara o cruz si el monstruo elegido del bestiario se
     * insertará al final del fichero personalizado con DataOutputStream
     * escribiendo al final o con RandomAccessFile posicionándose al final y
     * dejando un registro en blanco.
     *
     * @param registroUsuario Número de ID con que el usuario desea que sus
     * monstruos aparezcan en el fichero personalizado.
     * @param registroElegido Número de ID asociado en el bestiario al monstruo
     * que el usuario desea introducir en su fichero de monstruos personalizado.
     * @param rutaBestiario String que contiene la ruta del fichero bestiario
     * del cual se leerán los registros para después escribirlos en el fichero
     * de datos personalizado.
     * @param rutaPersonalizado String que contiene la ruta en la que se ubica
     * el fichero de datos personalizado en el que se escribirán los datos
     * leídos.
     */
    public static void insertarMonstruoEnFicheroPersonalizado(int registroUsuario, int registroElegido, String rutaBestiario, String rutaPersonalizado) {
        int aleatorio = aleatorio(1, 0);

        if (aleatorio == 0) {
            System.out.println("La moneda ha salido cara. Los enemigos se añaden al final.");
            escribirAContinuacion(registroUsuario, registroElegido, rutaBestiario, rutaPersonalizado);

        } else if (aleatorio == 1) {
            System.out.println("La moneda ha salido cruz. Los enemigos se añaden saltando un registro.");
            insertarAccesoAleatorio(registroUsuario, registroElegido, rutaBestiario, rutaPersonalizado);

        }
    }

    /**
     * Método para la creación de los directorios de importaciones y
     * exportaciones. Si ya existen, no se hace nada; en caso contrario, se
     * crean en la ruta especificada.
     *
     * @param rutaDir String que contiene la ruta donde se crearán los
     * directorios.
     */
    public static void crearDirImportarExportar(String rutaDir) {
        File dir = new File(rutaDir);
        if (dir.exists()) {
            System.out.println("El directorio ya existe. No se realizará ninguna acción.");
        } else {
            dir.mkdir();
        }
    }
    
    /**
     * Método que crea la carpeta "Zona personalizada" y mete dentro
     * el bestiario y un grupo de monstruos vacío
     */
    public static void iniciarJuegoPersonalizado() {
        crearZona(".\\Zona personalizada");
        crearBestiario(".\\Zona personalizada\\enemigos.dat");
        creaGrupo("Monstruos domados.dat", 64, "enemigos.dat", 0);
        moverGrupo(".\\Monstruos domados.dat",".\\Zona Personalizada\\Monstruos domados.dat");  
        
        File bestiario = new File(".\\enemigos.dat");
        bestiario.delete();
    }
    
    
    
    /**
     * Método al que se le pasa por parámetro una String que contiene el nombre
     * del monstruo que se está evaluando y devuelve el grito o reacción que
     * emitirá dicho monstruo cuando se entre en batalla con él.
     * @param enemigo String que contiene el nombre del enemigo que se está
     * evaluando
     * @return String que contiene el grito de guerra emitido por un monstruo
     * al enfrentarlo en combate
     */
    public static String obtenerGritoGuerra(String enemigo) {
        String grito = null;
        
        switch (enemigo) {
            case "Pyrobabosa" -> {
                grito = new String("¡Grrrblrblrblr!");
            }
            case "Geobabosa" -> {
                grito = new String("¡GRBL BLGR!");
            }
            case "Hydrobabosa" -> {
                grito = new String("¡Contrariando la creencia popular, algunas babosas sabemos hablar!");
            }
            case "Pyrodragarto" -> {
                grito = new String("¡Hisssssssssss!");
            }
            case "Geodragarto" -> {
                grito = new String("¡Hiss-hiss, hiss-hiss!");
            }
            case "Hydrodragarto" -> {
                grito = new String("*Parpadea, visiblemente confuso*");
            }
            case "Pyroesqueleto" -> {
                grito = new String("¡Usaré tu carne para recubrir mis huesos!");
            }
            case "Geoesqueleto" -> {
                grito = new String("¡Me haré un traje con tu pellejo!");
            }
            case "Hydroesqueleto" -> {
                grito = new String("Oye, ¿haces algo esta noche? *Sonidos de huesos*");
            }
            case "Pyrocaballero" -> {
                grito = new String("¡En guardia!");
            }
            case "Geocaballero" -> {
                grito = new String("¡Prepárate a morir!");
            }
            case "Hydrocaballero" -> {
                grito = new String("Otra vez a pelear... ¿cuánto falta para el viernes?");
            }
            case "Pyrocapitán" -> {
                grito = new String("¡¡TROPAS!! ¡¡EN FORMACIÓN!!");
            }
            case "Geocapitán" -> {
                grito = new String("¡¡RESISTID, SOLDADOS!!");
            }
            case "Hydrocapitán" -> {
                grito = new String("Otra vez trabajando en mi día libre...");
            }
            case "Pyrodiablillo" -> {
                grito = new String("¡Ñijiji! ¡Prepárate a sufrir!");
            }
            case "Geodiablillo" -> {
                grito = new String("¡Ñujuju! ¡Se avecina tu suplicio!");
            }
            case "Hydrodiablillo" -> {
                grito = new String("Yo no me río. ¿Tienes un euro para el bus?");
            }
            case "Pyrobestia" -> {
                grito = new String("¡¡¡GROAAAAAAAAAAAAAAAR!!!");
            }
            case "Geobestia" -> {
                grito = new String("Grrrrrr... grrrhrhgrrrrhrhgrrrrhg...");
            }
            case "Hydrobestia" -> {
                grito = new String("*Puedes escucharla roncar*");
            }
            case "Pyrodemonio" -> {
                grito = new String("Tu perdición... está cerca...");
            }
            case "Geodemonio" -> {
                grito = new String("Aprovecha estos minutos. Para ti, serán los últimos.");
            }
            case "Hydrodemonio" -> {
                grito = new String("Lo cierto es que me habría gustado estudiar Artes.");
            }
            case "Wyvern demente" -> {
                grito = new String("*Lanza una llamarada al aire*");
            }
        }
        
        return grito;
    }
    
    
    
    /**
     * Método al que se le pasa por parámetro una String que contiene el nombre
     * del monstruo que se está evaluando y devuelve la impresión que se
     * tiene de él.
     * @param enemigo String que contiene el nombre del enemigo que se está
     * evaluando
     * @return String que contiene las impresiones que se obtienen del monstruo
     * evaluado
     */
    public static String obtenerEsLindo(String enemigo) {
        String grito = null;
        
        switch (enemigo) {
            case "Pyrobabosa" -> {
                grito = new String("Te dan ganas de darle un pyrobesito.");
            }
            case "Geobabosa" -> {
                grito = new String("Pues... ni fu, ni fa.");
            }
            case "Hydrobabosa" -> {
                grito = new String("Te dan ganas de que no hable tanto.");
            }
            case "Pyrodragarto" -> {
                grito = new String("Se lo ve escamosito. Es lindo.");
            }
            case "Geodragarto" -> {
                grito = new String("También es escamosito, pero un poco menos lindo.");
            }
            case "Hydrodragarto" -> {
                grito = new String("Te dan ganas de acariciarlo.");
            }
            case "Pyroesqueleto" -> {
                grito = new String("Está un poco en los huesos.");
            }
            case "Geoesqueleto" -> {
                grito = new String("No es feo, sólo es complicado de mirar.");
            }
            case "Hydroesqueleto" -> {
                grito = new String("Mono, lo que se dice mono, no es.");
            }
            case "Pyrocaballero" -> {
                grito = new String("Demasiado mazado. No es tu tipo.");
            }
            case "Geocaballero" -> {
                grito = new String("Es normalucho, pero tirando a guapete.");
            }
            case "Hydrocaballero" -> {
                grito = new String("El ser tan quejica le quita atractivo.");
            }
            case "Pyrocapitán" -> {
                grito = new String("Lo cierto es que es todo un galán.");
            }
            case "Geocapitán" -> {
                grito = new String("Le notas una musculatura rocosa.");
            }
            case "Hydrocapitán" -> {
                grito = new String("Además de apuesto, parece adinerado.");
            }
            case "Pyrodiablillo" -> {
                grito = new String("Está bastante raquítico.");
            }
            case "Geodiablillo" -> {
                grito = new String("Es feo. Feo con ganas. Feo con saña y avaricia.");
            }
            case "Hydrodiablillo" -> {
                grito = new String("Tiene cara como de haber estado chupando limones.");
            }
            case "Pyrobestia" -> {
                grito = new String("Está bastante enfadada. Ganas de acariciarla no te dan.");
            }
            case "Geobestia" -> {
                grito = new String("Parece una tortuga grande. Sientes el deseo de darle lechuga.");
            }
            case "Hydrobestia" -> {
                grito = new String("La ves tan dormidita que te dan ganas de acurrucarte con ella.");
            }
            case "Pyrodemonio" -> {
                grito = new String("Lo cierto es que es bastante atractivo.");
            }
            case "Geodemonio" -> {
                grito = new String("No está mal, pero necesita una ducha.");
            }
            case "Hydrodemonio" -> {
                grito = new String("Si se cortase esas greñas, sería guapillo.");
            }
            case "Wyvern demente" -> {
                grito = new String("No es demasiado monete. De hecho, da canguelo.");
            }
        }
        
        return grito;
    }
    
    
    
    /**
     * Método que permite añadir nodos XML, dependiendo de una determinada
     * estructura de archivos XML, a un archivo cuya ruta se le pase por
     * parámetro. 
     * El nombre del nodo que se desea añadir también deberá pasarse por
     * parámetro; únicamente hay implementación para los nodos "gritoguerra"
     * y "eslindo".
     * @param rutaArchivo String que contiene la ruta del archivo XML al que
     * se añadirán los nodos.
     * @param nombreNodo String que contiene el nombre del nodo a añadir;
     * las posibilidades por el momento son "gritoguerra" y "eslindo".
     */
    public static void añadirNodoXml(String rutaArchivo, String nombreNodo) {
        try {
           File archivo = new File(rutaArchivo);
           // Parseamos el archivo xml a utilizar
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document documento = builder.parse(archivo);
           
           // Le aplicamos normalización
           documento.getDocumentElement().normalize();
           
           // Recuperamos la lista de nodos etiquetados como "Enemigo"
           // y la recorremos
           NodeList listaNodos = documento.getElementsByTagName("Enemigo");
           
           for (int i=0; i<listaNodos.getLength(); i++) {
               Node nodo = listaNodos.item(i);
               // Con ésto podemos iterar entre los distintos nodos "Enemigo".
               
               
               // Si lo que se están recorriendo son nodos, se les hace un
               // casting a objeto de la clase Elemento para poder tratarlos
               // como tal (como cuando escribimos ficheros XML con DOM)
               if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                   Element elemento = (Element) nodo;
                   
                   // Tras ello, se verifica si el nodo cuyo nombre pasado por
                   // parámetro existe; si no existe, se crea y añade, y si 
                   // existe, no se aplican cambios 
                   NodeList listaNodosParam = elemento.getElementsByTagName(nombreNodo);
                   if (listaNodosParam.getLength() == 0) {
                       // Se guarda en una String el texto que contiene
                       // el nodo etiquetado como "nombre"
                       String nombreMonstruo = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                       

                       // Obtenemos el contenido del nodo a añadir
                       // según el nombre del monstruo
                       String textoNodo = null;
                       if (nombreNodo.equals("gritoguerra")) {
                           textoNodo=Metodos.obtenerGritoGuerra(nombreMonstruo);
                       } else if (nombreNodo.equals("eslindo")) {
                           textoNodo=Metodos.obtenerEsLindo(nombreMonstruo);
                       }
                       
                       
                       // Utilizamos el método crearNodo para crear
                       // un nodo cuyo contenido es texto. Este nodo pasa
                       // a colgar del elemento que se indique por parámetro,
                       // en este caso los nodos Enemigo por los que se
                       // estaba iterando
                       Metodos.crearNodo(nombreNodo, textoNodo, documento, elemento);
                       
                   }
               }
           }
           
           // Y se crea el archivo xml
           Metodos.generarXml(documento, rutaArchivo);
           
           
           
       } catch (Exception e) {
           System.out.println("Excepción al añadir nodos xml");
       }
    }
    
    
    
    /**
     * Método que permite eliminar nodos XML en un archivo cuya ruta se pase
     * por parámetro. Los nodos que se eliminarán serán aquellos cuya etiqueta
     * coincida con una String pasada por parámetro.
     * @param rutaArchivo String que contiene la ruta del archivo XML del que
     * se eliminarán los nodos.
     * @param nombreNodo String que contiene la etiqueta del nodo a eliminar;
     * las posibilidades por el momento son "gritoguerra" y "eslindo".
     */
    public static void eliminarNodoXml(String rutaArchivo, String nombreNodo) {
        try {
            // Se empieza parseando y normalizando el archivo cuya ruta
            // se pasa por parámetro
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);
            documento.getDocumentElement().normalize();
            
            
            // Recogemos en una lista de nodos todos aquells nodos cuya
            // etiqueta coincida con la String que se pasa por parámetro
            // ("gritoguerra" o "eslindo")
            NodeList listaNodos = documento.getElementsByTagName(nombreNodo);
            
            
            // Recorremos la lista de nodos obtenida y, en cada uno de los nodos,
            // accedemos al padre; desde éste, indicamos que se elimine el nodo
            // hijo indicado, que es el mismo que se está recorriendo
            // Conseguimos de esta manera eliminar de manera efectiva los mismos
            // nodos que estamos recorriendo, es decir, aquellos cuya etiqueta
            // coincide con la String pasada por parámetro
            
            // La lista de nodos se recorre de atrás hacia adelante porque es
            // una lista dinámica, y, cuando se borra un elemento, el resto de
            // ellos se reubica. Si se intenta hacer un recorrido borrando de
            // atrás hacia adelante, los nodos se van reposicionando a medida
            // que se van borrando, y el resultado es que se borran un nodo
            // sí y un nodo no, alternativamente.
            for (int i = listaNodos.getLength()-1; i>=0; i--) {
                Node nodo = listaNodos.item(i);
                nodo.getParentNode().removeChild(nodo);
            }
            
            // Y regeneramos el XML
            Metodos.generarXml(documento, rutaArchivo);
            
        } catch (Exception e) {
            System.out.println("Excepción al eliminar nodos xml");
        }
    }
}
