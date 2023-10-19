/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabajoaccdat1equ4;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Patricia
 */
public class GestionContenido extends DefaultHandler {
    private boolean inEnemigo = false;
    private boolean inId = false;
    private boolean inNombre = false;
    private boolean inElemento = false;
    private StringBuilder id = new StringBuilder();
    private StringBuilder nombre = new StringBuilder();
    private StringBuilder elemento = new StringBuilder();
    private StringBuilder nuevoXml = new StringBuilder();

    public GestionContenido() {
        super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Enemigo")) {
        inEnemigo = true;
    } else if (qName.equalsIgnoreCase("id")) {
        inId = true;
    } else if (qName.equalsIgnoreCase("nombre")) {
        inNombre = true;
    } else if (qName.equalsIgnoreCase("elemento")) {
        inElemento = true;
    }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Enemigo")) {
        inEnemigo = false;

        String idDesencriptado = Metodos.desencriptarContenido(id.toString());
        String nombreDesencriptado = Metodos.desencriptarContenido(nombre.toString());
        String elementoDesencriptado = Metodos.desencriptarContenido(elemento.toString());

        nuevoXml.append("<Enemigo>")
                .append("<id>").append(idDesencriptado).append("</id>")
                .append("<nombre>").append(nombreDesencriptado).append("</nombre>")
                .append("<elemento>").append(elementoDesencriptado).append("</elemento>")
                .append("</Enemigo>");

        // Limpia los acumuladores para el próximo elemento
       id = new StringBuilder();
       nombre = new StringBuilder();
       elemento = new StringBuilder();
    } else if (qName.equalsIgnoreCase("id")) {
        inId = false;
    } else if (qName.equalsIgnoreCase("nombre")) {
        inNombre = false;
    } else if (qName.equalsIgnoreCase("elemento")) {
        inElemento = false;
    }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       if (inEnemigo) {
        String car = new String(ch, start, length);
        car = car.replaceAll("[\t\n]", "");

        if (inId) {
            id.append(car);
        } else if (inNombre) {
            nombre.append(car);
        } else if (inElemento) {
            elemento.append(car);
        }
    }
    }

    public String getNuevoXml() {
        return nuevoXml.toString();
    }
    
}
