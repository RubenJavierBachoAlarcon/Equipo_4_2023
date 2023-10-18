/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import com.mycompany.trabajoaccdat1equ4.Juego;
import com.mycompany.trabajoaccdat1equ4.Personalizado;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author pokem, patri, bacho
 */
public class TrabajoAccDat1Equ4 {

    public static void main(String[] args) {

        int tamañoRegistro = 64;

        Metodos.crearBestiario("enemigos.dat");
        Metodos.crearZona(".\\Zona 1");
        Metodos.crearZona(".\\Zona 2");
        Metodos.crearZona(".\\Zona 3");

        Metodos.creaGrupo("Grupo 1-1.dat", tamañoRegistro, "enemigos.dat", 5);
        Metodos.moverGrupo(".\\Grupo 1-1.dat", ".\\Zona 1\\Grupo 1-1.dat");

        Metodos.creaGrupo("Grupo 1-2.dat", tamañoRegistro, "enemigos.dat", 6);
        Metodos.moverGrupo(".\\Grupo 1-2.dat", ".\\Zona 1\\Grupo 1-2.dat");

        Metodos.creaGrupo("Grupo 1-3.dat", tamañoRegistro, "enemigos.dat", 7);
        Metodos.moverGrupo(".\\Grupo 1-3.dat", ".\\Zona 1\\Grupo 1-3.dat");

        Metodos.creaGrupo("Grupo 2-1.dat", tamañoRegistro, "enemigos.dat", 1);
        Metodos.moverGrupo(".\\Grupo 2-1.dat", ".\\Zona 2\\Grupo 2-1.dat");

        Metodos.creaGrupo("Grupo 2-2.dat", tamañoRegistro, "enemigos.dat", 2);
        Metodos.moverGrupo(".\\Grupo 2-2.dat", ".\\Zona 2\\Grupo 2-2.dat");

        Metodos.creaGrupo("Grupo 2-3.dat", tamañoRegistro, "enemigos.dat", 3);
        Metodos.moverGrupo(".\\Grupo 2-3.dat", ".\\Zona 2\\Grupo 2-3.dat");

        Metodos.creaGrupo("Grupo 3-1.dat", tamañoRegistro, "enemigos.dat", 4);
        Metodos.moverGrupo(".\\Grupo 3-1.dat", ".\\Zona 3\\Grupo 3-1.dat");

        Metodos.creaGrupo("Grupo 3-2.dat", tamañoRegistro, "enemigos.dat", 8);
        Metodos.moverGrupo(".\\Grupo 3-2.dat", ".\\Zona 3\\Grupo 3-2.dat");

        Metodos.creaGrupo("Grupo 3-3.dat", tamañoRegistro, "enemigos.dat", 9);
        Metodos.moverGrupo(".\\Grupo 3-3.dat", ".\\Zona 3\\Grupo 3-3.dat");

        File bestiario = new File(".\\enemigos.dat");
        bestiario.delete();

        //Metodos.reescrituraID("tierra", ".\\Zona 3\\Grupo 3-3.dat");
        Metodos.modoFacil(".\\Zona 3\\Grupo 3-3.dat", "fuego");
        Metodos.convertirGrupoAXml(".\\Zona 3\\Grupo 3-3.dat", ".\\Zona 3\\Grupo 3-3.xml");
        Metodos.modificarXml(".\\Zona 3\\Grupo 3-3.xml", "Hydrobabosa", "Tengo sueño cojones");

        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Seleccionar Modo");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String[] opciones = {"Modo de Juego", "Modo Personalizado"};
            int seleccion = JOptionPane.showOptionDialog(ventana, "Selecciona el modo de juego", "Modo de Juego", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == 0) {
                Juego juego = new Juego();
                juego.setVisible(true);
            } else if (seleccion == 1) {
                Personalizado personalizado = new Personalizado();
                personalizado.setVisible(true);
            }
        });
                
                
    }
}
