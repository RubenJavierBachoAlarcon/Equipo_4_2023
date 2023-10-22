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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.w3c.dom.NodeList;

/**
 *
 * @author pokem, patri, bacho
 */
public class TrabajoAccDat1Equ4 {
    
    public static void main(String[] args) {
        
        Metodos.iniciarJuegoNormal();
        Metodos.iniciarJuegoPersonalizado();
        
        Metodos.crearDirEncriptados("Exports");
        
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Seleccionar Modo");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String[] opciones = {"Modo normal", "Modo personalizado"};
            int seleccion = JOptionPane.showOptionDialog(ventana, "Selecciona el modo de juego", "Modo normal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if (seleccion == 0) {
                Juego juego = new Juego();
                juego.setTitle("Juego sobre mostros sin copyright");
                juego.setVisible(true);
            } else if (seleccion == 1) {
                Personalizado personalizado = new Personalizado();
                personalizado.setTitle("Juego para ver si los mostros sin copyright te parecen lindos");
                personalizado.setVisible(true);
            }
        });
    }
    
}
